<%@ page import="Model.MonitoredURL" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Editor</title>
</head>
    <body>
        <h2><%=request.getAttribute("url")%></h2>
            <form action="updater" method="post">
                <%
                    MonitoredURL monitoredURL = (MonitoredURL) request.getAttribute("monitoredURL");
                %>
                <input type="text" name="minResponseTime" value="<%=monitoredURL.getMinResponseTime()%>"/> Min Response Time<Br>
                <input type="text" name="maxResponseTime" value="<%=monitoredURL.getMaxResponseTime()%>"/> Max Response Time<Br>
                <input type="text" name="monitoringTimeSeconds" value="<%=monitoredURL.getMonitoringTimeSeconds()%>"/> Monitoring Time Seconds<Br>
                <input type="text" name="responseCode" value="<%=monitoredURL.getResponseCode()%>"/> Response Code<Br>
                <input type="text" name="minSize" value="<%=monitoredURL.getMinSize()%>"/> Min Size<Br>
                <input type="text" name="maxSize" value="<%=monitoredURL.getMaxSize()%>"/> Max Size<Br>
                <input type="hidden" name="url" value="<%=monitoredURL.getUrl()%>"/><Br>
                <input type="submit" value="Submit">
                <a href="initialDataTable"> Click to see monitoringDataStorage set</a>
            </form>
    </body>
</html>