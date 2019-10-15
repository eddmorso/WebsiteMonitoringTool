package Controller;

import Model.Data.Database;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MonitoredDataServlet extends HttpServlet {
    private Model model;
    private final String COULDNT_GET_SIZE_MSG = "Couldn't get page size;";
    private final String UNEXPECTED_TIME_MSG = "Unexpected response time;";
    private final String UNEXPECTED_RESPONSE_CODE_MSG = "Unexpected response code;";
    private final String UNEXPECTED_SIZE = "Unexpected size;";

    public MonitoredDataServlet(){
        model = new Model(new Database());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) {
            boolean isWarning = false;
            boolean isCritical = false;
            List<GatheredData> gatheredData = model.updateData();

            for (GatheredData data : gatheredData) {
                StatusMessage statusMessage = new StatusMessage(Status.OK, "");
                Status statusCode = checkResponseCode(data);
                Status statusTime = checkResponseTime(data);
                Status statusSize = checkSize(data);

                if (!statusCode.equals(Status.OK)) {
                    statusMessage.changeStatus(statusCode);
                    statusMessage.addMessage(UNEXPECTED_RESPONSE_CODE_MSG);
                }

                if (!statusTime.equals(Status.OK)) {
                    statusMessage.changeStatus(statusTime);
                    statusMessage.addMessage(UNEXPECTED_TIME_MSG);
                }

                if (!statusSize.equals(Status.OK)) {
                    if (statusSize.equals(Status.WARNING)){
                        statusMessage.changeStatus(statusSize);
                        statusMessage.addMessage(COULDNT_GET_SIZE_MSG);
                    } else {
                        statusMessage.changeStatus(statusSize);
                        statusMessage.addMessage(UNEXPECTED_SIZE);
                    }
                }
                data.setStatus(statusMessage);

                if (statusMessage.getStatus().equals(Status.WARNING)){
                    isWarning = true;
                } else if (statusMessage.getStatus().equals(Status.CRITICAL)){
                    isCritical = true;
                }
            }

            req.setAttribute("isWarning", isWarning);
            req.setAttribute("isCritical", isCritical);
            req.setAttribute("gatheredData", gatheredData);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("monitoringTable.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String urlRun = req.getParameter("buttonRun");
        String urlStop = req.getParameter("buttonStop");

        if (urlRun != null) {
           model.startMonitoredURL(urlRun);
           doGet(req, resp);
        }

        if (urlStop != null){
            model.stopMonitoredURL(urlStop);
            doGet(req, resp);
        }
    }

    public Status checkResponseCode(GatheredData data){
        synchronized (this) {
            MonitoredURL monitoredURL = model.getMonitoredUrl(data.getUrl());

            if (monitoredURL.getResponseCode() == data.getResponseCode()) {
                return Status.OK;
            }
        }
        return Status.CRITICAL;
    }

    public Status checkResponseTime(GatheredData data){
        synchronized (this) {
            MonitoredURL monitoredURL = model.getMonitoredUrl(data.getUrl());
            long currentTime = data.getResponseTime();
            long maxTime = monitoredURL.getMaxResponseTime();
            long middle = maxTime / 2;

            if (currentTime <= middle){
                return Status.OK;
            } else if (currentTime > middle && currentTime <= maxTime){
                return Status.WARNING;
            }
        }
        return Status.CRITICAL;
    }

    public Status checkSize(GatheredData data){
        synchronized (this) {
            MonitoredURL monitoredURL = model.getMonitoredUrl(data.getUrl());
            int minSize = monitoredURL.getMinSize();
            int maxSize = monitoredURL.getMaxSize();
            int size = data.getPageSize();

            if (size >= minSize && size <= maxSize) {
                return Status.OK;
            } else if (size < 0){
                return Status.WARNING;
            }
        }
        return Status.CRITICAL;
    }
}
