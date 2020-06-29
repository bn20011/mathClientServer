package mathclientserver;

public class ClientRunner {

    public static void main(String[] args){
        run();
    }

    static void run(){
        String host = "localhost";
        int port = 455;

        Client client = new Client(host, port);
        client.run();
    }
}
