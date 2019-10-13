package Controller;

import Model.*;
import Model.Data.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InitialDataServlet extends HttpServlet {
    private Model model;

    public InitialDataServlet(){
        this.model = new Model(new Database());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MonitoredURL> monitoredURLS = model.getMonitoredUrl();

        req.setAttribute("monitoredURLS", monitoredURLS);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable.jsp");
        requestDispatcher.forward(req, resp);
    }
}
