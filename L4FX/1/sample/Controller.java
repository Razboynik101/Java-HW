package sample;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class Controller {
    String userName ="Anonim";
    @FXML
    TextArea TextArea;
    @FXML
    TextField TextField;

    public void sendMsg (){
        TextArea.appendText( userName+":"+TextField.getText()+"\n");
        TextField.clear();
        TextField.requestFocus();
        String file = "src/audio/send.mp3";
        Media sound = new Media(new File(file).toURI().toString());
        MediaPlayer mp = new MediaPlayer(sound);
        mp.play();
    }
    public void login (){

        try {
            new Login();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}