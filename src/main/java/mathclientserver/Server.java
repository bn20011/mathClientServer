package mathclientserver;

import networking.serversocket.interfacepack.ServerInterface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ServerInterface {
    private ServerClientConnection connection;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream output;
    private BufferedReader reader;
    private int port;

    public Server(int port){
        this.port = port;
    }

    public DataInputStream getDataInputStream(){
        return input;
    }

    public DataOutputStream getDataOutputStream(){
        return output;
    }

    public BufferedReader getReader(){
        return reader;
    }

    public void run(){
        start();
        runConnection();
    }

    public void start(){
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            startConnection();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    public void startConnection(){
        System.out.println("Establishing Server-Client Connection");
        try {
            OutputStream os =  clientSocket.getOutputStream();
            output = new DataOutputStream(os);

            InputStream is = clientSocket.getInputStream();
            input = new DataInputStream(is);

            reader = new BufferedReader(new InputStreamReader(System.in));
            String greeting = input.readUTF();
            System.out.println("Client: " + greeting);
            String check = "hello";
            if (check.equals(greeting)) {
                System.out.println("Valid greeting: " + greeting);
                output.writeUTF("Hello Client");
                output.flush();

            } else {
                output.writeUTF("Wrong msg");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server-Client Connection is established");
    }

    public void runConnection(){
        String exit = "exit";
        String inputLine, outputLine;
        try {
            while(true){
                inputLine = input.readUTF();
                System.out.println("Client: " + inputLine);
                if(inputLine.equals(exit)){
                    System.out.println("Client is closing ");
                }
             outputLine = reader.readLine();
             System.out.println("Server: " + outputLine);
             output.writeUTF(outputLine);
                if(outputLine.equals("exit")){
                    output.writeUTF("exit");
                    System.out.println("Server is closing");
                    stop();
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

/*
    public void setSocket(int port){
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            InputStream is = clientSocket.getInputStream();
            input = new DataInputStream(is);

            OutputStream os = clientSocket.getOutputStream();
            output = new DataOutputStream(os);
            reader = new BufferedReader(new InputStreamReader(System.in));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    public void sendServerMsg(){
        System.out.println("Send Msg");
        String exit = "exit";
        String response;
        while (true) {
            try {
                response = reader.readLine();
                System.out.println("Server: " + response);
                output.writeUTF("Server: " + response);
                output.flush();
                boolean checkExit = response.equals(exit);
                if (checkExit == true) {
                    System.out.println(exit);
                    output.writeUTF("Server is closed");
                    output.flush();
                    System.exit(0);
                }
            } catch (IOException e) {
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
            serverSocket.close();
            System.out.println("closed");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

/*
*     public void start(){
        System.out.println("Establishing ServerClientConnection");
        try {
            String greeting = input.readUTF();
            System.out.println("ClientStandBy: " + greeting);
            String check = "hello";
            if (check.equals(greeting)) {
                System.out.println("Valid greeting: " + greeting);
                output.writeUTF("Hello ClientStandBy");
                output.flush();

            } else {
                output.writeUTF("Wrong msg");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ServerClientConnection established");
    }
* */