package Controller;

public enum Status {
    OK  {
        @Override
        public String toString() {
            return "OK " + getMsg();
        }
    },
    WARNING {
        @Override
        public String toString() {
            return "WARNING " + getMsg();
        }
    },
    CRITICAL {
        @Override
        public String toString() {
            return "CRITICAL " + getMsg();
        }
    };
    private String msg = "";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
