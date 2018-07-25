package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;


public class Login extends Controller {
  public String Logname;
  @FXML
    TextField name;
  @FXML
  Button btn3;

    public Login() throws Exception
    {




        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
//        AnchorPane load = (AnchorPane) loader.load();
        Stage stage = new Stage();
        stage.setTitle("login");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 200, 100));
     //   Scene scene = new Scene(load);
       // stage.setScene(scene);
        stage.show();


    }
    public void setLogname () {
        Logname = (name.getText() + "\n");
        super.userName = Logname;
    }

}


