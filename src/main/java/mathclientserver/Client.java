package mathclientserver;

import networking.serversocket.interfacepack.ClientInterface;

import java.io.*;
import java.net.Socket;

public class Client implements ClientInterface {
    private int port;
    private String host;
    private Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream output;
    private BufferedReader reader;

    public Client(String host, int port ){
        this.port = port;
        this.host = host;
    }

    public void run(){
        start();
        readInput();
        stop();
    }

    public void start(){
        try {
            clientSocket = new Socket(host, port);
            System.out.println("Connected!");
            OutputStream outputStream = clientSocket.getOutputStream();
            output = new DataOutputStream(outputStream);
            System.out.println("sending greeting: hello");
            output.writeUTF("hello");
            output.flush();
            InputStream inputStream = clientSocket.getInputStream();
            input = new DataInputStream(inputStream);
            String s = input.readUTF();
            System.out.println("Server: " + s);
            reader = new BufferedReader(new InputStreamReader(System.in));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void readInput(){
        String exit = "exit";
        reader = new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        while(true){
            try{
                String inputSocket = reader.readLine();
                System.out.println("Client: " + inputSocket);
                output.writeUTF(inputSocket);
                output.flush();
                if(inputSocket.equals(exit)){
                    System.out.println("Client is closing");
                    stop();
                }
                String line = input.readUTF();
                System.out.println("Server: " + line); //from Server:

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        try{
            input.close();
            output.close();
            reader.close();
            clientSocket.close();
            System.out.println("Client is closed");
            System.exit(0);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
