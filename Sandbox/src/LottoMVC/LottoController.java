package LottoMVC;


import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class LottoController {
	final private LottoModel model;
	final private LottoView view;
	
	int anzGew = 0;
	private ArrayList<Integer> userChoice = new ArrayList<Integer>();
	
	public LottoController(LottoModel model, LottoView view) {
		this.model = model;
		this.view = view;
		
		for(int i = 0; i<view.toggleArray.length;i++){
			view.toggleArray[i].setOnAction(new clicker());
		}
		
		view.btnRandom.setOnAction(new randomTip());
	
	}
	
	
	public class clicker implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			 ToggleButton sourceTB = (ToggleButton) event.getSource();
			 Boolean sourceSelected = sourceTB.isSelected();
			 
			 if(sourceSelected){ 
				 if(anzGew < view.MAXCHOICE){
					 anzGew++;
					 userChoice.add(Integer.parseInt(sourceTB.getText()));
					 updateChoiceProgress();
				 }
				 else{
					 view.lblStatus.setText("Schon alle Gewählt!");
					 sourceTB.setSelected(false);
				 }
			 }else{
				 view.lblStatus.setText("");
				 anzGew--;
				 userChoice.remove(userChoice.indexOf(Integer.parseInt(sourceTB.getText())));
				 updateChoiceProgress();
			 }
		}
		
	}
	
	public class randomTip implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			generateRandomTip();
			
		}
		
	}
	
	
	public void generateRandomTip(){
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
		
		random = (int)(Math.random() * view.MAXZUSATZ);
		view.zusatzZahlen[random].setSelected(true);
	
		
		anzGew = view.MAXCHOICE;
		updateChoiceProgress();
		setButtonState();
	}
	
	public void updateChoiceProgress(){
		//Fortschirttsbalken und Anzeige aktualisieren 
		 //choiceAnz.setText(anzGew+" / "+MAXCHOICE);
		 //choiceProgress.setProgress((double)anzGew/MAXCHOICE);
		
		view.lblOutOf.setText(anzGew+" / "+(view.MAXCHOICE+1));
		 
		 Collections.sort(userChoice);
		 for(int i : userChoice){
			 System.out.print(i+"/");
			 
		 }
		 System.out.println(); 
	}
	
	
	public void setButtonState(){
		for(int i = 0; i<view.toggleArray.length;i++){
			view.toggleArray[i].setSelected(false);
		}
		
		for(int id:userChoice){
			view.toggleArray[id-1].setSelected(true);
		}
	}
	

	
}