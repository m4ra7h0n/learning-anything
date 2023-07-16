public class Evil {
    public Evil(String cmd) {
        this.cmd = cmd;
    }

    static {
        System.out.println(1);
    }

    public String getCmd() {
        System.out.println("getCmd()" + this.hashCode());
        return cmd;
    }

    public Evil() {
        System.out.println("Evil()" + this.hashCode());
    }

    public void setCmd(String cmd) {
        System.out.println("setCmd()" + this.hashCode());
        this.cmd = cmd;
    }

    private String cmd;
}
