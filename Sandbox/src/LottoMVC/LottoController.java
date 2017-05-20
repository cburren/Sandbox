package LottoMVC;


import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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
		view.btnNext.setOnAction(new StartChoice());
		view.btnNew.setOnAction(new StartChoice());
		
		view.tipList.setCellFactory(new Callback<ListView<ArrayList>, ListCell<ArrayList>>() {

            @Override
            public ListCell<ArrayList> call(ListView<ArrayList> param) {
                ListCell<ArrayList> cell = new ListCell<ArrayList>() {

                    @Override
                    protected void updateItem(ArrayList item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                        	String temp = "";
                   
                        	for(int i = 1;i<item.size();i++){
                        		temp += item.get(i)+", ";
                        	}
                        	temp += "   +"+(Integer)item.get(0)*-1;
                            setText(temp);
                         
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });
	}
	
	
	// ************************** EVENT HANDLERS ***************************************
	
	
	public class clicker implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			view.lblStatus.setText("");
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
			view.lblStatus.setText("");
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
			view.lblStatus.setText("");
			if(anzTips<model.MAXTIP){
				
				if(anzGew >= view.MAXCHOICE){
					ToggleButton selectedZusatz = (ToggleButton) view.tGroup.getSelectedToggle();
					userChoice.add(Integer.parseInt(selectedZusatz.getText())*-1);
					Collections.sort(userChoice);
					allTips.add((ArrayList<Integer>) userChoice.clone());
					view.tipList.setItems(allTips);
					anzTips++;
					//System.out.println(view.tipList.getItems());
					resetTipField();
				}else{
					view.lblStatus.setText("Noch nicht alle zahlen gewählt");
				}
			    model.bet += model.tipPrice;
			}else{
				view.lblStatus.setText("Keine weitern Tips mehr möglich!");
			}
		    updateChoiceProgress();
		}
	}
	
	public class startZiehung implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			view.lblStatus.setText("");
			view.lblTitle.setText("Resultate");
			
			
			view.bPane.setCenter(view.gpZiehung);
			view.bPane.setAlignment(view.gpZiehung, Pos.CENTER);
			
			
			
			for(ArrayList<Integer> list:allTips){
			 view.addTipButtons(list);
			}
			model.generateLuckyNumbers();
			view.showLuckyNumbers(model.luckyNumbers);
			model.compareNumbers(allTips);
			view.showWinResults();
			
		}
	}
	
	public class removeTip implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			view.lblStatus.setText("");
			
			if(view.tipList.getSelectionModel().getSelectedItem() != null){
			allTips.remove(view.tipList.getSelectionModel().getSelectedItem());
			view.tipList.setItems(allTips);
			anzTips--;
			updateChoiceProgress();
			}else{
				view.lblStatus.setText("Bitte Tipp auswählen!");
			}
			model.bet -= model.tipPrice;
			
		}
	}	
	
	public class randomTip implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			view.lblStatus.setText("");
			
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
	
	public class StartChoice implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			view.lblStatus.setText("");
			view.lblTitle.setText("Auswahl");
			
			view.bPane.setCenter(view.gpCenter);
			anzGew = 0;
			userChoice.clear();
			allTips.clear();
			
		}
		
	}
	
	public void updateChoiceProgress(){
		//Auswahl sortieren und Anzeige aktualisieren
		Collections.sort(userChoice); 
		
		//Infoseite
		 if(zusatzChoice)
			 view.lblOutOf.setText((anzGew+1)+" / "+(view.MAXCHOICE+1));
		 else
			 view.lblOutOf.setText((anzGew)+" / "+(view.MAXCHOICE+1));
		 
		 view.lblTips.setText(anzTips+" / "+model.MAXTIP);
		 view.lblMoney.setText(model.bet+".- CHF");
		 
		 //Tip Button
		 if(anzGew <view.MAXCHOICE || zusatzChoice == false)
			 view.btnTipp.setDisable(true);
		 else
			 view.btnTipp.setDisable(false);
		 //Remove Tip & Ziehung starten Button
		 if(anzTips>0){
			 view.btnRemoveTip.setVisible(true);
			 view.btnZiehung.setDisable(false);
		 }else{
			 view.btnRemoveTip.setVisible(false);
			 view.btnZiehung.setDisable(true);
		 }
			 
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