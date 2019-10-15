package Controller;

import Model.Data.DatabaseMonitoringDataStorage;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdaterServlet extends HttpServlet {
    private Monitor monitor;

    public UpdaterServlet(){
        monitor = new Monitor(new DatabaseMonitoringDataStorage());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        MonitoredURL monitoredURL = monitor.getMonitoredUrl(url);
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
                monitor.updateMaxResponseTime(url, maxTime);
            }
            if (minTime != monitoredURL.getMinResponseTime()){
                monitor.updateMinResponseTime(url, minTime);
            }
            if (monitoringTimeSeconds != monitoredURL.getMonitoringTimeSeconds()){
                monitor.updateMonitoringTime(url, monitoringTimeSeconds);
            }
            if (responseCode != monitoredURL.getResponseCode()){
                monitor.updateResponseCode(url, responseCode);
            }
            if (minSize != monitoredURL.getMinSize()){
                monitor.updateMinSize(url, minSize);
            }
            if (maxSize != monitoredURL.getMaxSize()){
                monitor.updateMaxSize(url, maxSize);
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
        requestDispatcher.forward(req, resp);
    }
}
