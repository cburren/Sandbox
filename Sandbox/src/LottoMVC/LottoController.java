package LottoMVC;


import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleButton;

public class LottoController {
	final private LottoModel model;
	final private LottoView view;
	
	
	int anzGew = 0;
	int anzTips = 0;
	boolean zusatzChoice = false;
	private ArrayList<Integer> userChoice = new ArrayList<Integer>();
	private ObservableList<ArrayList<Integer>> allTips = FXCollections.observableArrayList();
	
	public LottoController(LottoModel model, LottoView view) {
		this.model = model;
		this.view = view;
		
		for(int i = 0; i<view.hauptZahlen.length;i++){
			view.hauptZahlen[i].setOnAction(new clicker());
		}
		for(int i = 0; i<view.zusatzZahlen.length; i++){
			view.zusatzZahlen[i].setOnAction(new clickerZusatz());
		}
		
		view.btnRandom.setOnAction(new randomTip());
		view.btnTipp.setOnAction(new addTip());
		view.btnZiehung.setOnAction(new startZiehung());
		view.btnRemoveTip.setOnAction(new removeTip());
	}
	
	
	// ************************** EVENT HANDLERS ***************************************
	
	
	public class clicker implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			 ToggleButton sourceButton = (ToggleButton) event.getSource();
			 Boolean sourceSelected = sourceButton.isSelected();
			 
			 if(sourceSelected){ 
				 if(anzGew < view.MAXCHOICE){
					 anzGew++;
					 userChoice.add(Integer.parseInt(sourceButton.getText()));
					 updateChoiceProgress();
				 }
				 else{
					 view.lblStatus.setText("Schon alle Gewählt!");
					 sourceButton.setSelected(false);
				 }
			 }else{
				 view.lblStatus.setText("");
				 anzGew--;
				 userChoice.remove(userChoice.indexOf(Integer.parseInt(sourceButton.getText())));
				 updateChoiceProgress();
			 }
		}
		
	}
	
	
	public class clickerZusatz implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			if(view.tGroup.getSelectedToggle() == null)
				zusatzChoice = false;
			else
				zusatzChoice = true;
			updateChoiceProgress();
		}
	}
	
	public class addTip implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			if(anzGew >= view.MAXCHOICE){
				ToggleButton selectedZusatz = (ToggleButton) view.tGroup.getSelectedToggle();
				userChoice.add(Integer.parseInt(selectedZusatz.getText())*-1);
				Collections.sort(userChoice);
				allTips.add((ArrayList<Integer>) userChoice.clone());
				view.tipList.setItems(allTips);
				anzTips++;
				System.out.println(view.tipList.getItems());
				resetTipField();
			}else{
				view.lblStatus.setText("Noch nicht alle zahlen gewählt");
			}
		    
		}
	}
	
	public class startZiehung implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			
		}
	}
	
	public class removeTip implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			allTips.remove(view.tipList.getSelectionModel().getSelectedItem());
			view.tipList.setItems(allTips);
			
			anzTips--;
			updateChoiceProgress();
			
			for(ArrayList<Integer> i : allTips){
				for(int y : i){
					System.out.print(y+"/");
				}
				System.out.println();
				 
			 }
			System.out.println("--------------");
		}
	}	
	
	public class randomTip implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			//Zufällige Hauptzahlen generieren
			int random;
			boolean added;
			anzGew = 0;
			userChoice.clear();
			for(int i = 0; i<view.MAXCHOICE;i++){
				added = false;
				while(added==false){
					random = (int)(Math.random() * view.MAXNR + 1);
					if(userChoice.contains(random)==false){
						userChoice.add(random);
						added = true;
					}
				}
			}
			
			//Zufällige Zusatzzahl generieren
			random = (int)(Math.random() * view.MAXZUSATZ);
			view.zusatzZahlen[random].setSelected(true);
	        anzGew = view.MAXCHOICE;
			zusatzChoice = true;
			updateChoiceProgress();
			updateButtonState();
			
		}
		
	}
	
	public void updateChoiceProgress(){
		//Auswahl sortieren und Anzeige aktualisieren
		Collections.sort(userChoice); 
		 if(zusatzChoice)
			 view.lblOutOf.setText((anzGew+1)+" / "+(view.MAXCHOICE+1));
		 else
			 view.lblOutOf.setText((anzGew)+" / "+(view.MAXCHOICE+1));
		 
		 view.lblTips.setText(""+anzTips);
	}
	
	
	public void updateButtonState(){
		for(int i = 0; i<view.hauptZahlen.length;i++){
			view.hauptZahlen[i].setSelected(false);
		}
		
		for(int id:userChoice){
			view.hauptZahlen[id-1].setSelected(true);
		}
	}
	
	public void resetTipField(){
		userChoice.clear();
		anzGew = 0;
		view.tGroup.getSelectedToggle().setSelected(false);
		zusatzChoice = false;
		updateChoiceProgress();
		updateButtonState();
		
	}
	

	
}