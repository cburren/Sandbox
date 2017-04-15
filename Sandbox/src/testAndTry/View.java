package testAndTry;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View {
	private final Model model;
	private final Stage stage;
	protected Label lblNumber;
	protected Button myButton;
	
	protected View(Stage stage, Model model){
		this.stage = stage;
		this.model = model;
		stage.setTitle("Programmtitel");
		
		GridPane pane = new GridPane();
		lblNumber = new Label();
		lblNumber.setText("Bitte den Button drücken!");
		lblNumber.setText(Integer.toString(model.getValue()));
		pane.add(lblNumber, 0, 0);
		
		myButton = new Button();
		myButton.setText("Sofort Drücken");
		pane.add(myButton, 0,1);
		
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
		stage.setScene(scene);
		
	}
	
	public void start (Stage stage){
		stage.show();
	}
	
	public void stop (){
		stage.hide();
	}
	public Stage getStage(){
		return stage;
	}
}
