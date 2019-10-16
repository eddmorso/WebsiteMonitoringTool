import Model.Data.DatabaseMonitoringDataStorage;
import Model.MonitoredURL;
import org.junit.BeforeClass;


public class Test {
    private static DatabaseMonitoringDataStorage databaseMonitoringDataStorage;

    @BeforeClass
    public static void prepare(){
        databaseMonitoringDataStorage = DatabaseMonitoringDataStorage.getInstance();
    }

    @org.junit.Test
    public void setConnectionTest(){
        if (databaseMonitoringDataStorage.isConnected()){
            System.out.println("setConnectionTest - succeeded");
        } else {
            System.out.println("setConnectionTest - failed");
        }
    }

    @org.junit.Test
    public void getAllRecordsTest(){
        if (databaseMonitoringDataStorage.getMonitoredURL().size() == 3){
            System.out.println("getAllRecordsTest - succeeded");
        } else {
            System.out.println("getAllRecordsTest - failed");
        }
    }

    @org.junit.Test
    public void getOneRecordTest(){
        MonitoredURL monitoredURL = databaseMonitoringDataStorage.getMonitoredURL("https://www.youtube.com/");

        if ("https://www.youtube.com/".equals(monitoredURL.getUrl())){
            System.out.println("getOneRecordTest - succeeded");
        } else {
            System.out.println("getOneRecordTest - failed");
        }
    }

    @org.junit.Test
    public void addOneRecordTest(){
        MonitoredURL monitoredURL = new MonitoredURL("https://www.google.com/",
                0, 1, 4, 200, 1, 2);

        databaseMonitoringDataStorage.addMonitoredURL(monitoredURL);

        if (databaseMonitoringDataStorage.getMonitoredURL().size() == 4){
            System.out.println("addOneRecordTest - succeeded");
        } else {
            System.out.println("addOneRecordTest - failed");
        }
    }

    @org.junit.Test
    public void removeOneRecordTest(){
        databaseMonitoringDataStorage.removeMonitoredURL("https://www.google.com/");

        if (databaseMonitoringDataStorage.getMonitoredURL().size() == 3){
            System.out.println("removeOneRecordTest - succeeded");
        } else {
            System.out.println("removeOneRecordTest - failed");
        }
    }

    @org.junit.Test
    public void updateMinResponseTimeTest(){
        databaseMonitoringDataStorage.updateMinResponseTime("https://www.youtube.com/", 17);

        if (databaseMonitoringDataStorage.getMonitoredURL("https://www.youtube.com/").getMinResponseTime() == 17){
            System.out.println("updateMinResponseTimeTest - succeeded");
        } else {
            System.out.println("updateMinResponseTimeTest - failed");
        }
    }
}
