package application;

public class PlayedCardMsg extends Message {
	private String actionPower;
	private static final long serialVersionUID = 8645385746176841907L;

	public PlayedCardMsg(Integer firstNumber, Integer secondNumber, String actionPower) {
		super(firstNumber, secondNumber);
		 this.actionPower = actionPower;
		
		
	}

	public String getActionPower() {
		return actionPower;
	}

	public void setActionPower(String actionPower) {
		this.actionPower = actionPower;
	}

	
	
}
