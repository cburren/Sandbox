package testAndTry;

import javafx.application.Application;
import javafx.stage.Stage;

public class Programm extends Application{

	private Model model;
	private View view;
	private Controller controller;
	
	public static void main(String[] args) {
		launch();

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new Model();
		view = new View(primaryStage, model);
		controller = new Controller(view, model);
		
		view.start(primaryStage);
	}
	
	public void stop(){
		if (view != null)
			view.stop();
	}

}
