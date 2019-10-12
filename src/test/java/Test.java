import Model.Data.Database;
import Model.MonitoredURL;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;


public class Test {
    private static Database database;

    @BeforeClass
    public static void prepare(){
        database = new Database();
    }

    @org.junit.Test
    public void setConnectionTest(){
        if (database.isConnected()){
            System.out.println("setConnectionTest - succeeded");
        } else {
            System.out.println("setConnectionTest - failed");
        }
    }

    @org.junit.Test
    public void getAllRecordsTest(){
        if (database.getMonitoredURL().size() == 3){
            System.out.println("getAllRecordsTest - succeeded");
        } else {
            System.out.println("getAllRecordsTest - failed");
        }
    }

    @org.junit.Test
    public void getOneRecordTest(){
        MonitoredURL monitoredURL = database.getMonitoredURL("https://www.youtube.com/");

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

        database.addMonitoredURL(monitoredURL);

        if (database.getMonitoredURL().size() == 4){
            System.out.println("addOneRecordTest - succeeded");
        } else {
            System.out.println("addOneRecordTest - failed");
        }
    }

    @org.junit.Test
    public void removeOneRecordTest(){
        database.removeMonitoredURL("https://www.google.com/");

        if (database.getMonitoredURL().size() == 3){
            System.out.println("removeOneRecordTest - succeeded");
        } else {
            System.out.println("removeOneRecordTest - failed");
        }
    }

    @org.junit.Test
    public void updateMinResponseTimeTest(){
        database.updateMinResponseTime("https://www.youtube.com/", 17);

        if (database.getMonitoredURL("https://www.youtube.com/").getMinResponseTime() == 17){
            System.out.println("updateMinResponseTimeTest - succeeded");
        } else {
            System.out.println("updateMinResponseTimeTest - failed");
        }
    }
}
