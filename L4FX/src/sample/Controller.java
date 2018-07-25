package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    String userName = "Anonim";
    @FXML
    TextArea TextArea;
    @FXML
    TextField TextField;

    public void sendMsg() {
        try {
            out.writeUTF(TextField.getText());
            TextField.clear();
            TextField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }












        String file = "src/audio/send.mp3";
        Media sound = new Media(new File(file).toURI().toString());
        MediaPlayer mp = new MediaPlayer(sound);
        mp.play();
    }

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    final String IP_ADRESS = "Localhost";
    final int PORT = 8189;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverclosed")){

                                break;
                            }
                            TextArea.appendText(str+"\n");

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
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

    public void login() {

        try {
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}