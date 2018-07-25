package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;

        Scanner self = new Scanner(System.in);







        try {

            serv = new ServerSocket(8189);
            System.out.println("Server started...");
            sock = serv.accept();
            System.out.println("Client connected");
            Scanner sc = new Scanner(sock.getInputStream());
            PrintWriter pw = new PrintWriter(sock.getOutputStream(),true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String wrd = self.nextLine();
                        if (wrd.equals("/end")) break;            //
                        pw.println("Server: " + wrd);

                        System.out.println(wrd);
                    }
                }
            }).start();








            while (true) {
                String str = sc.nextLine();
                if (str.equals("/end")) {
                    pw.println("Client stopped server ");
                    System.out.println("Client stopped server ");

                    break;}
                pw.println("Client: " + str);

                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println("Server inicialisation error");
        }















        finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop(ServerSocket serv){
        try {
            serv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

