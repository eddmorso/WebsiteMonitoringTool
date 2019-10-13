package Model;

public class StatusMessage {
    private Status status;
    private String msg;

    public StatusMessage(Status status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public void addMessage(String msg){
        this.msg += " " + msg;
    }

    public void changeStatus(Status status){
        if (!this.status.equals(Status.CRITICAL)){
            this.status = status;
        }
    }

    @Override
    public String toString() {
        return status.toString() + " " + msg;
    }
}
