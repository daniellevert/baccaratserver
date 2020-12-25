import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestBaccaratDealer {
	BaccaratDealer testDealer;
	
	@BeforeEach
	void instantiateDealer() {
		testDealer = new BaccaratDealer();
	}
	
	// test Constructor, deck should be initialized to empty
	@Test
	void testBaccaratDealerConstructor() {
		assertTrue(testDealer.deck.isEmpty(), "deck not empty after initialization");
	}
	
	//  test generateDeck
	// deck should not be empty
	@Test
	void testBaccaratDealerGenerateDeckNotEmpty() {
		testDealer.generateDeck();	
		assertFalse(testDealer.deck.isEmpty(), "deck empty after call to generateDeck");
	}
	
	// deck should have 52 cards
	@Test
	void testBaccaratDealerGenerateDeckLen52() {
		testDealer.generateDeck();	
		assertEquals(52, testDealer.deck.size(), "deck size not 52 after call to generate deck");
	}
	
	
	// test that all values are in the deck
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerGenerateDeckValuesClubs(int value) {
		testDealer.generateDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Clubs") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerGenerateDeckValuesHearts(int value) {
		testDealer.generateDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Hearts") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerGenerateDeckValuesDiamonds(int value) {
		testDealer.generateDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Diamonds") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerGenerateDeckValuesSpades(int value) {
		testDealer.generateDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Spades") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	// test shuffleDeck
	// empty deck should remain empty after shuffle
	@Test
	void testBaccaratDealerShuffleDeckEmpty() {
		testDealer.shuffleDeck();
		assertFalse(testDealer.deck.isEmpty(), "empty deck stayed empty after shuffleDeck");
	}
//	
//	// shuffled non-empty deck should not be empty
	@Test
	void testBaccaratDealerShuffleDeckNotEmpty() {
		testDealer.generateDeck();
		testDealer.shuffleDeck();
		assertFalse(testDealer.deck.isEmpty(), "non-empty deck empty after shuffleDeck");
		
	}
	
	// shuffled deck should have 52 cards
	@Test
	void testBaccaratDealerShuffleDeckSize() {
		testDealer.generateDeck();
		testDealer.shuffleDeck();
		assertEquals(52, testDealer.deck.size(), "deck size not 52 after shuffle");
	}
	
	// test that all values are in the deck
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerShuffleDeckValuesClubs(int value) {
		testDealer.generateDeck();
		testDealer.shuffleDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Clubs") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerShuffleDeckValuesHearts(int value) {
		testDealer.generateDeck();
		testDealer.shuffleDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Hearts") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerShuffleDeckValuesDiamonds(int value) {
		testDealer.generateDeck();
		testDealer.shuffleDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Diamonds") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
	void testBaccaratDealerShuffleDeckValuesSpades(int value) {
		testDealer.generateDeck();
		testDealer.shuffleDeck();
		boolean testCardFound = false;
		for(Card c : testDealer.deck) {
			if(c.value == value && c.suite == "Spades") {
				testCardFound = true;
				break;
			}
		}
		assertTrue(testCardFound, "An expected value was not found in the deck");
	}
	
	// test that new and old and new lists are not equal
	// NOTE: This test can technically fail, even when things are working properly, 
	//       however the odds of that happening are so low that I (Adam) am not worried about it
	@Test
	void testBaccaratDealerShuffleDeckNotEqual() {
		testDealer.generateDeck();
		ArrayList<Card> oldDeck = new ArrayList<Card>(testDealer.deck);
		testDealer.shuffleDeck();
		boolean differentCardFound = false;
		for(int i = 0; i < 52; i++) {
			String oldSuite = oldDeck.get(i).suite;
			int oldVal = oldDeck.get(i).value;
			String newSuite = testDealer.deck.get(i).suite;
			int newVal = testDealer.deck.get(i).value;
			if(!(oldSuite.equals(newSuite)) && oldVal != newVal) {
				differentCardFound = true;
				break;
			}
		}
		assertTrue(differentCardFound, "decks were the same after shuffling (very unlikely to happen if working properly");
	}
	// Test dealHand
	// deal hand on deck should be null
	@Test
	void testBaccaratDealerDealHandEmpty() {
		ArrayList<Card> hand = new ArrayList<Card>(testDealer.dealHand());
		assertTrue(hand.isEmpty(), "deal hand did not return an empty list for an empty deck");
	}
	
	// deal hand should have size 2
	@Test
	void testBaccaratDealerDealHandSize() {
		testDealer.generateDeck();
		ArrayList<Card> hand = testDealer.dealHand();
		assertEquals(2, hand.size(), "hand size not two");
	}
	
	// first element should be ace of clubs
	@Test
	void testBaccaratDealerDealHandFirstElemValue() {
		testDealer.generateDeck();
		ArrayList<Card> hand = testDealer.dealHand();
		assertEquals(1, hand.get(0).value, "first elem of hand not an ace (unshuffled deck)");
	}
	
	@Test
	void testBaccaratDealerDealHandFirstElemSuite() {
		testDealer.generateDeck();
		ArrayList<Card> hand = testDealer.dealHand();
		assertEquals("Clubs", hand.get(0).suite, "first elem of hand not clubs (unshuffled deck)");
	}
	
	// second element should be 2 of clubs
	@Test
	void testBaccaratDealerDealHandSecondElemValue() {
		testDealer.generateDeck();
		ArrayList<Card> hand = testDealer.dealHand();
		assertEquals(2, hand.get(1).value, "second elem of hand not an ace (unshuffled deck)");
	}
	
	@Test
	void testBaccaratDealerDealHandSecondElemSuite() {
		testDealer.generateDeck();
		ArrayList<Card> hand = testDealer.dealHand();
		assertEquals("Clubs", hand.get(1).suite, "second elem of hand not clubs (unshuffled deck)");
	}
	
	//make sure elements are removed from deck
	@Test
	void testBaccaratDealerDealHandREmoveFromDeckSize() {
		testDealer.generateDeck();
		ArrayList<Card> hand = testDealer.dealHand();
		assertEquals(50, testDealer.deck.size());
	}
	
	@Test
	void testBaccaratDealerDealHandREmoveFromDeckValues() {
		testDealer.generateDeck();
		ArrayList<Card> hand = testDealer.dealHand();
		assertEquals(3, testDealer.deck.get(0).value, "first element in deck not 3 after deal hand (unshuffled deck)");
	}
	
	// Test drawOne
	@Test
	void testBaccaratDealerDrawOneEmpty() {
		Card c = testDealer.drawOne();
		//NOTE: This is how this has been handled for the time being, could possibly change
		assertEquals("NONE", c.suite, "an unexpected card was return from drawOne on an empty list");
	}
	
	@Test 
	void testBaccaratDealerDrawOneNonEmptyValue() {
		testDealer.generateDeck();
		Card c = testDealer.drawOne();
		assertEquals(1, c.value, "drawOne did not return an ace (unshuffled deck)");		
	}
	
	@Test 
	void testBaccaratDealerDrawOneNonEmptySuite() {
		testDealer.generateDeck();
		Card c = testDealer.drawOne();
		assertEquals("Clubs", c.suite, "drawOne did not return a club (unshuffled deck)");		
	}
	
	//test that element is removed
	@Test
	void testBaccaratDealerDrawOneSize() {
		testDealer.generateDeck();
		Card c = testDealer.drawOne();
		assertEquals(51, testDealer.deck.size(), "size of deck not changed after drawOne");
	}
	
	@Test
	void testBaccaratDealerDrawOneTopOfDeck() {
		testDealer.generateDeck();
		Card c = testDealer.drawOne();
		assertEquals(2, testDealer.deck.get(0).value, "top of deck not a 2 after drawOne (unshuffled deck)");
	}
	
	// Test deck Size
	@Test
	void testBaccaratDealerDeckSizeEmptyDeck() {
		assertEquals(0, testDealer.deckSize(), "empty deck size not 0");
	}
	
	@Test
	void testBaccaratDealerDeckSizeFullDeck() {
		testDealer.generateDeck();
		assertEquals(52, testDealer.deckSize(), "full deck size not 52");
	}
	
	@Test
	void testBaccaratDealerDeckSizeAfterDraw() {
		testDealer.generateDeck();
		testDealer.drawOne();
		assertEquals(51, testDealer.deckSize(), "deck size not 51 after draw");

	}
	
	@Test
	void testBaccaratDealerDeckSizeAfterDeal() {
		testDealer.generateDeck();
		testDealer.dealHand();
		assertEquals(50, testDealer.deckSize(), "deck size not 50 after deal");

	}
	
}
