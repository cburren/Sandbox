package testAndTry;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Controller {
	
	private final View view;
	private final Model model;
	
	
	
	public Controller(View view, Model model){
	
		this.view = view;
		this.model = model;
		
		view.myButton.setOnAction(new EventHandler<ActionEvent>() {
		
		public void handle(ActionEvent event){
			model.incrementValue();
			String newText = Integer.toString(model.getValue());
			view.lblNumber.setText(newText);
		}
		});
		
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				view.stop();
				Platform.exit();
			}
			
		});
		
		
	}

}
