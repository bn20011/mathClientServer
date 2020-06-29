package mathclientserver;

public class ServerRunner {
    public static void main(String[] args){
        run();
    }


    public static void run(){
        int port = 455;
        MathServer server = new MathServer(port);
        server.execute();
    }
}
