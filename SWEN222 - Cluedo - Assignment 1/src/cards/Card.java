package cards;

public class Card {
	
	private String name;
	private String type;
	
	public Card(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean equals(Card card) {
		if(this.name == card.getName()) {
			return true;
		}
		else {
			return false;
		}
	}
}
