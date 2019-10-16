package Model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Monitor {
    private MonitoredURL monitoredURL;
    private MonitoringResult monitoringResult;
    private HttpURLConnection connection;
    private long beginningTime;

    public Monitor(MonitoredURL monitoredURL) {
        if (monitoredURL != null) {
            this.monitoredURL = monitoredURL;
        } else {
            throw new RuntimeException("No monitored url to update");
        }
        beginningTime = System.currentTimeMillis();
    }

    public void updateData() {
        Thread monitorThread = new Thread(()-> {
            try {
                connection = (HttpURLConnection) new URL(monitoredURL.getUrl()).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            long monitoringLeftTime = getMonitoringTimeLeft();

            if (monitoredURL.isStopped()) {
                return;
            }

            if (monitoringLeftTime < 0){
                monitoringResult.setMonitoringTimeLeftToZero();
                return;
            }
            monitoringResult = new MonitoringResult(monitoredURL.getUrl(),
                    getCurrentResponseTime(),
                    getCurrentResponseCode(),
                    getCurrentSize(),
                    monitoringLeftTime);
        });
        monitorThread.start();
        try {
            monitorThread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
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
        monitoredURL.setMonitoringTimeSeconds((int) monitoringResult.getMonitoringTimeLeft() / 1000);
    }

    public void startMonitoredURL() {
        monitoredURL.setStopped(false);
        setBeginningTime(System.currentTimeMillis());
    }

    public void setBeginningTime(long beginningTime) {
        this.beginningTime = beginningTime;
    }

    public MonitoredURL getMonitoredURL() {
        return monitoredURL;
    }

    public MonitoringResult getMonitoringResult() {
        return monitoringResult;
    }
}