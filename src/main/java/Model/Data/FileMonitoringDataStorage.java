package Model.Data;

import Model.MonitoredURL;

import java.util.List;

public class FileMonitoringDataStorage extends MonitoringDataStorage {

    @Override
    public MonitoredURL getMonitoredURL(String url) {
        return null;
    }

    @Override
    public List<MonitoredURL> getMonitoredURL() {
        return null;
    }

    @Override
    public void addMonitoredURL(MonitoredURL monitoredURL) {

    }

    @Override
    public void removeMonitoredURL(String url) {

    }

    @Override
    public void updateMinResponseTime(String targetUrl, int updatedMinResponseTime) {

    }

    @Override
    public void updateMaxResponseTime(String targetUrl, int updatedMaxResponseTime) {

    }

    @Override
    public void updateMonitoringTime(String targetUrl, int updateMonitoringTime) {

    }

    @Override
    public void updateResponseCode(String targetUrl, int updatedResponseCode) {

    }

    @Override
    public void updateMinSize(String targetUrl, int updateMinSize) {

    }

    @Override
    public void updateMaxSize(String targetUrl, int updateMaxSize) {

    }
}
