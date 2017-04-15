package calculator;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class CalculatorView {
	final private CalculatorModel model;
	final private Stage stage;
	
	public TextField anzeige;
	protected Button digits[] = new Button[10];
	
	protected CalculatorView(Stage stage, CalculatorModel model) {
		this.stage = stage;
		this.model = model;
		
		stage.setTitle("Calculator");
		
		BorderPane root = new BorderPane();
		root.setTop(anzeige);
		GridPane gPane = new GridPane();
		root.setCenter(gPane);
		
		for (int i = 0; i < digits.length; i++) {
			digits[i] = new Button(Integer.toString(i));
		}
		
		
		// special add for button 0
		gPane.add(digits[0], 0, 3, 3, 1);
				
		// add all other digits
		for (int i = 1; i < digits.length; i++) {
			gPane.add(digits[i], (i-1) % 3, 2 - (i-1) / 3);
		}
		
		// Configure grid columns and rows to resize to available space
				// See "GridPane" in the JavaFX API, section "Percentage Sizing"
				ColumnConstraints cc = new ColumnConstraints();
				cc.setPercentWidth(25);
				gPane.getColumnConstraints().addAll(cc, cc, cc, cc);
				RowConstraints rc = new RowConstraints();
				rc.setPercentHeight(25);
				gPane.getRowConstraints().addAll(rc, rc, rc, rc);
		
		Label helloWorld = new Label("Hello, World!");
	
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Calculator_style.css").toExternalForm());
		stage.setScene(scene);
	}

	public void start() {
		stage.show();
	}
	
	/**
	 * Stopping the view - just make it invisible
	 */
	public void stop() {
		stage.hide();
	}
}