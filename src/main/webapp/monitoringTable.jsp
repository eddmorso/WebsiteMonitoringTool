<%@ page import="Model.GatheredData" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Monitoring Table</title>
</head>
<body>
    <%
        response.setIntHeader("Refresh", 5);
    %>
    <table border="1">
        <thead>
            <tr>
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
                List<GatheredData> gatheredData = (List<GatheredData>) request.getAttribute("gatheredData");
            %>
            <%
                for (GatheredData data : gatheredData){
            %>
            <tr>
                <td><%=data.getUrl()%></td>
                <td><%=data.getPageSize()%></td>
                <td><%=data.getResponseCode()%>></td>
                <td><%=data.getResponseTime()%></td>
                <td><%=data.getMonitoringTimeLeft()%></td>
                <td><%=data.getStatus()%></td>
                <td>
                    <form action="monitoringTable" method="post">
                        <input type="hidden" name="buttonRun" value="<%=data.getUrl()%>">
                        <input type="submit" value="Run">
                    </form>
                </td>
                <td>
                    <form action="monitoringTable" method="post">
                        <input type="hidden" name="buttonStop" value="<%=data.getUrl()%>">
                        <input type="submit" value="Stop">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
