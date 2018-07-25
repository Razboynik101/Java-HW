package sample.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Server server;




    ClientHandler(Server server,Socket socket){

        try {

            this.socket =socket;
            this.in=new DataInputStream(socket.getInputStream());
            this.out=new DataOutputStream(socket.getOutputStream());
this.server = server;

            new Thread(new Runnable() {
                @Override



                public void run() {


                            try {
                        while (true){
                            String str = in.readUTF();

                            if(str.equals("/end")){
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            //System.out.println("Client: " + str);
                            server.BroadcastMsg(str);
                        }
                        }


                     catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }




                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }



        }
        public boolean IsSocletClosed(){
            boolean a= socket.isClosed();
            return a;


    }
    public void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
