import java.util.ArrayList;

/*
*
* BaccaratServer - Project 3 for CS324, Fall Semester - 2020
* Authors: Daniel LeVert, Adam Sammakia
* UIN Adam:  659002242 Daniel: 673238527
*
*/

public class BaccaratGame {
	ArrayList<Card> playerHand;
	ArrayList<Card> dealerHand;
	BaccaratDealer theDealer;
	double currentBet;
	double totalWinnings;
	String betOn;
	
	//
	// constructor
	//
	public BaccaratGame() {
		this.playerHand = new ArrayList<Card>();
		this.dealerHand = new ArrayList<Card>();
		this.theDealer = new BaccaratDealer();
		this.currentBet= -1;
		this.totalWinnings = -1;
	}
	
    // evaluateWinnings
	//
	// determines the result of the game using betOn and current bet. 
	public double evaluateWinnings() {
		Double result = 0.0;
		
		if(BaccaratGameLogic.whoWon(this.playerHand, this.dealerHand).contentEquals(betOn)) {
			if(betOn.contentEquals("Draw")) { 
				result = 8*this.currentBet; 
			}
			else if(betOn.contentEquals("Banker")) {
				result = 0.95*this.currentBet;
			}
			else if(betOn.contentEquals("Player")) {
				result = this.currentBet;
			}
		}
		else {
			result = -1*(this.currentBet); 
		}
		System.out.println("FROM GAME result is: " + result);
		return result;
	}
	
	
}
