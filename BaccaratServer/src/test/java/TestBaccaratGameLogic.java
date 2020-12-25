import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestBaccaratGameLogic {

	ArrayList<Card> testHand;
	ArrayList<Card> testHand2;
	
	@BeforeEach
	void instantiateDealer() {
		testHand = new ArrayList<Card>();
		testHand2 = new ArrayList<Card>();
	}
	
	// Test handTotal
	@Test
	void testHandTotalTwoCardsLessThanNine() {
		Card c1 = new Card("Clubs", 4);
		Card c2 = new Card("Hearts", 2);
		testHand.add(c1);
		testHand.add(c2);
		assertEquals(6, BaccaratGameLogic.handTotal(testHand), "Card values of 2 and 4 did not return 6");
	}
	
	@Test
	void testHandTotalTwoCardsEqualsNine() {
		Card c1 = new Card("Diamonds", 4);
		Card c2 = new Card("Spades", 5);
		testHand.add(c1);
		testHand.add(c2);
		assertEquals(9, BaccaratGameLogic.handTotal(testHand), "Card values of 5 and 4 did not return 9");
	}
	
	@Test
	void testHandTotalTwoCardsGreaterThanNineEqualsZero() {
		Card c1 = new Card("Clubs", 10);
		Card c2 = new Card("Hearts", 10);
		testHand.add(c1);
		testHand.add(c2);
		assertEquals(0, BaccaratGameLogic.handTotal(testHand), "Card values of 10 and 10 did not return 9");
	}
	
	@Test
	void testHandTotalTwoCardsGreaterThanNineNonZero() {
		Card c1 = new Card("Diamonds", 10);
		Card c2 = new Card("Spades", 5);
		testHand.add(c1);
		testHand.add(c2);
		assertEquals(5, BaccaratGameLogic.handTotal(testHand), "Card values of 10 and 5 did not return 5");
	}
	
	@Test
	void testHandTotalFaceCardsZero() {
		Card c1 = new Card("Clubs", 12);
		Card c2 = new Card("Clubs", 11);
		testHand.add(c1);
		testHand.add(c2);
		assertEquals(0, BaccaratGameLogic.handTotal(testHand), "Card values of 12 (Q) and 11 (J) did not return 0");
	}
	
	@Test
	void testHandTotalFaceCardsNonZero() {
		Card c1 = new Card("Clubs", 13);
		Card c2 = new Card("Clubs", 1);
		testHand.add(c1);
		testHand.add(c2);
		assertEquals(1, BaccaratGameLogic.handTotal(testHand), "Card values of 13 (K) and 1 (A) did not return 0");
	}
	
	@Test
	void testHandTotalThreeCardsLessThanNine() {
		Card c1 = new Card("Clubs", 4);
		Card c2 = new Card("Hearts", 2);
		Card c3 = new Card("Diamonds", 1);
		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		assertEquals(7, BaccaratGameLogic.handTotal(testHand), "Card values of 2, 4 and 1 did not return 7");
	}
	
	@Test
	void testHandTotalThreeCardsEqualsNine() {
		Card c1 = new Card("Clubs", 4);
		Card c2 = new Card("Hearts", 2);
		Card c3 = new Card("Diamonds", 3);
		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		assertEquals(9, BaccaratGameLogic.handTotal(testHand), "Card values of 2, 4 and 3 did not return 9");
	}
	
	@Test
	void testHandTotalThreeGreaterThanNine() {
		Card c1 = new Card("Clubs", 11);
		Card c2 = new Card("Hearts", 2);
		Card c3 = new Card("Diamonds", 3);
		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		assertEquals(5, BaccaratGameLogic.handTotal(testHand), "Card values of 11 (J), 2 and 3 did not return 5");
	}
	
	// Test whoWon
	 @Test
	 void testWhoWonPlayer() {
		 Card pc1 = new Card("Clubs", 4);
		 Card pc2 = new Card("Diamonds", 5);
		 Card bc1 = new Card("Hearts", 4);
		 Card bc2 = new Card("Spades", 4);
		 testHand.add(pc1);
		 testHand.add(pc2);
		 testHand2.add(bc1);
		 testHand2.add(bc2);
		 assertEquals("Player", BaccaratGameLogic.whoWon(testHand, testHand2), "playerHand of 9 and Banker hand of 8 did not return 'Player'");
	 }
	 
	 @Test
	 void testWhoWonBanker() {
		 Card pc1 = new Card("Clubs", 4);
		 Card pc2 = new Card("Diamonds", 4);
		 Card bc1 = new Card("Hearts", 4);
		 Card bc2 = new Card("Spades", 5);
		 testHand.add(pc1);
		 testHand.add(pc2);
		 testHand2.add(bc1);
		 testHand2.add(bc2);
		 assertEquals("Banker", BaccaratGameLogic.whoWon(testHand, testHand2), "playerHand of 8 and Banker hand of 9 did not return 'Banker'");
	 }
	 
	 @Test
	 void testWhoWonDraw() {
		 Card pc1 = new Card("Clubs", 4);
		 Card pc2 = new Card("Diamonds", 4);
		 Card bc1 = new Card("Hearts", 4);
		 Card bc2 = new Card("Spades", 4);
		 testHand.add(pc1);
		 testHand.add(pc2);
		 testHand2.add(bc1);
		 testHand2.add(bc2);
		 assertEquals("Draw", BaccaratGameLogic.whoWon(testHand, testHand2), "playerHand of 8 and Banker hand of 9 did not return 'Draw'");
	 }
	 
	 //Test evaluatePlayerDraw
	 @Test
	 void testEvaluatePlayerDrawFalse() {
			Card c1 = new Card("Clubs", 3);
			Card c2 = new Card("Hearts", 3);
			testHand.add(c1);
			testHand.add(c2);
			assertFalse(BaccaratGameLogic.evaluatePlayerDraw(testHand), "player Hand of 6 returned true");
	 }
	 
	 @Test
	 void testEvaluatePlayerDrawTrue() {
			Card c1 = new Card("Clubs", 2);
			Card c2 = new Card("Hearts", 3);
			testHand.add(c1);
			testHand.add(c2);
			assertTrue(BaccaratGameLogic.evaluatePlayerDraw(testHand), "player Hand of 6 returned false");
	 }
	 
	 // Test evaluateBankerDraw
	 @Test
	 void testEvaluateBankerDrawNullTrue() {
		Card c1 = new Card("Clubs", 13);
		Card c2 = new Card("Hearts", 5);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, null), "Banker hand of 5 and player card of null did not return true");
		
	 }
	 
	 @Test
	 void testEvaluateBankerDrawNullFalse() {
		Card c1 = new Card("Clubs", 13);
		Card c2 = new Card("Hearts", 6);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, null), "Banker hand of 6 and player card of null did not return false");
		
	 }
	 
	 @Test
	 void testEvaluateBankerDrawZeroTrue() {
		Card c1 = new Card("Clubs", 1);
		Card c2 = new Card("Hearts", 2);
		Card pc = new Card("Spades", 10);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 3 and player card of 12 (Q) did not return true");
		
	 }
	 
	 @Test
	 void testEvaluateBankerDrawZeroFalse() {
		Card c1 = new Card("Clubs", 2);
		Card c2 = new Card("Hearts", 2);
		Card pc = new Card("Spades", 13);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 4 and player card of 12 (Q) did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawOneTrue() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 11);
		Card pc = new Card("Spades", 1);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 3 and player card of 1 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawOneFalse() {
		Card c1 = new Card("Clubs", 4);
		Card c2 = new Card("Hearts", 11);
		Card pc = new Card("Spades", 1);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 4 and player card of 1 did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawTwoTrue() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 1);
		Card pc = new Card("Spades", 2);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 4 and player card of 2 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawTwoFalse() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 2);
		Card pc = new Card("Spades", 2);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 5 and player card of 2 did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawThreeTrue() {
		Card c1 = new Card("Clubs", 2);
		Card c2 = new Card("Hearts", 2);
		Card pc = new Card("Spades", 3);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 4 and player card of 3 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawThreeFalse() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 2);
		Card pc = new Card("Spades", 3);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 5 and player card of 3 did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawFourTrue() {
		Card c1 = new Card("Clubs", 2);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 4);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 5 and player card of 4 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawFourFalse() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 4);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 6 and player card of 4 did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawFiveTrue() {
		Card c1 = new Card("Clubs", 2);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 5);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 5 and player card of 5 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawFiveFalse() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 5);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 6 and player card of 5 did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawSixTrue() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 6);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 6 and player card of 6 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawSixFalse() {
		Card c1 = new Card("Clubs", 4);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 6);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 7 and player card of 6 did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawSevenTrue() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 7);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 6 and player card of 7 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawSevenFalse() {
		Card c1 = new Card("Clubs", 4);
		Card c2 = new Card("Hearts", 3);
		Card pc = new Card("Spades", 7);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 7 and player card of 7 did not return false");
	 }
	 
	 
	 // NOTE: There is some ambiguity as far as when the banker should draw / not draw 
	 //       when the player card is 8. This is assuming the banker draws if their score
	 //       is below three and does not otherwise. This might change.
	 @Test
	 void testEvaluateBankerDrawEightTrue() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 10);
		Card pc = new Card("Spades", 8);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 3 and player card of 8 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawEightFalse() {
		Card c1 = new Card("Clubs", 10);
		Card c2 = new Card("Hearts", 4);
		Card pc = new Card("Spades", 8);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 4 and player card of 8 did not return false");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawNineTrue() {
		Card c1 = new Card("Clubs", 3);
		Card c2 = new Card("Hearts", 10);
		Card pc = new Card("Spades", 9);
		testHand.add(c1);
		testHand.add(c2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 3 and player card of 9 did not return true");
	 }
	 
	 @Test
	 void testEvaluateBankerDrawNineFalse() {
		Card c1 = new Card("Clubs", 10);
		Card c2 = new Card("Hearts", 4);
		Card pc = new Card("Spades", 9);
		testHand.add(c1);
		testHand.add(c2);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(testHand, pc), "Banker hand of 4 and player card of 9 did not return false");
	 }

}
