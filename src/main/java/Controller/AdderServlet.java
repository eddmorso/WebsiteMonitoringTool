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
import java.util.List;

public class AdderServlet extends HttpServlet {
    private MonitoringDataStorage monitoringDataStorage;

    public AdderServlet(){
        monitoringDataStorage = DatabaseMonitoringDataStorage.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");

        if (!isExistedUrl(url)) {
            int maxTime = Integer.valueOf(req.getParameter("maxResponseTime"));
            int minTime = Integer.valueOf(req.getParameter("minResponseTime"));
            int monitoringTimeSeconds = Integer.valueOf(req.getParameter("monitoringTimeSeconds"));
            int responseCode = Integer.valueOf(req.getParameter("responseCode"));
            int minSize = Integer.valueOf(req.getParameter("minSize"));
            int maxSize = Integer.valueOf(req.getParameter("maxSize"));

            addMonitoredURL(new MonitoredURL(url, minTime, maxTime, monitoringTimeSeconds, responseCode, minSize, maxSize));

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
            requestDispatcher.forward(req, resp);
        } else {
            resp.getWriter().write(
                    "<html>" +
                            "<head></head>" +
                            "<body>" +
//                                "<script>const func = () => {\n" +
//                                    " alert('URL is already exists')\n" +
//                                    "window.location.href = 'initialDataTable'\n" +
//                                "}" +
//                            "func()" +
//                            "</script>" +
                            "<script>alert('Url is already exists')</script>" +
                            "</body>" +
                       "</html>");
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("initialDataTable");
//            requestDispatcher.forward(req, resp);
        }
    }

    public boolean isExistedUrl(String url){
        List<MonitoredURL> monitoredURLS = monitoringDataStorage.getMonitoredURL();

        for (MonitoredURL monitoredURL : monitoredURLS){
            if (monitoredURL.getUrl().equals(url)){
                return true;
            }
        }
        return false;
    }

    private void addMonitoredURL(MonitoredURL monitoredURL){
        monitoringDataStorage.addMonitoredURL(monitoredURL);
    }

}
