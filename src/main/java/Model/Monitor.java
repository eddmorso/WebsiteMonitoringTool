package Model;

import Model.Data.MonitoringDataStorage;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Monitor {
    private List<MonitoredURL> monitoredURLS;
    private List<MonitoringResult> gatheredData;
    private MonitoringDataStorage monitoringDataStorage;


    public Monitor(MonitoringDataStorage monitoringDataStorage){
        this.monitoringDataStorage = monitoringDataStorage;
        monitoredURLS = monitoringDataStorage.getMonitoredURL();
        gatheredData = new ArrayList<>();

        monitoredURLS.forEach(monitoredURL -> monitoredURL.setBeginningTime(System.currentTimeMillis()));
    }

    public List<MonitoringResult> updateData() {
        if (!monitoredURLS.isEmpty()) {
            for (MonitoredURL monitoredURL : monitoredURLS) {
                long deltaTime = System.currentTimeMillis() - monitoredURL.getBeginningTime();
                long monitoringLeftTime = monitoredURL.getMonitoringTimeSeconds() * 1000 - deltaTime;

                //??????
                //new Thread(() ->
                if (gatheredData.contains(new MonitoringResult(monitoredURL.getUrl()))) {
                    if (!monitoredURL.isStopped()) {
                        if (monitoringLeftTime <= 0) {
                            gatheredData.remove(new MonitoringResult(monitoredURL.getUrl()));
                            continue;
                        }
                        gatheredData.remove(new MonitoringResult(monitoredURL.getUrl()));
                        gatheredData.add(new MonitoringResult(monitoredURL.getUrl(),
                                getCurrentResponseCode(monitoredURL.getUrl()),
                                getCurrentResponseTime(monitoredURL.getUrl()),
                                getCurrentSize(monitoredURL.getUrl()),
                                monitoringLeftTime));
                    }
                    //).start();
                } else {
                    gatheredData.add(new MonitoringResult(monitoredURL.getUrl(),
                            getCurrentResponseCode(monitoredURL.getUrl()),
                            getCurrentResponseTime(monitoredURL.getUrl()),
                            getCurrentSize(monitoredURL.getUrl()),
                            monitoringLeftTime));
                }
            }
        }
        return gatheredData;
    }

    public void updateMonitoredURLS(){
        monitoredURLS = monitoringDataStorage.getMonitoredURL();
    }

    public long getCurrentResponseTime(String url){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            long startTime = System.currentTimeMillis();
            connection.connect();
            long endTime = System.currentTimeMillis();

            return endTime - startTime;

        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getCurrentSize(String url){
        try{
            URLConnection connection = new URL(url).openConnection();

            return connection.getContentLength();
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getCurrentResponseCode(String url){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            return connection.getResponseCode();

        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public void stopMonitoredURL(String url){
        monitoredURLS.get(monitoredURLS.indexOf(new MonitoredURL(url))).setStopped(true);
    }

    public void startMonitoredURL(String url){
        int indexUrl = monitoredURLS.indexOf(new MonitoredURL(url));
        int indexData = gatheredData.indexOf(new MonitoringResult(url));
        int newMonitoringTime = (int) gatheredData.get(indexData).getMonitoringTimeLeft() / 1000;

        monitoredURLS.get(indexUrl).setStopped(false);
        monitoredURLS.get(indexUrl).setBeginningTime(System.currentTimeMillis());
        monitoredURLS.get(indexUrl).setMonitoringTimeSeconds(newMonitoringTime);
    }

    public void addMonitoredURL(MonitoredURL monitoredURL){
        monitoringDataStorage.addMonitoredURL(monitoredURL);
        updateMonitoredURLS();
    }

    public void removeMonitoredURL(String url){
        monitoringDataStorage.removeMonitoredURL(url);
        updateMonitoredURLS();
    }

    public void updateMinResponseTime(String targetUrl, int updatedMinResponseTime){
        monitoringDataStorage.updateMinResponseTime(targetUrl, updatedMinResponseTime);
        updateMonitoredURLS();
    }

    public void updateMaxResponseTime(String targetUrl, int updatedMaxResponseTime){
        monitoringDataStorage.updateMaxResponseTime(targetUrl, updatedMaxResponseTime);
        updateMonitoredURLS();
    }

    public void updateMonitoringTime(String targetUrl, int updateMonitoringTime){
        monitoringDataStorage.updateMonitoringTime(targetUrl, updateMonitoringTime);
        updateMonitoredURLS();
    }

    public void updateResponseCode(String targetUrl, int updatedResponseCode){
        monitoringDataStorage.updateResponseCode(targetUrl, updatedResponseCode);
        updateMonitoredURLS();
    }

    public void updateMinSize(String targetUrl, int updateMinSize){
        monitoringDataStorage.updateMinSize(targetUrl, updateMinSize);
        updateMonitoredURLS();
    }

    public void updateMaxSize(String targetUrl, int updateMaxSize){
        monitoringDataStorage.updateMinSize(targetUrl, updateMaxSize);
        updateMonitoredURLS();
    }

    public List<MonitoredURL> getMonitoredUrl() {
        return monitoredURLS;
    }

    public MonitoredURL getMonitoredUrl(String url){
       for (MonitoredURL monitoredURL : monitoredURLS){
           if (monitoredURL.getUrl().equals(url)){
               return monitoredURL;
           }
       }
       throw new RuntimeException("No such url");
    }
}
