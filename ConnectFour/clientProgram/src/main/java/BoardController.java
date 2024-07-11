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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class BoardController extends GuiClient implements Initializable {
    @FXML
    GridPane gridPane;

    @FXML
    BorderPane borderPane;

    @FXML
    VBox textBox;

    static ArrayList<GameButton> board1D;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board1D = new ArrayList<GameButton>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                GameButton gb = new GameButton();
                gridPane.add(gb, j, i);
                board1D.add(gb);
            }
        }
    }

    public static void enablePieces() {
        for (int i = 0; i < board1D.size(); i++) {
            board1D.get(i).setDisable(false);
        }
    }

    public void ButtonPress(ActionEvent e) throws IOException {
    }

}
