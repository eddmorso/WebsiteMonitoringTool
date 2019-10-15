package Model;

public class MonitoringResult {
    private String url;
    private long responseTime;
    private int responseCode;
    private int pageSize;
    private long monitoringTimeLeft;
    private String status;

    public MonitoringResult(String url, long responseTime, int responseCode, int pageSize, long monitoringTimeLeft){
        this.url = url;
        this.pageSize = pageSize;
        this.responseCode = responseCode;
        this.responseTime = responseTime;
        this.monitoringTimeLeft = monitoringTimeLeft;
    }

    public MonitoringResult(String url){
        this.url = url;
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

    public void setStatus(StatusMessage status) {
        this.status = status.toString();
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "URL: " + url;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MonitoringResult){
            MonitoringResult monitoringResult = (MonitoringResult) obj;

            return monitoringResult.url.equals(this.url);
        }
        return false;
    }
}
