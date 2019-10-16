package Model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Monitor extends Thread {
    private MonitoredURL monitoredURL;
    private MonitoringResult monitoringResult;
    private HttpURLConnection connection;
    private long beginningTime;

    public Monitor(MonitoredURL monitoredURL, long beginningTime) {
        if (monitoredURL != null) {
            this.monitoredURL = monitoredURL;
        } else {
            throw new RuntimeException("No monitored url to update");
        }
        this.beginningTime = beginningTime;
    }

    private void updateData() {
        long monitoringLeftTime = getMonitoringTimeLeft();

        if (monitoredURL.isStopped() || monitoringLeftTime < 0) {
            return;
        }
        monitoringResult = new MonitoringResult(monitoredURL.getUrl(),
                getCurrentResponseTime(),
                getCurrentResponseCode(),
                getCurrentSize(),
                monitoringLeftTime);
    }

    private long getCurrentResponseTime() {
        try {
            long startTime = System.currentTimeMillis();
            long responseTime;

            connection.connect();

            responseTime = System.currentTimeMillis() - startTime;

            return responseTime;
        } catch (IOException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Couldn't get response time");
    }

    private int getCurrentSize() {
        return connection.getContentLength();
    }

    private int getCurrentResponseCode(){
        try {
            return connection.getResponseCode();
        } catch (IOException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Couldn't get response  code");
    }

    private long getMonitoringTimeLeft(){
        long monitoringTimeMillis = monitoredURL.getMonitoringTimeSeconds() * 1000;
        long deltaTime = System.currentTimeMillis() - beginningTime;

        return monitoringTimeMillis - deltaTime;
    }

    public void stopMonitoredURL(){
        monitoredURL.setStopped(true);
    }

    public void startMonitoredURL() {
        monitoredURL.setStopped(false);
        setBeginningTime(System.currentTimeMillis());
    }

    public void setBeginningTime(long beginningTime) {
        this.beginningTime = beginningTime;
    }

    @Override
    public synchronized void start() {
        try {
            connection = (HttpURLConnection) new URL(monitoredURL.getUrl()).openConnection();
        } catch (IOException e){
            e.printStackTrace();
        }
        super.start();
    }

    @Override
    public void run() {
        updateData();
    }

    public MonitoredURL getMonitoredURL() {
        return monitoredURL;
    }

    public MonitoringResult getMonitoringResult() {
        return monitoringResult;
    }
}