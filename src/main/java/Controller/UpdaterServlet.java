package Controller;

import Model.Data.DatabaseMonitoringDataStorage;
import Model.*;
import Model.Data.MonitoringDataStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdaterServlet extends HttpServlet {
    private MonitoringDataStorage monitoringDataStorage;

    public UpdaterServlet(){
        monitoringDataStorage = new DatabaseMonitoringDataStorage();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        MonitoredURL monitoredURL = monitoringDataStorage.getMonitoredURL(url);
        int maxTime = Integer.valueOf(req.getParameter("maxResponseTime"));
        int minTime = Integer.valueOf(req.getParameter("minResponseTime"));
        int monitoringTimeSeconds = Integer.valueOf(req.getParameter("monitoringTimeSeconds"));
        int responseCode = Integer.valueOf(req.getParameter("responseCode"));
        int minSize = Integer.valueOf(req.getParameter("minSize"));
        int maxSize = Integer.valueOf(req.getParameter("maxSize"));

        req.setAttribute("monitoredURL", monitoredURL);
        req.setAttribute("url", url);

        if (monitoredURL != null){
            if (maxTime != monitoredURL.getMaxResponseTime()){
                updateMaxResponseTime(url, maxTime);
            }
            if (minTime != monitoredURL.getMinResponseTime()){
                updateMinResponseTime(url, minTime);
            }
            if (monitoringTimeSeconds != monitoredURL.getMonitoringTimeSeconds()){
                updateMonitoringTime(url, monitoringTimeSeconds);
            }
            if (responseCode != monitoredURL.getResponseCode()){
                updateResponseCode(url, responseCode);
            }
            if (minSize != monitoredURL.getMinSize()){
                updateMinSize(url, minSize);
            }
            if (maxSize != monitoredURL.getMaxSize()){
                updateMaxSize(url, maxSize);
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
        requestDispatcher.forward(req, resp);
    }

    private void updateMinResponseTime(String targetUrl, int updatedMinResponseTime){
        monitoringDataStorage.updateMinResponseTime(targetUrl, updatedMinResponseTime);
    }

    private void updateMaxResponseTime(String targetUrl, int updatedMaxResponseTime){
        monitoringDataStorage.updateMaxResponseTime(targetUrl, updatedMaxResponseTime);
    }

    private void updateMonitoringTime(String targetUrl, int updateMonitoringTime){
        monitoringDataStorage.updateMonitoringTime(targetUrl, updateMonitoringTime);
    }

    private void updateResponseCode(String targetUrl, int updatedResponseCode){
        monitoringDataStorage.updateResponseCode(targetUrl, updatedResponseCode);
    }

    private void updateMinSize(String targetUrl, int updateMinSize){
        monitoringDataStorage.updateMinSize(targetUrl, updateMinSize);
    }

    private void updateMaxSize(String targetUrl, int updateMaxSize){
        monitoringDataStorage.updateMinSize(targetUrl, updateMaxSize);
    }
}
