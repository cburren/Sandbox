package testAndTry;

public class Model {
	private int value;
	
	protected Model (){
		value = 0;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void incrementValue(){
		value++;
	}
	

}
