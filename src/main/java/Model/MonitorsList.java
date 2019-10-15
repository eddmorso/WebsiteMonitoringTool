package Model;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MonitorsList extends Iterable<Monitor>{
    private ConcurrentHashMap<MonitoredURL, Monitor> monitors;

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

    @Override
    public Iterator<Monitor> iterator() {
        return monitors.values().iterator();
    }
}
