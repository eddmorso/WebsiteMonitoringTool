package Model;

import Model.Data.Data;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<MonitoredURL> monitoredURLS;
    private List<GatheredData> gatheredData;
    private Data data;
    private long beginningTime;

    public Model(Data data){
        this.data = data;
        beginningTime = System.currentTimeMillis();
        monitoredURLS = data.getMonitoredURL();
        gatheredData = new ArrayList<>();
    }

    public List<GatheredData> updateData() {
        long deltaTime = System.currentTimeMillis() - beginningTime;

        gatheredData.clear();

        if (!monitoredURLS.isEmpty()) {
            for (MonitoredURL monitoredURL : monitoredURLS) {
                long monitoringLeftTime = monitoredURL.getMonitoringTimeSeconds() * 1000 - deltaTime;

                if (monitoringLeftTime <= 0) {
                    continue;
                }
                gatheredData.add(new GatheredData(monitoredURL.getUrl(),
                        getCurrentResponseCode(monitoredURL.getUrl()),
                        getCurrentResponseTime(monitoredURL.getUrl()),
                        getCurrentSize(monitoredURL.getUrl()),
                        monitoringLeftTime));
            }
        }
        return gatheredData;
    }

    public void updateMonitoredURLS(){
        monitoredURLS = data.getMonitoredURL();
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

    }

    public void startMonitoredURL(String url){

    }

    public void addMonitoredURL(MonitoredURL monitoredURL){
        data.addMonitoredURL(monitoredURL);
        updateMonitoredURLS();
    }

    public void removeMonitoredURL(String url){
        data.removeMonitoredURL(url);
        updateMonitoredURLS();
    }

    public void updateMinResponseTime(String targetUrl, int updatedMinResponseTime){
        data.updateMinResponseTime(targetUrl, updatedMinResponseTime);
        updateMonitoredURLS();
    }

    public void updateMaxResponseTime(String targetUrl, int updatedMaxResponseTime){
        data.updateMaxResponseTime(targetUrl, updatedMaxResponseTime);
        updateMonitoredURLS();
    }

    public void updateMonitoringTime(String targetUrl, int updateMonitoringTime){
        data.updateMonitoringTime(targetUrl, updateMonitoringTime);
        updateMonitoredURLS();
    }

    public void updateResponseCode(String targetUrl, int updatedResponseCode){
        data.updateResponseCode(targetUrl, updatedResponseCode);
        updateMonitoredURLS();
    }

    public void updateMinSize(String targetUrl, int updateMinSize){
        data.updateMinSize(targetUrl, updateMinSize);
        updateMonitoredURLS();
    }

    public void updateMaxSize(String targetUrl, int updateMaxSize){
        data.updateMinSize(targetUrl, updateMaxSize);
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
