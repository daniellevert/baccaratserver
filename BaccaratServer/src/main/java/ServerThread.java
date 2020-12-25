import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

/*
*
* BaccaratServer - Project 3 for CS324, Fall Semester - 2020
* Authors: Daniel LeVert, Adam Sammakia
* UIN Adam:  659002242 Daniel: 673238527
*
*/

public class ServerThread {
	ObjectInputStream in;
	ObjectOutputStream out;
	BaccaratGame game;
	BaccaratInfo message;
	BaccaratGameLogic logic;
	Consumer<Serializable> callback;
	ArrayList<ClientT> clients;
	ServerSocket serverSocket;
	Server server;
	Integer portNum;
	int count; 
	
	//
	// ServerThread constructor
	//
	public ServerThread(int portNumber, Consumer<Serializable> call){
		
		this.portNum = portNumber; 
		this.callback = call;
		this.clients = new ArrayList<ClientT>();
		this.game = new BaccaratGame();
		this.server = new Server();
		this.count = 0;
		this.message = new BaccaratInfo(); 
		server.start(); // start the server 
	}
	
	// ClientThread
	//
	//
	class ClientT extends Thread{
		Socket connection;
		ObjectInputStream in;
		ObjectOutputStream out;
		int clientID;
		
		//
		// ClientThread constructor
		//
		public ClientT(Socket s, int id){
			System.out.println("Hello from server ClientThread constructor");
			this.connection = s;
			this.clientID = id;
		}
		
		public Boolean isNatural(ArrayList<Card> bankerHand, ArrayList<Card> playerHand) {
			if(BaccaratGameLogic.handTotal(bankerHand) >= 8 || BaccaratGameLogic.handTotal(playerHand) >= 8) {
				return true;
			}
			return false;			
		}
		
//		// getGameHandFromInfo
//		//
//		// convert an ArrayList of suites and and ArrayList of values into an ArrayList of cards
//		private ArrayList<Card> getGameHandFromInfo(ArrayList<String> suites, ArrayList<Integer> values){
//			ArrayList<Card> result = new ArrayList<Card>();
//			for(int i = 0; i < suites.size(); i++) {
//				Card newCard = new Card(suites.get(i), values.get(i));
//				result.add(newCard);
//			}
//			return result;
//		}
		
