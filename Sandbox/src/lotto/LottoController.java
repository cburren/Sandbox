package lotto;

import java.net.URL;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;

public class LottoController implements Initializable   {
	
	@FXML TilePane zahlenPane;
	@FXML Label choiceAnz;
	@FXML ProgressBar choiceProgress;
	@FXML Button btnRandom;
	
	//Definition der Spielgrösse
	private final int MAXNR = 42;
	private final int MAXCHOICE = 6;
	
	int anzGew = 0;
	int i;
	
	private ToggleButton[] toggleArray = new ToggleButton[MAXNR]; 		// Alle Toggels
	private ArrayList<Integer> userChoice = new ArrayList<Integer>();	// Auswahl des Spielers
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		updateChoiceProgress();
	
		for ( i = 0; i < MAXNR; i++ ){
			
			//Feld zum Auswählen der Zahlen instanzieren
			toggleArray[i] = new ToggleButton(""+(i+1));
			toggleArray[i].setId("no"+(i+1));
			toggleArray[i].getStyleClass().add("zahlenToggles");
			zahlenPane.getChildren().add(toggleArray[i]);
			
			//Definieren des ActionEvents falls die Zahl ausgewählt bzw. abgewählt wird
			toggleArray[i].setOnAction((event) -> {
				 ToggleButton sourceTB = (ToggleButton) event.getSource();
				 Boolean sourceSelected = sourceTB.isSelected();
				 
				 if(sourceSelected){ 
					 if(anzGew < MAXCHOICE){
						 anzGew++;
						 //System.out.println(sourceTB.getId()+" selected");
						 //System.out.println("Gewählt: "+anzGew);
						 
						 userChoice.add(Integer.parseInt(sourceTB.getText()));
						 updateChoiceProgress();
					 }
					 else{
						 System.out.println("Schon alle gewählt!");
						 sourceTB.setSelected(false);
					 }
				 }else{
					 anzGew--;
					 userChoice.remove(userChoice.indexOf(Integer.parseInt(sourceTB.getText())));
					 updateChoiceProgress();
					 //System.out.println(sourceTB.getId()+" deselected");
				 } 
		});
		}

	
	}
	
	public void updateChoiceProgress(){
		//Fortschirttsbalken und Anzeige aktualisieren 
		 choiceAnz.setText(anzGew+" / "+MAXCHOICE);
		 choiceProgress.setProgress((double)anzGew/MAXCHOICE);
		 
		 Collections.sort(userChoice);
		 for(int i : userChoice){
			 System.out.print(i+"/");
			 
		 }
		 System.out.println(); 
	};
	
	
	public void generateRandom(){
		int counter;
		int random;
		boolean added;
		anzGew = 0;
		userChoice.clear();
		for(counter = 0;counter<MAXCHOICE;counter++){
			added = false;
			while(added==false){
				random = (int)(Math.random() * MAXNR + 1);
				if(userChoice.contains(random)==false){
					userChoice.add(random);
					added = true;
				}
			}
			
			
		}
		
		anzGew = MAXCHOICE;
		updateChoiceProgress();
		setToggles();
	}
	
	public void setToggles(){
		for(int i = 0; i<toggleArray.length;i++){
			toggleArray[i].setSelected(false);
		}
		
		for(int id:userChoice){
			toggleArray[id-1].setSelected(true);
		}
	}

	

}
