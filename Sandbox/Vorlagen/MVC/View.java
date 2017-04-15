package MVC;

import javafx.stage.Stage;

public class View {
	private final Model model;
	private final Stage stage;
	
	protected View(Stage stage, Model model){
		this.stage = stage;
		this.model = model;
		
	}
	
	public void start (Stage stage){
		stage.show();
	}
	
	public void stop (){
		stage.hide();
	}
}