		// playGame
		//
		// Gets bet information from the client through message and plays a game with it.
		// Sends the client a completed game that the client can display. 
		public void playGame(Integer whichClient, BaccaratInfo message) throws IOException {
			ClientT t = clients.get(whichClient);
			callback.accept("Client number " + this.clientID + " started a round" );

			if(game.theDealer.deck.size() < 6) {
				game.theDealer.generateDeck(); 
				game.theDealer.shuffleDeck();
			}
			
			// load game with bet and winnings info from message
			game.currentBet = message.bid;
			game.betOn = message.betOn;
			game.totalWinnings = message.totalWinnings;
			
			callback.accept("Client number " + this.clientID + " bet $" + game.betOn);
			
			// load game with new hands
			game.playerHand = game.theDealer.dealHand();
			game.dealerHand = game.theDealer.dealHand();
			
			// determine if game is a natural
			message.natural = isNatural(game.dealerHand, game.playerHand);
			message.clearHands();
			
			
			// these are the cards that may or may not be added to the player '
			// and banker decks
			Card playerCard = null;
			Card bankerCard = null;
			
			// determine if player and banker need to take another card
			if(BaccaratGameLogic.evaluatePlayerDraw(game.playerHand)) {
				playerCard = game.theDealer.drawOne();
				game.playerHand.add(playerCard);
			}
			if(BaccaratGameLogic.evaluateBankerDraw(game.dealerHand, playerCard)) {
				bankerCard = game.theDealer.drawOne();
				game.dealerHand.add(bankerCard);				
			}
			
			// load message with the hands
			for(int i = 0; i < game.playerHand.size(); i++) {
				message.playerHandVals.add(game.playerHand.get(i).value);
				message.playerHandSuits.add(game.playerHand.get(i).suite);
			}
			for(int i = 0; i < game.dealerHand.size(); i++) {
				message.bankerHandVals.add(game.dealerHand.get(i).value);
				message.bankerHandSuits.add(game.dealerHand.get(i).suite);
			}
			
			// evaluate result of the game and update message
			double result = game.evaluateWinnings();
			message.winningsThisHand = result;
			message.totalWinnings += result;
			
			// print game result to server
			if(result > 0) {
				callback.accept("Client number " + this.clientID + " won $" + result);
			}
			else {
				callback.accept("Client number " + this.clientID + " lost $" + result);
			}
			
			t.out.writeObject(message); // IOException thrown to outer while loop
		}
		
//		// startBaccaratRound
//		//
//		// set up message to start a new round
//		public void startBaccaratRound(Integer whichClient, BaccaratInfo message) throws IOException  {
//
//			ClientT t = clients.get(whichClient);
//			
//			callback.accept("Client number " + this.clientID + " started a round" );
//			game.theDealer.generateDeck(); //TODO: should this happen each time?
//			game.theDealer.shuffleDeck();
//			// get initial two card hands for the round
//			game.playerHand = game.theDealer.dealHand();
//			game.dealerHand = game.theDealer.dealHand();
//			
//			// determine if a natural has occurred
//			message.natural = isNatural(game.dealerHand, game.playerHand);
//			
//			//////////////////////DEBUG/////////////////////////////////////////
//			System.out.println("startBaccaratRound playerHand: ");
//			for(Card c : game.playerHand) {
//				System.out.println("    " + c.value + ", " + c.suite);
//			}
//			System.out.println("startBaccaratRound bankerHand: " + game.dealerHand);
//			for(Card c : game.dealerHand) {
//				System.out.println("    " + c.value + ", " + c.suite);
//			}
//			////////////////////////////////////////////////////////////////////
//			
//			game.totalWinnings = message.totalWinnings;
//			// clear the arrays in message
//			message.bankerHandSuits.clear();
//			message.bankerHandVals.clear();
//			message.playerHandSuits.clear();
//			message.playerHandVals.clear();
//			
//			// load message with the hands
//			for(int i = 0; i < game.playerHand.size(); i++) {
//				message.playerHandVals.add(game.playerHand.get(i).value);
//				message.playerHandSuits.add(game.playerHand.get(i).suite);
//			}
//			for(int i = 0; i < game.dealerHand.size(); i++) {
//				message.bankerHandVals.add(game.dealerHand.get(i).value);
//				message.bankerHandSuits.add(game.dealerHand.get(i).suite);
//			}
//			// send message through the socket to the client
//			t.out.writeObject(message); // IOException thrown to outer while loop
//		}	
//		
//		// getGameResults
//		// 
//		// Determine if banker or player need to draw another card, 
//		// calculate the results of the hand, and send them through the socket to the client
//		public void getGameResults(Integer whichClient, BaccaratInfo message) throws IOException {
//			ClientT t = clients.get(whichClient);
//			Card playerCard = null;
//			Card bankerCard = null;
//			// update game 
//			game.playerHand = getGameHandFromInfo(message.playerHandSuits, message.playerHandVals);
//			game.dealerHand = getGameHandFromInfo(message.bankerHandSuits, message.bankerHandVals);
//			game.totalWinnings = message.totalWinnings;
//			game.currentBet = message.bid;
//			game.betOn = message.betOn;			
//			
//			callback.accept("Client number " + this.clientID + " bet $" + game.betOn);
//
//			
////			// determine if a natual has occured
////			if(BaccaratGameLogic.handTotal(game.playerHand) >= 8 || BaccaratGameLogic.handTotal(game.dealerHand) >= 8) {
////				message.natural = true;
////			}
//			
//			// determine if player and banker need to take another card
//			if(BaccaratGameLogic.evaluatePlayerDraw(game.playerHand)) {
//				playerCard = game.theDealer.drawOne();
//				game.playerHand.add(playerCard);
//			}
//			if(BaccaratGameLogic.evaluateBankerDraw(game.dealerHand, playerCard)) {
//				bankerCard = game.theDealer.drawOne();
//				game.dealerHand.add(bankerCard);				
//			}
//			//////////////////DEBUG///////////////////////
//			System.out.println("getGameResults playerHand: ");
//			for(Card c : game.playerHand) {
//				System.out.println("    " + c.value + ", " + c.suite);
//			}
//			System.out.println("getGameResults bankerHand: " + game.dealerHand);
//			for(Card c : game.dealerHand) {
//				System.out.println("    " + c.value + ", " + c.suite);
//			}
//			////////////////////////////////////////////////
//			// get the result of the game
//			System.out.println("DEBUG: betOn is:  " + message.betOn);
//			double result = game.evaluateWinnings();
//			message.winningsThisHand = result;
//			message.totalWinnings += result;
//			
//			// print game result to server
//			if(result > 0) {
//				callback.accept("Client number " + this.clientID + " won $" + result);
//			}
//			else {
//				callback.accept("Client number " + this.clientID + " lost $" + result);
//			}
//			// clear message hands so we can re-load
//			message.clearHands();
//			// add any new cards to message
//			for(int i = 0; i < game.playerHand.size(); i++) {
//				message.playerHandVals.add(game.playerHand.get(i).value);
//				message.playerHandSuits.add(game.playerHand.get(i).suite);
//			}
//			for(int i = 0; i < game.dealerHand.size(); i++) {
//				message.bankerHandVals.add(game.dealerHand.get(i).value);
//				message.bankerHandSuits.add(game.dealerHand.get(i).suite);
//			}
//			// send message through the socket to the client
//			t.out.writeObject(message); // IOException thrown to outer while loop
//
//		} 
		
		// run
		//
		// start the ClientT gameplay loop
		public void run() {
			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);	
			}
			catch(Exception e) {
				System.out.println("DEBUG: Streams not open");
			}
			
			while(true) {
				try {
					// main loop for gameplay
//					startBaccaratRound(this.clientID, message); // sends message to client
					message = (BaccaratInfo) in.readObject();
					playGame(this.clientID, message);
//					getGameResults(this.clientID, message); // sends message to client
//					message = (BaccaratInfo) in.readObject();
					
				} catch (Exception e) {
					callback.accept("Client number " + this.clientID + " has disconnected");
					e.printStackTrace();
			    	clients.remove(this);
			    	count--; 
			    	break;
			    }
		    }// end while 
	    }// end run 
	}

		class Server extends Thread{
			public void run() {
				try(ServerSocket mySocket = new ServerSocket(portNum);){ 
					System.out.println("DEBUG: Server started");				
					
					// add clients to the server
					while(true) {
						ClientT c = new ClientT(mySocket.accept(), count);
						callback.accept("A client number " + count + " has connected to server: ");
						clients.add(c); // add the client to the server and into the game loop
						c.start();
						count ++; // count tracks the client id
					}
				}
				catch(Exception e) {
					callback.accept("Server socket did not launch");	
				}
			}		
		}
}
	

	
  