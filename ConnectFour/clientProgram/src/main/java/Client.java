import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{


	Socket socketClient;

	private CFourInfo clientSide;

	ObjectOutputStream out;
	ObjectInputStream in;

	private Consumer<Serializable> callback;
	Client(Consumer<Serializable> call){
		clientSide = new CFourInfo();
		callback = call;
	}

	public void run() {

		try {
			socketClient= new Socket(InetAddress.getLocalHost(),5555);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		while(true) {
			try {
				String temp = clientSide.buttonClickColor;
				clientSide = (CFourInfo)in.readObject();
				if (temp != "") {
					clientSide.buttonClickColor = temp;
				}
				if (clientSide.have2players == false) {
					callback.accept("Please wait for another player");
				}
				if (clientSide.turn == true) {
					BoardController.enablePieces();
				}
				callback.accept("Sent");
			}
			catch(Exception e) {}
		}

	}

	public void send(String data) {

		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// class that saves the game board with GameButton objects
	public class GameButton extends Button {
		GameButton () {
			this.setDisable(true);
			this.setMaxHeight(75);
			this.setMaxWidth(111);
			this.setOnAction(e -> {
				this.setStyle("-fx-background-color: #ffff00");
				clientSide.turn = false;
				clientSide.lastPlay = "has moved to "
				send();
			});
		}
	}
}
