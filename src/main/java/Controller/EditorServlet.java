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

public class EditorServlet extends HttpServlet {
    private MonitoringDataStorage monitoringDataStorage;

    public EditorServlet(){
        monitoringDataStorage = DatabaseMonitoringDataStorage.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        MonitoredURL monitoredURL = monitoringDataStorage.getMonitoredURL(url);

        req.setAttribute("monitoredURL", monitoredURL);
        req.setAttribute("url", url);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("editorPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
