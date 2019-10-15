package Controller;

import Model.*;
import Model.Data.DatabaseMonitoringDataStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoverServlet extends HttpServlet {
    private Monitor monitor;

    public RemoverServlet(){
        monitor = new Monitor(new DatabaseMonitoringDataStorage());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        MonitoredURL monitoredURL = monitor.getMonitoredUrl(url);

        if (monitoredURL != null){
            monitor.removeMonitoredURL(url);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
        requestDispatcher.forward(req, resp);
    }
}
