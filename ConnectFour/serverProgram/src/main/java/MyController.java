import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class MyController extends GuiServer implements Initializable {
    @FXML
    private TextField portTextField;

    @FXML
    private Button startButton;

    @FXML
    private Button enterButton;

    @FXML
    private ListView listView;

    @FXML
    private BorderPane gameSpread;

    //static so each instance of controller can access to update
    private static String portNumber = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    public void setPortNo(ActionEvent e){
        portNumber = portTextField.getText();
        System.out.println(portNumber);
        portTextField.setText("");
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView li) {
        this.listView = li;
    }

    public static String getPortNo() {
        return portNumber;
    }

    public void enableServer(ActionEvent e) throws IOException {
        listItems = new ListView<String>();
        Scene serverScene;
        Parent root = FXMLLoader.load(GuiServer.class.getResource("/FXML/MyDisplayGS.fxml"));
        BorderPane gameSpread2 = new BorderPane();
        gameSpread2.setCenter(listItems);
        serverScene = new Scene(gameSpread2, 800, 800);
        serverScene.getStylesheets().add("/styles/style1.css");
        changeScene(serverScene);

        serverConnection = new Server(data -> {
            Platform.runLater(()->{
                listItems.getItems().add(data.toString());
            });
        });
    }
}
