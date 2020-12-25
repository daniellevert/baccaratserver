import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/*
 *  BaccaratServer - Project 3 for CS324, Fall Semester - 2020
 * Authors: Daniel LeVert, Adam Sammakia
 * UIN Adam:  659002242 Daniel: 673238527
 * 
 * A server with a graphical user interface for the game of Baccarat. 
 * Can host two clients playing the game, and clients can connect or 
 * disconnect at will. The GUI also displays when a client has joined or left 
 * the game. The GUI also displays each round a client plays how much money
 * they have won or lost that hand. Contains all of the game logic for gameplay.
 *
 */

public class ServerGUI extends Application {
	/*
	 * Declaring all fields that will be used in various functions
	 * throughout the program so all functions have access
	 */
	Button startServer, off;
	Text title, enterPort;
	TextField port, ip;
	Integer portNum;
	ServerThread serverConnection;
	ListView<String> dataList;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*
		 * Initializing fields that will be used in and out of start()
		 */
		off = new Button();
		dataList = new ListView<String>();
		title = new Text();
		
		primaryStage.setTitle("Baccarat (Server)");
		dataList = new ListView<String>(); // Added
		
		/*
		 * Creates a start screen to be displayed upon program launch
		 */
		title = new Text("BACCARAT SERVER");
		enterPort = new Text("Enter port number: ");
		startServer = new Button("Start Server");
		startServer.setDisable(true);
		
		title.setStyle("-fx-font-size: 25;");
		
		port = new TextField();
		port.setMaxWidth(300);
		
		VBox screenItems = new VBox(title, enterPort, port, startServer);
		screenItems.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(screenItems, 700,700);
		
		/*
		 * Stores port number upon enter key being pressed
		 */
		port.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)){
			portNum = Integer.parseInt(port.getText());
			startServer.setDisable(false);
			port.setDisable(true);
			System.out.println("Port num is: " + portNum);
			}
		});
		
		/*
		 * switches server screen and adds data to dataList
		 */
		Scene mainScene = createServerScreen();
		this.startServer.setOnAction(e->{ primaryStage.setScene(mainScene);
											primaryStage.setTitle("Baccarat (Server)");
											mainScene.getStylesheets().add("style.css"); 
				serverConnection = new ServerThread(portNum, data -> {
					Platform.runLater(()->{
						dataList.getItems().add(data.toString());
					});

				});
			
		});
		
		this.off.setOnAction(e->{System.exit(0); });
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Scene createServerScreen() {
		Text title = new Text();
		title.setText("BACCARAT GAME STATE");
		title.setStyle("-fx-font-size: 30;");
		// Center title
		StackPane root1 = new StackPane();
		title.setTextAlignment(TextAlignment.CENTER);
		root1.getChildren().add(title);
		StackPane.setAlignment(title, Pos.CENTER);

		off.setText("Turn server off");
		
//		dataList = new ListView<String>();
//		dataList.getItems().add("Ex: Client 1 has connected");
//		dataList.getItems().add("Ex: Client 2 has disconnected");
		
		VBox screen = new VBox(title, dataList, off);
		screen.setAlignment(Pos.CENTER);
		
		return new Scene(screen, 700,700);
	}
	
}
