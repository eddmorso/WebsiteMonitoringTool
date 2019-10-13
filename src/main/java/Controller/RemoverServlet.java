package Controller;

import Model.*;
import Model.Data.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoverServlet extends HttpServlet {
    private Model model;

    public RemoverServlet(){
        model = new Model(new Database());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        MonitoredURL monitoredURL = model.getMonitoredUrl(url);

        if (monitoredURL != null){
            model.removeMonitoredURL(url);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
        requestDispatcher.forward(req, resp);
    }
}
