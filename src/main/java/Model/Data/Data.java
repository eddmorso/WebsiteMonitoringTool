package Model.Data;

import Model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Data{

    public Data(){
        init();
    }

    abstract void init();

    public abstract MonitoredURL getMonitoredURL(String url);

    public abstract List<MonitoredURL> getMonitoredURL();

    public abstract void addMonitoredURL(MonitoredURL monitoredURL);

    public abstract void removeMonitoredURL(String url);

    public abstract void updateMinResponseTime(String targetUrl, int updatedMinResponseTime);

    public abstract void updateMaxResponseTime(String targetUrl, int updatedMaxResponseTime);

    public abstract void updateMonitoringTime(String targetUrl, int updateMonitoringTime);

    public abstract void updateResponseCode(String targetUrl, int updatedResponseCode);

    public abstract void updateMinSize(String targetUrl, int updateMinSize);

    public abstract void updateMaxSize(String targetUrl, int updateMaxSize);

}
