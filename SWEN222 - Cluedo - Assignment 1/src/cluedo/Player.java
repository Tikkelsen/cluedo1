package cluedo;

import java.util.ArrayList;

import cards.Card;
import square.Square;

public class Player {

	Square position;
	String charName;
	ArrayList<Card> hand;
	boolean isPlaying;

	public Player(Square position, String charName) {
		this.position = position;
		this.charName = charName;
		this.hand = new ArrayList<Card>();
		isPlaying = true;
	}

	/**
	 * Prints the cards in the players hands with commas inbetween each of them
	 */
	public void showHand() {
		String seperator = "";
		for (Card card : hand) {
			System.out.print(seperator + card.toString());
			seperator = ", ";
		}
		System.out.println();
	}

	public void setHand(Card[] hand) {
		for (Card card : hand) {
			this.hand.add(card);
		}
	}

	public void addToHand(Card card) {
		this.hand.add(card);
	}

	public Card getCard(int index) {
		return this.hand.get(index);
	}

	public void playerLost() {
		this.isPlaying = false;
		System.out.println("The player " + this.charName
				+ " has lost the game.");
	}

	public int sizeOfHand() {
		return this.hand.size();
	}
}
