package cards;

import java.util.ArrayList;
import java.util.Collections;

import cluedo.PlayingLogic;
import ui.Board;

public class Deck {

	public String[] playerCards = { "Miss Scarlett", "Colonel Mustard",
			"Mrs. White", "The Reverend Green", "Mrs. Peacock",
			"Professor Plum" };

	public String[] weaponCards = { "Candlestick", "Dagger", "Lead Pipe",
			"Revolver", "Rope", "Spanner" };

	public String[] roomCards = { "Kitchen", "Ball Room", "Conservatory",
			"Dinning Room", "Billiard Room", "Library", "Lounge", "Hall",
			"Study" };

	Card cards[];

	public Deck() {
		cards = new Card[21]; // total number of cards

		// add all the player cards to the deck
		for (int i = 0; i < playerCards.length; i++) {
			cards[i] = new Card(playerCards[i], "PlayerCard");
		}

		// add all the weapon cards to the deck - start i from end of
		// playerCards
		for (int i = playerCards.length; i < playerCards.length
				+ weaponCards.length; i++) {
			cards[i] = new Card(weaponCards[i - playerCards.length],
					"WeaponCard");
		}

		// add all the room cards to the deck - start i from end of playerCards
		// + end of weaponCards
		for (int i = playerCards.length + weaponCards.length; i < playerCards.length
				+ weaponCards.length + roomCards.length; i++) {
			cards[i] = new Card(roomCards[i - playerCards.length
					- weaponCards.length], "RoomCard");
		}
	}

	public void startGame(Board board) {
		
		// make deck of all cards
		ArrayList<Card> deck = new ArrayList<Card>();
		for (Card card : cards) {
			deck.add(card);
		}

		// get a random solution - one of each card from the deck
		int a = (int) (Math.random() * 6); // playerCard = card 1 to 6
		int b = playerCards.length + (int) (Math.random() * weaponCards.length);
		int c = playerCards.length + weaponCards.length
				+ (int) (Math.random() * roomCards.length);
		
		//to avoid c being greater than 19
		//something wrong with my c calculation above
		if(c >= 19) {
			startGame(board);
		}
		
		
		// make and set the solution consist of these 3 cards
		Card solution[] = {deck.get(a), deck.get(b), deck.get(c)};
		board.setSolution(solution);
		
		// remove the solution cards from the deck
		deck.remove(a);
		deck.remove(b);
		deck.remove(c);

		// shuffle the deck
		Collections.shuffle(deck);

		// deal rest of cards equally to all players
		for (int i = 0; i < deck.size(); i++) {
			board.players[i % board.players.length].addToHand(deck.get(i));
			deck.remove(i);
		}
		
		//show remaining cards
		System.out.println("Cards not given to any of the players:");
		System.out.println("------------------");
		for(Card card : deck) {
			System.out.println(card.toString());
		}
		System.out.println();
		
		PlayingLogic.startGame(board, deck, solution);
	}
}
