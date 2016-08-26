package cluedo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cards.Card;
import square.Square;
import ui.Board;

public class PlayingLogic {

	static boolean gameWon = false;

	public static void startGame(Board board, ArrayList<Card> deck,
			Card[] solution) {

		printInstructions();

		Player[] players = board.players;
		int map[][] = board.getMap();

		// print the board to System.out
		printMap(map);

		System.out.println(); // add a blank space

		while (gameWon == false) {
			for (int i = 0; i < players.length; i++) {

				int playerNumber = i + 1;
				System.out.println("PLAYER " + playerNumber
						+ "'S TURN \n------");

				System.out.println("Your location is: \n("
						+ (players[i].position.getCol() + 1) + ", "
						+ (players[i].position.getRow() + 1) + ")");
				System.out.println("Your cards are:");
				players[i].showHand();
				System.out.println("\n------"); // add a blank space

				readCommand(solution, players, i);
			}
		}
		System.out.println("GAME IS OVER");

	}

	private static void readCommand(Card[] solution, Player[] players, int i) {
		System.out.println("Type desired command: ");

		String command = "";
		command = readCommand(command);

		if (checkValidCommand(command)) {
			System.out.println(); // add a blank space

			if (command.equalsIgnoreCase("roll")) {
				int dice = rollDice();
				System.out.println("The dice shows " + dice + "!");
				movePlayer(dice, players[i]);
			} else if (command.equalsIgnoreCase("suggestion")) {
				makeSuggestion(players, players[i], solution);
			} else if (command.equalsIgnoreCase("accusation")) {
				makeAccusation(players, players[i], solution);
			}
			// for debugging purposes
			else if (command.equalsIgnoreCase("skip")) {
				System.out.println("Turn skipped");
				
			} else {
				System.out.println("Shouldn't be able to get here!");
			}
			
		} else { //invalid command - write command again
			System.out.println("Please enter a valid command:");
			readCommand(solution, players, i);
		}
	}

	/**
	 * Asks the player for what person, weapon and room (in that order) that
	 * they want to make the accusation consisting of. Then checks if this
	 * accusation matches the solution. If the player is correct, changes
	 * gameWon boolean to true and game ends. If the player is not correct, the
	 * player losses and cannot keep playing.
	 * 
	 * @param players
	 *            array of all the players
	 * @param playersTurn
	 *            player who's taking the current turn
	 * @param solution
	 *            solution with the three cards to check against
	 */
	private static void makeAccusation(Player[] players, Player playersTurn,
			Card[] solution) {

		System.out.println("Type person, weapon, then room...");
		System.out.println("------");

		System.out.println("Person: ");
		String person = "";
		person = readCommand(person);
		Card personCard = new Card(person, "PlayerCard");

		System.out.println("Weapon: ");
		String weapon = "";
		weapon = readCommand(weapon);
		Card weaponCard = new Card(weapon, "WeaponCard");

		System.out.println("Room: ");
		String room = "";
		room = readCommand(room);
		Card roomCard = new Card(room, "RoomCard");

		Card[] accusation = { personCard, weaponCard, roomCard };

		if (solution[0].getName().equals(accusation[0].getName())
				&& solution[1].getName().equals(accusation[1].getName())
				&& solution[2].getName().equals(accusation[2].getName())) {
			System.out.println("You are correct! You won!");
			gameWon = true;
		} else {
			playersTurn.playerLost();
		}

	}

	private static void makeSuggestion(Player[] players, Player playersTurn,
			Card[] solution) {
		System.out.println("Type person, weapon, then room...");
		System.out.println("------");

		System.out.println("Person: ");
		String person = "";
		person = readCommand(person);
		Card personCard = new Card(person, "PlayerCard");

		System.out.println("Weapon: ");
		String weapon = "";
		weapon = readCommand(weapon);
		Card weaponCard = new Card(weapon, "WeaponCard");

		System.out.println("Room: ");
		String room = "";
		room = readCommand(room);
		Card roomCard = new Card(room, "RoomCard");

		// check which room the player is in, and if it matches the suggested
		// room
		if (playersTurn.position.equals(room) == false) {
			System.out.println("You must be in the room you suggest!");
		} else {
			for (int i = 0; i < players.length; i++) {
				if (players[i].getCard(0).getName()
						.equals(personCard.getName())) {
					System.out.println("Player " + (i + 1) + " refutes "
							+ person);
				}
				if (players[i].getCard(1).getName()
						.equals(weaponCard.getName())) {
					System.out.println("Player " + (i + 1) + " refutes "
							+ weapon);
				}
				if (players[i].getCard(2).getName().equals(roomCard.getName())) {
					System.out
							.println("Player " + (i + 1) + " refutes " + room);
				} else {
					System.out.println("Player " + (i + 1)
							+ " cannot refute any of the cards suggested");
				}
			}
		}
	}

