package sample.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;


    public Server() {
        clients = new Vector<>();

        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Server started...");




            while (true){
                socket = server.accept();
                System.out.println("Client connected");
                clients.add(new ClientHandler(this,socket));


            }






        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void BroadcastMsg(String msg){
        for (ClientHandler o:clients) {

if (o.IsSocletClosed()==false) {
    o.sendMsg(msg);
}
        }
    }
}
