package Controller;

import Model.Data.Database;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MonitoredDataServlet extends HttpServlet {
    private Model model;
    private final String COULDNT_GET_SIZE_MSG = "Couldn't get page size";
    private final String UNEXPECTED_TIME_MSG = "Unexpected response time";
    private final String UNEXPECTED_RESPONSE_CODE_MSG = "Unexpected response code";
    private final String UNEXPECTED_SIZE = "Unexpected size";

    public MonitoredDataServlet(){
        model = new Model(new Database());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) {
            long beginningTime = System.currentTimeMillis();
            List<GatheredData> gatheredData = model.updatePage(beginningTime);

            for (GatheredData data : gatheredData) {
                StatusMessage statusMessage = new StatusMessage(Status.OK, "");

                if (!checkResponseCode(data)) {
                    statusMessage.changeStatus(Status.CRITICAL);
                    statusMessage.addMessage(UNEXPECTED_RESPONSE_CODE_MSG);
                }
                if (!checkResponseTime(data)) {
                    statusMessage.changeStatus(Status.CRITICAL);
                    statusMessage.addMessage(UNEXPECTED_TIME_MSG);

                }
                if (!checkSize(data)) {
                    statusMessage.changeStatus(Status.CRITICAL);
                    statusMessage.addMessage(UNEXPECTED_SIZE);
                }
                data.setStatus(statusMessage);
            }

            req.setAttribute("gatheredData", gatheredData);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("table.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    public boolean checkResponseCode(GatheredData data){
        synchronized (this) {
            MonitoredURL monitoredURL = model.getMonitoredUrl(data.getUrl());

            if (monitoredURL.getResponseCode() == data.getResponseCode()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkResponseTime(GatheredData data){
        synchronized (this) {
            MonitoredURL monitoredURL = model.getMonitoredUrl(data.getUrl());
            long currentTime = data.getResponseTime();
            long minTime = monitoredURL.getMinResponseTime();
            long maxTime = monitoredURL.getMaxResponseTime();

            if (currentTime >= minTime && currentTime <= maxTime) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSize(GatheredData data){
        synchronized (this) {
            MonitoredURL monitoredURL = model.getMonitoredUrl(data.getUrl());
            int minSize = monitoredURL.getMinSize();
            int maxSize = monitoredURL.getMaxSize();
            int size = data.getPageSize();

            if (size >= minSize && size <= maxSize) {
                return true;
            }
        }
        return false;
    }
}
