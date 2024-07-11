import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ResultsController extends GuiClient implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button replayButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button confirm2;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    //static so each instance of controller can access to update
    private static String blank = "";

    public void restartGame(ActionEvent e) throws IOException {
        // restart the game
    }

    public void exitGame(ActionEvent e) throws IOException {
        System.exit(1);
        // NEED TO IMPLEMENT CLIENT DROP
    }
}
