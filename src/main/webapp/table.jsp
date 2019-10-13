<%@ page import="Model.GatheredData" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>MonitoringTable</title>
</head>
<body>
    <%
        response.setIntHeader("Refresh", 5);
    %>
    <table border="1">

        <thead>
            <tr>
                <th>#</th>
                <th>URL</th>
                <th>Page Size</th>
                <th>Response Code</th>
                <th>Response Time</th>
                <th>Monitoring Time Left</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <%
                int i = 1;
                List<GatheredData> gatheredData = (List<GatheredData>) request.getAttribute("gatheredData");
            %>
            <%
                for (GatheredData data : gatheredData){
            %>
                <tr>
                    <td><%=i++%></td>
                    <td><%=data.getUrl()%></td>
                    <td><%=data.getPageSize()%></td>
                    <td><%=data.getResponseCode()%>></td>
                    <td><%=data.getResponseTime()%></td>
                    <td><%=data.getMonitoringTimeLeft()%></td>
                    <td><%=data.getStatus()%></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
