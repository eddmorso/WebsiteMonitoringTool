package Model.Data;
import Model.MonitoredURL;

import java.awt.image.DataBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database extends Data{
    private final String DATA_BASE_URL = "jdbc:mysql://localhost:3306/monitored_urls";
    private final String USER = "test";
    private final String PASSWORD = "test";
    private Connection connection;

//    public Database(String url, String user, String pass){
//        super();
//        DATA_BASE_URL = url;
//        USER = user;
//        PASSWORD = pass;
//    }

    @Override
    void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATA_BASE_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MonitoredURL getMonitoredURL(String url) {
        try{
            MonitoredURL monitoredURL;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM urls WHERE URL = ?");

            statement.setString(1, url);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            monitoredURL = new MonitoredURL(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                    resultSet.getInt(6), resultSet.getInt(7));

            statement.close();

            return monitoredURL;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MonitoredURL> getMonitoredURL() {
        List<MonitoredURL> monitoredURLS = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM urls");

            while (resultSet.next()){
                monitoredURLS.add(new MonitoredURL(resultSet.getString(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                        resultSet.getInt(6), resultSet.getInt(7)));
            }

            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return monitoredURLS;
    }

    @Override
    public void addMonitoredURL(MonitoredURL monitoredURL) {
        if (monitoredURL != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO urls (URL, Min_response_time, Max_response_time, Monitoring_time, " +
                                "Response_code, Min_size, Max_size) VALUES (?, ?, ?, ?, ?, ?, ?)");

                preparedStatement.setString(1, monitoredURL.getUrl());
                preparedStatement.setInt(2, monitoredURL.getMinResponseTime());
                preparedStatement.setInt(3, monitoredURL.getMaxResponseTime());
                preparedStatement.setInt(4, monitoredURL.getMonitoringTimeSeconds());
                preparedStatement.setInt(5, monitoredURL.getResponseCode());
                preparedStatement.setInt(6, monitoredURL.getMinSize());
                preparedStatement.setInt(7, monitoredURL.getMaxSize());

                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeMonitoredURL(String url) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM urls WHERE URL = ?");

            preparedStatement.setString(1, url);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateMinResponseTime(String targetUrl, int updatedMinResponseTime) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET Min_response_time = ? WHERE URL = ?");

            preparedStatement.setInt(1, updatedMinResponseTime);
            preparedStatement.setString(2, targetUrl);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateMaxResponseTime(String targetUrl, int updatedMaxResponseTime) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET Max_response_time = ? WHERE URL = ?");

            preparedStatement.setInt(1, updatedMaxResponseTime);
            preparedStatement.setString(2, targetUrl);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateMonitoringTime(String targetUrl, int updateMonitoringTime) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET Monitoring_time = ? WHERE URL = ?");

            preparedStatement.setInt(1, updateMonitoringTime);
            preparedStatement.setString(2, targetUrl);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateResponseCode(String targetUrl, int updatedResponseCode) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET Monitoring_time = ? WHERE URL = ?");

            preparedStatement.setInt(1, updatedResponseCode);
            preparedStatement.setString(2, targetUrl);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateMinSize(String targetUrl, int updateMinSize) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET Monitoring_time = ? WHERE URL = ?");

            preparedStatement.setInt(1, updateMinSize);
            preparedStatement.setString(2, targetUrl);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateMaxSize(String targetUrl, int updateMaxSize) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE urls SET Monitoring_time = ? WHERE URL = ?");

            preparedStatement.setInt(1, updateMaxSize);
            preparedStatement.setString(2, targetUrl);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            return !connection.isClosed();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}