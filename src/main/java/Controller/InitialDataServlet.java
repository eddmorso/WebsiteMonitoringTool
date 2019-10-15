package Controller;

import Model.*;
import Model.Data.DatabaseMonitoringDataStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InitialDataServlet extends HttpServlet {
    private Monitor monitor;

    public InitialDataServlet(){
        this.monitor = new Monitor(new DatabaseMonitoringDataStorage());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        monitor.updateMonitoredURLS();
        List<MonitoredURL> monitoredURLS = monitor.getMonitoredUrl();

        req.setAttribute("monitoredURLS", monitoredURLS);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}
