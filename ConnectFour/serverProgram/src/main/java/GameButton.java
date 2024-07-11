import javafx.scene.control.Button;
import javafx.scene.paint.Color;

// class that saves the game board with GameButton objects
public class GameButton extends Button {
    Color defaultColor;
    GameButton() {
        this.setDisable(true);
        this.setMaxHeight(75);
        this.setMaxWidth(111);
        this.setOnAction(e -> {

        });
    }
}
