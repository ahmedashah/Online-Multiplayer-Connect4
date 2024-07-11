import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.Socket;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeController extends GuiClient implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button connectButton;

    @FXML
    private Button confirm1;

    @FXML
    private Button confirm2;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    //static so each instance of controller can access to update
    private static String portNumber = "";

    private static String ipAddr = "";

    public void setAddr(ActionEvent e) throws IOException {
        ipAddr = textField1.getText();
        textField1.setText("");
    }

    public void setPortNo(ActionEvent e) throws IOException {
        portNumber = textField2.getText();
        textField2.setText("");
    }

    public static String getPortNo(){
        return portNumber;
    }

    public static String getAddr(){
        return ipAddr;
    }

    public void goToPlayScreen(ActionEvent e) throws IOException {
        Scene serverScene;
        Parent root = FXMLLoader.load(GuiClient.class.getResource("/FXML/MyBoard.fxml"));
        serverScene = new Scene(root, 800, 800);
        serverScene.getStylesheets().add("/styles/style1.css");
        changeScene(serverScene);
        listItems = new ListView<String>();
        clientConnection = new Client(data->{
            Platform.runLater(()->{listItems.getItems().add(data.toString());
            });
        });
        clientConnection.start();
    }
}
