package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import square.Square;
import cards.Card;
import cards.Deck;
import cluedo.Player;

public class Board {

	int maxX = 26;
	int maxY = 27;
	private int map[][] = {
			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
					99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 41, 99, 99, 99, 99, 42,
					99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
			{ 99, 11, 11, 11, 11, 11, 11, 99, 00, 00, 00, 12, 12, 12, 12, 00,
					00, 00, 99, 13, 13, 13, 13, 13, 13, 99 },
			{ 99, 11, 11, 11, 11, 11, 11, 00, 00, 12, 12, 12, 12, 12, 12, 12,
					12, 00, 00, 13, 13, 13, 13, 13, 13, 99 },
			{ 99, 11, 11, 11, 11, 11, 11, 00, 00, 12, 12, 12, 12, 12, 12, 12,
					12, 00, 00, 13, 13, 13, 13, 13, 13, 99 },
			{ 99, 11, 11, 11, 11, 11, 11, 00, 00, 12, 12, 12, 12, 12, 12, 12,
					12, 00, 00, 13, 13, 13, 13, 13, 13, 99 },
			{ 99, 11, 11, 11, 11, 11, 11, 00, 00, 2, 12, 12, 12, 12, 12, 12,
					12, 00, 00, 00, 13, 13, 13, 13, 99, 99 },
			{ 99, 99, 11, 11, 11, 1, 11, 00, 00, 12, 12, 12, 12, 12, 12, 12,
					12, 00, 00, 00, 00, 00, 00, 00, 43, 99 },
			{ 99, 00, 00, 00, 00, 00, 00, 00, 00, 12, 2, 12, 12, 12, 12, 2, 12,
					00, 00, 00, 00, 00, 00, 00, 99, 99 },
			{ 99, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00,
					00, 00, 00, 14, 14, 14, 14, 14, 14, 99 },
			{ 99, 15, 15, 15, 15, 15, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00,
					00, 00, 00, 4, 14, 14, 14, 14, 14, 99 },
			{ 99, 15, 15, 15, 15, 15, 15, 15, 15, 00, 00, 99, 99, 99, 99, 99,
					00, 00, 00, 14, 14, 14, 14, 14, 14, 99 },
			{ 99, 15, 15, 15, 15, 15, 15, 15, 15, 00, 00, 99, 99, 99, 99, 99,
					00, 00, 00, 14, 14, 14, 14, 14, 14, 99 },
			{ 99, 15, 15, 15, 15, 15, 15, 15, 5, 00, 00, 99, 99, 99, 99, 99,
					00, 00, 00, 14, 14, 14, 14, 4, 14, 99 },
			{ 99, 15, 15, 15, 15, 15, 15, 15, 15, 00, 00, 99, 99, 99, 99, 99,
					00, 00, 00, 00, 00, 00, 00, 00, 99, 99 },
			{ 99, 15, 15, 15, 15, 15, 15, 15, 15, 00, 00, 99, 99, 99, 99, 99,
					00, 00, 00, 16, 16, 6, 16, 16, 99, 99 },
			{ 99, 15, 15, 15, 15, 15, 15, 5, 15, 00, 00, 99, 99, 99, 99, 99,
					00, 00, 16, 16, 16, 16, 16, 16, 16, 99 },
			{ 99, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 99, 99, 99, 99,
					00, 00, 6, 16, 16, 16, 16, 16, 16, 99 },
			{ 99, 44, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00,
					00, 00, 16, 16, 16, 16, 16, 16, 16, 99 },
			{ 99, 99, 00, 00, 00, 00, 00, 00, 00, 00, 17, 17, 07, 07, 17, 17,
					00, 00, 00, 16, 16, 16, 16, 16, 99, 99 },
			{ 99, 18, 18, 18, 18, 18, 18, 8, 00, 00, 17, 17, 17, 17, 17, 17,
					00, 00, 00, 00, 00, 00, 00, 00, 45, 99 },
			{ 99, 18, 18, 18, 18, 18, 18, 18, 00, 00, 17, 17, 17, 17, 17, 07,
					00, 00, 00, 00, 00, 00, 00, 00, 99, 99 },
			{ 99, 18, 18, 18, 18, 18, 18, 18, 00, 00, 17, 17, 17, 17, 17, 17,
					00, 00, 9, 19, 19, 19, 19, 19, 19, 99 },
			{ 99, 18, 18, 18, 18, 18, 18, 18, 00, 00, 17, 17, 17, 17, 17, 17,
					00, 00, 19, 19, 19, 19, 19, 19, 19, 99 },
			{ 99, 18, 18, 18, 18, 18, 18, 18, 00, 00, 17, 17, 17, 17, 17, 17,
					00, 00, 19, 19, 19, 19, 19, 19, 19, 99 },
			{ 99, 18, 18, 18, 18, 18, 18, 99, 46, 99, 99, 17, 17, 17, 17, 99,
					99, 00, 99, 19, 19, 19, 19, 19, 19, 99 },
			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
					99, 99, 99, 99, 99, 99, 99, 99, 99, 99 } };
	
	public Player[] players;
	public Square[][] squares;
	private Card[] solution;

	
	public Board(int numPlayers) {
		
		players = new Player[numPlayers];
		this.squares = new Square[maxY][maxX];
		
		for(int row = 0; row < maxY; row++) {
			for(int col = 0; col < maxX; col++) {
				if(map[row][col] == 00 || (map[row][col] >= 41 && map[row][col] <= 46)) {
					squares[row][col] = new square.WalkableSquare(row, col);
				}
				else if(map[row][col] < 10 && map[row][col] > 0) {
					squares[row][col] = new square.DoorSquare(row, col, "Room");
				}
				else if(map[row][col] > 10 && map[row][col] < 20) {
					squares[row][col] = new square.RoomSquare(row, col, "Room");
				}
				else {
					squares[row][col] = new square.NotPlayableSquare(row, col);
				}
			}
		}
		
		//create the deck of cards
		Deck deck = new Deck();
		
		ArrayList<String> pickedCharacters = new ArrayList<String>();
		
		for(int i=0; i < numPlayers ; i++) {
			String character = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int charNumber = i + 1;
			
			System.out.println("Player " +charNumber+ " write your name: ");

			try {
				character = br.readLine();
			}
			catch(IOException e) {
				System.out.println("Something went wrong reading line...");
			}
			
			int size = pickedCharacters.size();
			
			//first player to pick character
			if(i == 0) { 
				pickedCharacters.add(character);
			}
			//check if character has already been picked
			else if(characterAlreadyPicked(character, size, pickedCharacters)) {
				i--;
			}
			//if not, then just add the character
			else {
				pickedCharacters.add(character);
			}
		}
		
		Square startPos[] = getStartingSquare();
		
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player(startPos[i], pickedCharacters.get(i));
		}
		System.out.println(); //add a blank line
		
		//ready to start the game
		deck.startGame(this);
	}

	/**
	 * Checks weather the player name has already been picked
	 * @param character the player name to check
	 * @param size size of the pickedCharacters array
	 * @param pickedCharacters the characters that have already been picked
	 * @return true if the character has already been picked, false if not
	 */
	private boolean characterAlreadyPicked(String character, int size, ArrayList<String> pickedCharacters) {
		for(int j = 0; j < size; j++) {
			if(character.equals(pickedCharacters.get(j))) {
				System.out.println("Character already picked.");
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the starting sqaure of a character on the board
	 * @return the starting sqaure
	 */
	private Square[] getStartingSquare() {
		Square startPos[] = new Square[6];
		int count = 0;
		for(int i = 0; i < this.maxY; i++) {
			for(int j = 0; j < this.maxX; j++) {
				if(this.map[i][j] >= 41 && this.map[i][j] <= 46) {
					startPos[count] = this.squares[i][j];
					count++;
				}
			}
		}
		return startPos;
	}
	
	public Card[] getSolution() {
		return this.solution;
	}

	public void setSolution(Card[] solution) {
		this.solution = solution;
	}
	
	public int[][] getMap() {
		return this.map;
	}
	
	public int getHeight() {
		return maxY;
	}
	
	public int getWidth() {
		return maxX;
	}
}
