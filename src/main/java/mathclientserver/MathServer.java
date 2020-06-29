package mathclientserver;

import java.io.*;

public class MathServer extends Server {
    private MathService mathservice;
    private DataInputStream input;
    private DataOutputStream output;
    private BufferedReader reader;


    public MathServer(int port){
        super(port);
        super.start();
        input = super.getDataInputStream();
        output = super.getDataOutputStream();
       // reader = super.getReader();
        mathservice = new MathService();


    }

    public void execute(){
        String exit = "exit";
        String inputLine ="", outputLine;
        try {
            while(true){
                inputLine = input.readUTF();
                System.out.println("Client: " + inputLine);
                if(inputLine.equals(exit)){
                    super.stop();
                }
                Double result = mathservice.returnResult(inputLine);
                String resultStr = Double.toString(result);
                System.out.println(resultStr);
                output.writeUTF(resultStr);
                if(inputLine.equals(exit)){
                    System.out.println("Client is closing ");
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
