<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>New URL</title>
</head>
    <body>
        <form action="adder" method="post">
            <input type="text" name="url"/> URL<Br>
            <input type="text" name="minResponseTime"/> Min Response Time<Br>
            <input type="text" name="maxResponseTime" /> Max Response Time<Br>
            <input type="text" name="monitoringTimeSeconds"/> Monitoring Time Seconds<Br>
            <input type="text" name="responseCode"/> Response Code<Br>
            <input type="text" name="minSize"/> Min Size<Br>
            <input type="text" name="maxSize"/> Max Size<Br>
            <input type="hidden" name="url"/><Br>
            <input type="submit" value="Submit">
            <a href="initialDataTable"> Click to see monitoringDataStorage set</a>
        </form>
    </body>
</html>