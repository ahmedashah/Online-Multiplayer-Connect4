import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiClient extends Application{

	Client clientConnection;
	// scenes for Client display
	Scene welcome, play, results;

	// list views for saving previous moves
	ListView<String> listItems;

	public static Stage stage;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.stage = primaryStage;
		primaryStage.setTitle("Connect 4 StartUp");
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/MyWelcome.fxml"));
		welcome = new Scene(root, 500, 500);
		welcome.getStylesheets().add("/styles/style1.css");
		primaryStage.setScene(welcome);
		primaryStage.show();
	}

	public void changeScene(Scene temp) {
		stage.setScene(temp);
	}

	public Scene createClientResults() {

		BorderPane pane = new BorderPane();
		Scene serverScene;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/myResults.fxml"));
			serverScene = new Scene(root, 500, 500);
			serverScene.getStylesheets().add("/styles/style1.css");
			return serverScene;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return new Scene(pane, 500, 500);
	}
}
