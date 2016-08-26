package square;

/**
 * Represent a door on the board
 * @author Tobias
 *
 */
public class DoorSquare extends Square {

	private String room;
	
	public DoorSquare(int row, int col, String room) {
		super(row, col);
		this.setRoom(room);
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
}
