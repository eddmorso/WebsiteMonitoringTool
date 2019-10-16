package Controller;

import Model.*;
import Model.Data.DatabaseMonitoringDataStorage;
import Model.Data.MonitoringDataStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoverServlet extends HttpServlet {
    private MonitoringDataStorage monitoringDataStorage;

    public RemoverServlet(){
        monitoringDataStorage = new DatabaseMonitoringDataStorage();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        MonitoredURL monitoredURL = monitoringDataStorage.getMonitoredURL(url);

        if (monitoredURL != null){
            removeMonitoredURL(url);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
        requestDispatcher.forward(req, resp);
    }

    private void removeMonitoredURL(String url){
        monitoringDataStorage.removeMonitoredURL(url);
    }
}
