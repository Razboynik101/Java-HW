package gb.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
 //   private List<String> blackList;

    public String getNick() {
        return nick;
    }

    String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
           // this.blackList = new ArrayList<>();
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if(str.startsWith("/addUser")) {
                            String[] tokens = str.split(" ");
                            AuthService.addUser(tokens[1], tokens[2], tokens[3]);
                            sendMsg("Регистрация прошла успешно");



                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(3000);
                                     //   out.writeUTF("/end");
                                        sendMsg("Время истекло");
                                       // Stop();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } //catch IOException e) {
                                        //e.printStackTrace();
                                    //}

                                }
                            }).start();


                        }


                        if(str.startsWith("/AuthExeption")) {
                            sendMsg("Поля пусты");
                        }
                        if(str.startsWith("/auth")) {
                            String[] tokens = str.split(" ");
                            String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);

                            if(newNick != null) {
                                if(!server.isNickBusy(newNick)) {
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(this);












                                    break;
                                } else {
                                    sendMsg("Учетная запись уже используется");
                                }
                            } else {
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }
                    while (true) {
                        String str = in.readUTF();
                        if(str.startsWith("/")){
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if(str.startsWith("/w ")) {
                                String[] tokens = str.split(" ", 3);
                                server.sendPersonalMsg(this, tokens[1], tokens[2]);
                            }

                            if(str.startsWith("/ban ")){
                                String[] tokens = str.split(" ");
                                AuthService.banUser(tokens[1]);
                             //   blackList.add(tokens[1]);
                                sendMsg("Вы добавили пользователя " + tokens[1] + " в чёрный список");
                            }
                            if(str.startsWith("/unban ")){
                                String[] tokens = str.split(" ");
                                AuthService.unbanUser(tokens[1]);
                                //   blackList.add(tokens[1]);
                                sendMsg("Вы убрали пользователя " + tokens[1] + " из чёрного списка");
                            }
                        } else {
                            server.broadcastMsg(this,nick + " " + str);
                        }
                        System.out.println("Client: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
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
                    server.unsubscribe(this);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Stop(){
        server.unsubscribe(this);
        try {
            out.writeUTF("/serverclosed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String nick) {
     //   return blackList.contains(nick);
if (AuthService.checkBan(nick) == true){
    return true;
} return false;
      // return AuthService.checkBan(nick);
    }
}
