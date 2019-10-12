package Model;

import Controller.Status;

public class GatheredData {
    private String url;
    private long responseTime;
    private int responseCode;
    private int pageSize;
    private long monitoringTimeLeft;
    private Status status;

    public GatheredData(String url, int responseCode, long responseTime, int pageSize, long monitoringTimeLeft){
        this.url = url;
        this.pageSize = pageSize;
        this.responseCode = responseCode;
        this.responseTime = responseTime;
        this.monitoringTimeLeft = monitoringTimeLeft;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getUrl() {
        return url;
    }

    public long getMonitoringTimeLeft() {
        return monitoringTimeLeft;
    }

    @Override
    public String toString() {
        return "URL: " + url + "\n" +
                "Page size: " + pageSize + "\n" +
                "Response code: " + responseCode + "\n" +
                "Response time: " + responseTime + "\n" +
                "Monitoring time left: " + monitoringTimeLeft + "\n";
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
