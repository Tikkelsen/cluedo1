package square;

public abstract class Square {

	private int row;
	private int col;
	
	public Square(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public static boolean isValidMove(Square square) {
		if(square instanceof WalkableSquare) {
			return true;
		}
		else if(square instanceof DoorSquare) {
			return true;
		}
		else if(square instanceof RoomSquare) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return ("Row: " +getRow()+ ", Col: " +getCol());
	}
}
