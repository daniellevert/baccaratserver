import java.util.ArrayList;

/*
 * BaccaratServer - Project 3 for CS324, Fall Semester - 2020
 * Authors: Daniel LeVert, Adam Sammakia
 * UIN Adam:  659002242 Daniel: 673238527
 */

public class BaccaratGameLogic {
	
	/*
	 * Calculates the total of a given hand and returns the total.
	 * 10s and face cards are worth 0 points. Ace is worth 1 point.
	 * All other cards are worth their face value.
	 * Hands adding to >9 have the 1 in front removed.
	 */
	public static int handTotal(ArrayList<Card> hand) {
		int total = 0;
		Card card;
		
		for (int i = 0; i < hand.size(); i++) {
			card = hand.get(i);
			if (card.value < 10) {
				total += card.value;
			}
		}
		
		if (total > 9) {
			total = total % 10;
		}
		return total;
	}
	
	/*
	 * Determine which hand wins
	 * hand1 is player hand, hand2 is banker hand
	 */
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		if( handTotal(hand1) > handTotal(hand2)) {
			return("Player");
		}
		else if(handTotal(hand1) <  handTotal(hand2)) {
			return("Banker");
		}
		else {
			return("Draw");
		}
	}
	
	/*
	 * Determines if the banker should be dealt a third card
	 */
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		// need to do null checks separately to avoid NullPointer error
		if(playerCard == null) {
			if(handTotal(hand) <= 5) {
				return true;
			}
			else {
				return false;
			}
		}	
		
		// get adjusted card value
		// if card value is >= 10, adjusted card value is 0, otherwise it is just the card value
		int adjustedCardValue = 0;		
		if(playerCard.value < 10) {
			adjustedCardValue = playerCard.value;
		}
		
		// logic for real values for playerCard
		// NOTE: parentheses around || statements added - we were wrong, 
		//       the && takes precedent and tests were failing without them.
		if ((adjustedCardValue == 0 || adjustedCardValue == 1) && handTotal(hand) <= 3) {
			return true;
		}
		else if ((adjustedCardValue == 2 || adjustedCardValue == 3) && handTotal(hand) <= 4) {
			return true;
		}
		else if ((adjustedCardValue == 4 || adjustedCardValue == 5) && handTotal(hand) <= 5) {
			return true;
		}
		else if ((adjustedCardValue == 6 || adjustedCardValue == 7) && handTotal(hand) <= 6) {
			return true;
		}
		else if ((adjustedCardValue == 8 || adjustedCardValue == 9) && handTotal(hand) <= 3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Determines if the player should be dealt a third card
	 */
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		if (hand.size() == 2 && handTotal(hand) <= 5) {
			return true;
		}
		
		return false;
	}
}