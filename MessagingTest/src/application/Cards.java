package application;

import java.io.Serializable;

public class Cards implements Serializable{

	private String cardname;
	private String type;
	private int value;
	
	private static final long serialVersionUID = -5399605122490342229L;
	
	public Cards (String cardname, String type, int value){
		
		this.cardname = cardname;
		this.type = type;
		this.value = value;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
