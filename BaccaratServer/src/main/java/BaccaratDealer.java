import java.util.ArrayList;
import java.util.Random;

/*
 * BaccaratServer - Project 3 for CS324, Fall Semester - 2020
 * Authors: Daniel LeVert, Adam Sammakia
 * UIN Adam:  659002242 Daniel: 673238527
 * 
 */

public class BaccaratDealer {
	ArrayList<Card> deck;
	
	public BaccaratDealer() {
		this.deck = new ArrayList<Card>();
	}
	
	// generateDeck
	//
	// Generates a standard deck of 52 cards (no jokers)
	// value is represented as an integer 1-13. 
	// It is intended that 1's are aces, 11's are jacks, 12's are queens 
	// and 13's are kings.
	// Deck is generated in order by suite (clubs, hearts, diomands, spades)
	// and then in ascending order by value.
	public void generateDeck() {
		// clear the old deck if it exists
		this.deck.clear();
		
		// create a list of suites we can iterate through
		ArrayList<String> suites = new ArrayList<String>();
		suites.add("Clubs");
		suites.add("Hearts");
		suites.add("Diamonds");
		suites.add("Spades");
		
		// Iterate through each suite, and then through values 1 - 13
		// to generate a 52 card deck
		for(String s : suites) {
			for(int i = 1; i <= 13; i++) {
				Card newCard = new Card(s, i);
				this.deck.add(newCard);
			}
		}
		
		return;
	}
	
	// shuffleDeck TODO: Should create a new 52 card deck (call generateDeck first)
	//
	// takes the current deck and shuffles it into new deck in a random order
	public void shuffleDeck() {
		// if deck is empty there is nothing to do, return
		generateDeck();
		
		Random rng = new Random(System.currentTimeMillis()); // set seed to current time for randomness
		ArrayList<Card> oldDeck = this.deck;
		ArrayList<Card> shuffledDeck = new ArrayList<Card>();
		for(int i = 0; i < 52; i++) {
			// generate a random int between 0 and 51 - i (since nextInt is exclusive on its upper bound)
			int newRand = rng.nextInt(52 - i); 

			shuffledDeck.add(oldDeck.get(newRand)); // add to shuffledDeck the card at index newRand for sortedDeck\
			// remove the card at the index that was just removed 
			// note that indices now range from 0 -> (51 - i) - 1
			oldDeck.remove(newRand); 
		}
		this.deck = shuffledDeck;
	}
	
	// dealHand
	//
	// deals two cards from the deck and removes them from the deck
	public ArrayList<Card> dealHand(){
		ArrayList<Card> result = new ArrayList<Card>();
		// if deck is empty return empty list
		if(this.deck.isEmpty()) {
			return result;
		}		
		// remove two elements from the deck and add them to result
		result.add(this.deck.get(0));
		this.deck.remove(0);
		result.add(this.deck.get(0));
		this.deck.remove(0);
		
		return result;
	}
	
	
	// draw one
	//
	// returns and removes a card from the deck
	public Card drawOne() {
		Card result = new Card("NONE", -1);
		if(this.deck.isEmpty()) {
			return result; //TODO: Is this the best way to deal with this edge case?
		}
		// grab info from top of the deck, 
		// assign those values to result 
		// and remove the top card from the deck
		result.suite = this.deck.get(0).suite;
		result.value = this.deck.get(0).value;
		this.deck.remove(0);
		return result;	
	}
	
	// deckSize
	//
	// return the current size of the deck
	public int deckSize() {
		return(this.deck.size());
	}
}