	private static void movePlayer(int dice, Player player) {
		System.out
				.println("How many moves horizontally do you want to move? (+ for left, - for right)");
		int movesSide = 0;
		movesSide = Integer.parseInt(readCommand(Integer.toString(movesSide)));

		System.out
				.println("How many moves laterally do you want to move? (+ for up, - for down)");
		int movesUp = 0;
		movesUp = Integer.parseInt(readCommand(Integer.toString(movesUp)));

		if (Math.abs(movesSide) + Math.abs(movesUp) == dice) {
			System.out.println("Valid Move!");
			player.position.setCol(player.position.getCol() - movesSide);
			player.position.setRow(player.position.getRow() - movesUp);

			// check if move is on a valid sqaure type
			if (Square.isValidMove(player.position)) {
				System.out.println("Players position has been changed");
			}
			// if not, then move player back to where they were before
			// make the player move again
			else {
				player.position.setCol(player.position.getCol() + movesSide);
				player.position.setRow(player.position.getRow() + movesUp);
				System.out
						.println("Not valid move. Player has been moved back to previous position");

				movePlayer(dice, player);
			}

		} else {
			System.out.println("Invalid movement. Please try again...");
			movePlayer(dice, player);
		}
	}

	/**
	 * Rolls a artificial dice which uses Math.random() to get a random integer
	 * between 1 and 6
	 * 
	 * @return integer between 1 and 6
	 */
	public static int rollDice() {
		return 1 + (int) (Math.random() * 6); // random number between 1 and 6
	}

	/**
	 * Checks if the command is "roll", "suggestion", "accusation" Ignores case
	 * 
	 * @param command
	 *            command the player has entered
	 * @return true if it is one of the words, false if it is not
	 */

	private static boolean checkValidCommand(String command) {
		if (command.equalsIgnoreCase("roll")) {
			return true;
		} else if (command.equalsIgnoreCase("suggestion")) {
			return true;
		} else if (command.equalsIgnoreCase("accusation")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads a line and returns it as a string
	 * 
	 * @param command
	 *            the command to change
	 * @return the string entered by the player
	 */
	private static String readCommand(String command) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			command = br.readLine();
		} catch (IOException e) {
			System.out.println("Couldn't read command.");
		}

		return command;
	}

	/**
	 * Prints the instructions to System.out
	 */
	private static void printInstructions() {
		System.out.println("----GAME INSTRCUTIONS----");
		System.out.println("Check README for description of board");
		System.out.println("Write DICE to roll the dice");
		System.out
				.println("When in a room, write SUGGESTION to start to make a suggestion");
		System.out.println("Write ACCUSATION to start making an accusation");
		System.out.println("Play fair!\n");
		System.out.println("-------------------------");
	}

	/**
	 * Prints the map with the coordinates sorrounded by it
	 * 
	 * @param map
	 */
	private static void printMap(int[][] map) {
		int rowIndex = -1;

		while (rowIndex < 26) {
			if (rowIndex == -1) {
				System.out
						.println("      1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26");
				rowIndex++;
			} else if (rowIndex == 0) {
				System.out
						.println("------------------------------------------------------------------------------------------------------------");
				rowIndex++;
			} else {
				for (int[] row : map) {
					// print out coordinates
					if (rowIndex < 10) {
						System.out.print(rowIndex + "  | ");
						rowIndex++;
					} else if (rowIndex >= 10) {
						System.out.print(rowIndex + " | ");
						rowIndex++;
					}

					for (int i : row) {
						System.out.print(i);
						if (i < 10 && i != 0) {
							System.out.print(" "); // add a space after all
													// single
													// digests
													// except 0
						}
						if (i == 0) {
							System.out.print("0"); // after a 0, add another 0
													// to
													// make the
													// map look better
						}
						System.out.print("  "); // add a double space after each
												// number
					}
					System.out.println(); // skip line for the next row
				}
			}
		}
	}
}
