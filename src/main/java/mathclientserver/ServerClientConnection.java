package mathclientserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClientConnection extends Thread{
    Socket clientSocket;
    private DataOutputStream output;
    private DataInputStream input;
    private BufferedReader reader;
    private ServerSocket serverSocket;



    public ServerClientConnection(ServerSocket serverSocket, Socket connection){
        this.serverSocket = serverSocket;
        this.clientSocket = connection;

        start();
    }

    public void run(){
        runConnection();
        startConnnection();
          }

    public void runConnection(){
        String inputLine, outputLine;
        try {
            OutputStream os =  clientSocket.getOutputStream();
            output = new DataOutputStream(os);

            InputStream is = clientSocket.getInputStream();
            input = new DataInputStream(is);

            reader = new BufferedReader(new InputStreamReader(System.in));

            while((inputLine = input.readUTF()) !=null){
                outputLine = reader.readLine();
                output.writeUTF(outputLine);
                if(outputLine.equals("exit")){
                    System.out.println("Server ClientStandBy Connection is closing");
                    break;
                }
            }
            stopConnection();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void startConnnection(){
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

    public void stopConnection(){

        try{
            input.close();
            output.close();
            reader.close();
            clientSocket.close();
            System.out.println("ServerClient is closed");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
