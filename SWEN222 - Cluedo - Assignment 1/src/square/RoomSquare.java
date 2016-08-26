package square;

/**
 * Represents a square that is a room
 * @author Tobias
 *
 */
public class RoomSquare extends Square {

	private String room;
	
	public RoomSquare(int row, int col, String room) {
		super(row, col);
		this.room = room;
	}

	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
}
