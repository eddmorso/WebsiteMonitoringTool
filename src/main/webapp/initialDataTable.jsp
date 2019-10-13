<%@ page import="Model.MonitoredURL" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Initial Data</title>
    </head>
    <body>
        <h2>Set your data</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>URL</th>
                    <th>Min response time</th>
                    <th>Max response time</th>
                    <th>Monitoring time seconds</th>
                    <th>Response code</th>
                    <th>Min size</th>
                    <th>Max size</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<MonitoredURL> monitoredURLS = (List<MonitoredURL>) request.getAttribute("monitoredURLS");
                %>
                <%
                    for (MonitoredURL monitoredURL : monitoredURLS){
                %>
                <tr>
                    <td><%=monitoredURL.getUrl()%></td>
                    <td><%=monitoredURL.getMinResponseTime()%></td>
                    <td><%=monitoredURL.getMaxResponseTime()%></td>
                    <td><%=monitoredURL.getMonitoringTimeSeconds()%></td>
                    <td><%=monitoredURL.getResponseCode()%></td>
                    <td><%=monitoredURL.getMinSize()%></td>
                    <td><%=monitoredURL.getMaxSize()%></td>
                    <td>
                        <form action="editorPage" method="post">
                            <input type="hidden" name="url" value="<%=monitoredURL.getUrl()%>">
                            <input type="submit" value="Edit">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <Br>
        <a href="monitoringTable">Click to start monitoring</a>
    </body>
</html>