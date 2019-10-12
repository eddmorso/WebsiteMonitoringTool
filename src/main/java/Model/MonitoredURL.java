package Model;

public class MonitoredURL{
    private String url;
    private int minResponseTime;
    private int maxResponseTime;
    private int monitoringTimeSeconds;
    private int responseCode;
    private int minSize;
    private int maxSize;

    public MonitoredURL(String url, int minResponseTime, int maxResponseTime, int monitoringTimeSeconds,
                        int responseCode, int minSize, int maxSize){
        this.url = url;
        this.minResponseTime = minResponseTime;
        this.maxResponseTime = maxResponseTime;
        this.monitoringTimeSeconds = monitoringTimeSeconds;
        this.responseCode = responseCode;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public MonitoredURL(String url){
        this.url = url;
    }

    public int getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(int minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public int getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(int maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public int getMonitoringTimeSeconds() {
        return monitoringTimeSeconds;
    }

    public void setMonitoringTimeSeconds(int monitoringTimeSeconds) {
        this.monitoringTimeSeconds = monitoringTimeSeconds;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MonitoredURL) {
            MonitoredURL monitoredURL = (MonitoredURL) obj;
            return monitoredURL.url.equals(this.url);
        } else {
            return false;
        }
    }
}