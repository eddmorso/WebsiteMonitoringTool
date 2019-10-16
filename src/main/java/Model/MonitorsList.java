package Model;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class MonitorsList implements Iterable<Monitor>{
    private ConcurrentHashMap<MonitoredURL, Monitor> monitors;

    public MonitorsList(){
        monitors = new ConcurrentHashMap<>();
    }

    public boolean isEmpty(){
        return monitors.size() == 0;
    }

    public boolean contains(MonitoredURL monitoredURL){
        return monitors.containsKey(monitoredURL);
    }

    public void add(Monitor monitor){
        monitors.put(monitor.getMonitoredURL(), monitor);
    }

    public void remove(MonitoredURL monitoredURL){
        monitors.remove(monitoredURL);
    }

    public Monitor get(MonitoredURL monitoredURL){
        return monitors.get(monitoredURL);
    }

    @Override
    public Iterator<Monitor> iterator() {
        return monitors.values().iterator();
    }
}
