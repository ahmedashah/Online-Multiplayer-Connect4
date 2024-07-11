import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiServer extends Application{

	// scenes for Server display
	Scene intro, displayGS;

	// int to store the inputted port no
	Server serverConnection;

	public static Stage stage;

	ListView<String> listItems;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	// start method loads the server intro scene
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.stage = primaryStage;
		primaryStage.setTitle("Connect 4 StartUp");
		intro = createServerIntro();
		primaryStage.setScene(intro);
		primaryStage.show();
	}

	public void changeScene(Scene temp) {
		stage.setScene(temp);
	}

	// method to create the server intro scene and set it to the intro in start
	public Scene createServerIntro() {
		BorderPane pane = new BorderPane();
		Scene serverScene;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/MyIntro.fxml"));
			serverScene = new Scene(root, 500, 500);
			serverScene.getStylesheets().add("/styles/style1.css");
			return serverScene;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return new Scene(pane, 500, 500);
	}

	public Scene createDisplayGS() {
		BorderPane pane = new BorderPane();
		Scene serverScene;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/MyDisplayGS.fxml"));
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
