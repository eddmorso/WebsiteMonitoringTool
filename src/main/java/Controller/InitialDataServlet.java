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
import java.util.List;

public class InitialDataServlet extends HttpServlet {
    private MonitoringDataStorage monitoringDataStorage;

    public InitialDataServlet(){
       monitoringDataStorage = new DatabaseMonitoringDataStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MonitoredURL> monitoredURLS = monitoringDataStorage.getMonitoredURL();

        req.setAttribute("monitoredURLS", monitoredURLS);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
