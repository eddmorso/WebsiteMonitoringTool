package Model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Monitor extends Thread{
    private MonitoredURL monitoredURL;
    private MonitoringResult monitoringResult;
    private HttpURLConnection connection;
    private long beginningTime;

    public Monitor(MonitoredURL monitoredURL) {
        this.monitoredURL = monitoredURL;

        try {
            connection = (HttpURLConnection) new URL(monitoredURL.getUrl()).openConnection();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public MonitoringResult updateData() {
        if (monitoredURL != null) {
            long monitoringLeftTime = getMonitoringTimeLeft();

            if (monitoringLeftTime < 0){
                return null;
            }

            monitoringResult = new MonitoringResult(monitoredURL.getUrl(),
                    getCurrentResponseTime(),
                    getCurrentResponseCode(),
                    getCurrentSize(),
                    monitoringLeftTime);
            return monitoringResult;
        } else {
            throw new RuntimeException("No monitored url to update");
        }
    }

    public long getCurrentResponseTime(){
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

    public int getCurrentSize() {
        return connection.getContentLength();
    }

    public int getCurrentResponseCode(){
        try {
            return connection.getResponseCode();
        } catch (IOException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Couldn't get response  code");
    }

    public long getMonitoringTimeLeft(){
        long monitoringTimeMillis = monitoredURL.getMonitoringTimeSeconds() * 1000;
        long deltaTime = System.currentTimeMillis() - beginningTime;

        return monitoringTimeMillis - deltaTime;
    }

    public void stopMonitoredURL(){
        try{
            this.wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void startMonitoredURL() {
        this.notify();
        setBeginningTime(System.currentTimeMillis());
    }

    public void setBeginningTime(long beginningTime) {
        this.beginningTime = beginningTime;
    }

    @Override
    public synchronized void start() {
        setBeginningTime(System.currentTimeMillis());

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