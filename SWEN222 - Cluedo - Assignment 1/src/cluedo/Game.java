package cluedo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ui.Board;

public class Game {

	public static void main(String[] args) {
		int numPlayers = 0;
		
		numPlayers = readNumberOfPlayers(numPlayers);
		
		new Board(numPlayers);
	}

	/**
	 * Reads the number of players from the console
	 * @param numPlayers the field to change
	 * @return the integer that the user inputs
	 */
	private static int readNumberOfPlayers(int numPlayers) {
		System.out.println("Type number of players: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			numPlayers = br.read() - 48; //-48 to convert from ascii to the actual number
		}
		catch(IOException e) {
			System.out.println("Error reading from user");
		}
		
		if(numPlayers <= 2 || numPlayers > 7) {
			System.out.println("Please enter a valid number of players.");
			numPlayers = readNumberOfPlayers(numPlayers);
		}
		System.out.println(); //add a blank line
		return numPlayers;
	}
}
