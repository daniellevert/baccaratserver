import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestBaccaratGame {
	BaccaratGame bg;
	BaccaratInfo bi;
	
	@BeforeEach
	void instantiateBaccaratGame() {
		bg = new BaccaratGame();
		bi = new BaccaratInfo();
	}
	
	//Test Constructor
	@Test
	void testBGConstructorPlayerHand() {
		assertTrue(bg.playerHand.isEmpty(), "playerHand not empty upon initialization");
	}
	
	@Test
	void testBGConstructorBankerHand() {
		assertTrue(bg.dealerHand.isEmpty(), "dealerHand not empty upon initialization");
	}
	
	@Test
	void testBGConstructorTheDealer() {
		assertTrue(bg.theDealer.deck.isEmpty(), "theDealer does not have an empty deck upon initialization");
	}
	
	@Test
	void testBGConstructorCurrentBet() {
		assertEquals(-1, bg.currentBet, "currentBet not -1 after initialization");
	}
	
	@Test
	void testBGConstructorTotalWinnings() {
		assertEquals(-1, bg.totalWinnings, "totalWinnings not -1 after initialization");
	}
	
	//Test evaluateWinnings
	@Test
	void testEvaluateWinningsDraw() {
//		BaccaratInfo.betOn = "Draw";
		bi.betOn = "Draw";
		bg.betOn = bi.betOn;
		bg.currentBet = 10;
		Card c1 = new Card("Spades", 4);
		Card c2 = new Card("Diamonds", 4);
		Card c3 = new Card("Hearts", 4);
		Card c4 = new Card("Clubs", 4);
		ArrayList<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(c1);
		dealerHand.add(c2);
		bg.dealerHand = dealerHand;
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		playerHand.add(c3);
		playerHand.add(c4);
		bg.playerHand = playerHand;
		assertEquals(80, bg.evaluateWinnings(), "$10 winning bet on draw did not return $80");
	}
	
	@Test
	void testEvaluateWinningsBanker() {
		bi.betOn = "Banker";
		bg.betOn = bi.betOn;
		bg.currentBet = 10;
		Card c1 = new Card("Spades", 4);
		Card c2 = new Card("Diamonds", 4);
		Card c3 = new Card("Hearts", 3);
		Card c4 = new Card("Clubs", 4);
		ArrayList<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(c1);
		dealerHand.add(c2);
		bg.dealerHand = dealerHand;
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		playerHand.add(c3);
		playerHand.add(c4);
		bg.playerHand = playerHand;
		assertEquals(10*0.95, bg.evaluateWinnings(), "$10 winning bet on banker did not return $9.50");
	}
	
	@Test
	void testEvaluateWinningsPlayer() {
		bi.betOn = "Player";
		bg.betOn = bi.betOn;
		bg.currentBet = 10;
		Card c1 = new Card("Spades", 4);
		Card c2 = new Card("Diamonds", 3);
		Card c3 = new Card("Hearts", 4);
		Card c4 = new Card("Clubs", 4);
		ArrayList<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(c1);
		dealerHand.add(c2);
		bg.dealerHand = dealerHand;
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		playerHand.add(c3);
		playerHand.add(c4);
		bg.playerHand = playerHand;
		assertEquals(10, bg.evaluateWinnings(), "$10 winning bet on player did not return $10");
	}
	
	@Test
	void testEvaluateWinningsPlayerLosing() {
		bi.betOn = "Banker";
		bg.betOn = bi.betOn;
		bg.currentBet = 10;
		Card c1 = new Card("Spades", 4);
		Card c2 = new Card("Diamonds", 3);
		Card c3 = new Card("Hearts", 4);
		Card c4 = new Card("Clubs", 4);
		ArrayList<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(c1);
		dealerHand.add(c2);
		bg.dealerHand = dealerHand;
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		playerHand.add(c3);
		playerHand.add(c4);
		bg.playerHand = playerHand;
		assertEquals(-10, bg.evaluateWinnings(), "$10 losing bet on banker did not return -$10");
	}

}
