package calculator;

import javafx.application.Application;
import javafx.stage.Stage;

public class Calculator extends Application {
	
	private CalculatorModel model;
	private CalculatorView view;
	private CalculatorController controller;

	public static void main(String[] args) {
		launch();
	}
	
	/**
	 * Note the dependencies between model, view and controller. Additionally,
	 * the view needs the primaryStage created by JavaFX.
	 */
	@Override
	public void start(Stage primaryStage) {
		model = new CalculatorModel();
		view = new CalculatorView(primaryStage, model);
		controller = new CalculatorController(model, view);
		
		// Display the GUI after all initialization is complete
		view.start();
	}

	/**
	 * The stop method is the opposite of the start method. It provides an
	 * opportunity to close down the program gracefully, when the program has
	 * been closed.
	 */
	@Override
	public void stop() {
		if (view != null) view.stop();
	}
}