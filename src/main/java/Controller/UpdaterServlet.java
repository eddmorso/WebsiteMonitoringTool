package Controller;

import Model.Data.Database;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdaterServlet extends HttpServlet {
    private Model model;

    public UpdaterServlet(){
        model = new Model(new Database());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        MonitoredURL monitoredURL = model.getMonitoredUrl(url);
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
                model.updateMaxResponseTime(url, maxTime);
            }
            if (minTime != monitoredURL.getMinResponseTime()){
                model.updateMinResponseTime(url, minTime);
            }
            if (monitoringTimeSeconds != monitoredURL.getMonitoringTimeSeconds()){
                model.updateMonitoringTime(url, monitoringTimeSeconds);
            }
            if (responseCode != monitoredURL.getResponseCode()){
                model.updateResponseCode(url, responseCode);
            }
            if (minSize != monitoredURL.getMinSize()){
                model.updateMinSize(url, minSize);
            }
            if (maxSize != monitoredURL.getMaxSize()){
                model.updateMaxSize(url, maxSize);
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
        requestDispatcher.forward(req, resp);
    }
}
