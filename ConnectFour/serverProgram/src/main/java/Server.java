import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	int count = 1;
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;


	Server(Consumer<Serializable> call){

		callback = call;
		server = new TheServer();
		server.start();
	}


	public class TheServer extends Thread{

		public void run() {

			try(ServerSocket mysocket = new ServerSocket(5555);){
				System.out.println("Server is waiting for a client!");


				while(true) {
					ClientThread c = new ClientThread(mysocket.accept(), count);
					callback.accept("client has connected to server: " + "client #" + count);
					clients.add(c);
					c.start();

					count++;
				}
			}//end of try
			catch(Exception e) {
				callback.accept("Server socket did not launch");
			}
		}//end of while
	}


	class ClientThread extends Thread{


		Socket connection;
		int count;
		ObjectInputStream in;
		ObjectOutputStream out;

		CFourInfo serverSide;

		ClientThread(Socket s, int count){
			this.connection = s;
			this.count = count;
			serverSide = new CFourInfo();
		}

		public void startGame() {
			ClientThread t = clients.get(0);
			ClientThread t2 = clients.get(1);
			try {
				serverSide.turn = true;
				serverSide.buttonClickColor = "-fx-background-color: #ff0000;";
				t.out.writeObject(serverSide);
				serverSide.buttonClickColor = "-fx-background-color: #ffff00;";
				serverSide.turn = false;
				serverSide.buttonClickColor = "";
				t2.out.writeObject(serverSide);
			}
			catch(Exception e) {}
		}

		public void updateClients(String message) {
			for(int i = 0; i < clients.size(); i++) {
				ClientThread t = clients.get(i);
				try {
					t.out.writeObject(message);
				}
				catch(Exception e) {}
			}
		}

		public void run(){

			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}

			if (clients.size() > 1) {
				serverSide.have2players = true;
				callback.accept("Game starting...");
				startGame();
			} else {
				ClientThread t = clients.get(0);
				try {
					t.out.writeObject(serverSide);
					System.out.println("HELLO");
				}
				catch(Exception e) {}
			}

			// updateClients("Client #"+count+" has joined the Server.");

			while(true) {
				try {
					String data = in.readObject().toString();
					callback.accept("client: " + count + " sent: " + data);
					updateClients("client #"+count+" said: "+data);
				}
				catch(Exception e) {
					callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					updateClients("Client #"+count+" has left the server!");
					clients.remove(this);
					break;
				}
			}
		}//end of run


	}//end of client thread
}


	
	

	
