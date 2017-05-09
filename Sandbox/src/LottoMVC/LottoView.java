package LottoMVC;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LottoView {
	final private LottoModel model;
	final private Stage stage;
	
	final int MAXNR = 42;
	final int MAXZUSATZ = 6;
	final int MAXCHOICE = 6;
	final int MAXCHOICEZUSATZ = 1;
	
	ToggleButton[] toggleArray = new ToggleButton[MAXNR];
	ToggleButton[] zusatzZahlen = new ToggleButton[MAXZUSATZ];
	
	ToggleGroup tGroup;
	
	Label lblStatus;
	Label lblOutOf;
	Label lblOutOfText;
	Label lblTips;
	Label lblTipsText;
	Label lblMoney;
	Label lblMoneyText;
	
	Button btnRandom;
	Button btnTipp;
	Button btnZiehung;
	
	
	public LottoView(Stage stage, LottoModel model){
		this.model = model;
		this.stage = stage;
		
		BorderPane bPane = new BorderPane();
		VBox vbCenter = new VBox();
		vbCenter.setAlignment(Pos.CENTER);
		vbCenter.setSpacing(30);
		vbCenter.setMaxWidth(800);
		
		TilePane tPane = new TilePane();
		tPane.setAlignment(Pos.CENTER);
		tPane.setMaxSize(280, 700);
		tPane.setMinSize(260,150);
		
		TilePane tPaneZusatz = new TilePane();
		tPaneZusatz.setAlignment(Pos.CENTER);
		tPaneZusatz.setMaxSize(280, 700);
		
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(15);
		
		btnRandom = new Button("Zufällig");
		btnTipp = new Button("Tipp abgeben");
		btnZiehung = new Button ("Ziehung starten");
		
		buttonBox.getChildren().addAll(btnRandom, btnTipp, btnZiehung);
		
		lblStatus = new Label("Status kommt hier");
		
		
		vbCenter.getChildren().addAll(tPane, tPaneZusatz, buttonBox,lblStatus);
		
		//********************** LINKS ************************
		
		VBox vbLeft = new VBox();
		vbLeft.setAlignment(Pos.CENTER);
		lblOutOf = new Label ("" + MAXCHOICE);
		lblOutOfText = new Label("Zahlen gewählt:");
		lblTips = new Label ("2");
		lblTipsText = new Label ("Gesetzte Tipps:");
		lblMoney = new Label ("2500.- CHF");
		lblMoneyText = new Label ("Kontostand:");
		
		
		lblOutOf.setStyle("-fx-font-size: 22px;");
		lblTips.setStyle("-fx-font-size: 22px;");
		lblMoney.setStyle("-fx-font-size: 22px;");
		vbLeft.getChildren().addAll(lblOutOfText, lblOutOf, lblTipsText, lblTips, lblMoneyText, lblMoney);
		
		GridPane gpCenter = new GridPane();
		gpCenter.setAlignment(Pos.CENTER);
		gpCenter.setConstraints(vbLeft, 0, 1);
		gpCenter.setConstraints(vbCenter, 1, 1);
		
		ColumnConstraints col1 = new ColumnConstraints();
	    col1.setPercentWidth(33);
	    col1.setHalignment(HPos.LEFT);
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setPercentWidth(33);
	    ColumnConstraints col3 = new ColumnConstraints();
	    col3.setPercentWidth(33);
	    gpCenter.getColumnConstraints().addAll(col1,col2,col3);
		
		gpCenter.getChildren().addAll(vbLeft,vbCenter);
		
		
		
		bPane.setAlignment(gpCenter, Pos.CENTER);
		bPane.setCenter(gpCenter);
		
		for (int i = 0; i < MAXNR; i++){
			//Feld zum Auswählen der Zahlen instanzieren
			toggleArray[i] = new ToggleButton(""+(i+1));
			toggleArray[i].setId("no"+(i+1));
			toggleArray[i].getStyleClass().add("zahlenToggles");
			tPane.getChildren().add(toggleArray[i]);
		}
		
		tGroup = new ToggleGroup();
		for(int i = 0; i < MAXZUSATZ; i++){
			zusatzZahlen[i] = new ToggleButton(""+(i+1));
			zusatzZahlen[i].setId("no"+(i+1));
			zusatzZahlen[i].getStyleClass().add("zahlenToggles");
			tPaneZusatz.getChildren().add(zusatzZahlen[i]);
			zusatzZahlen[i].setToggleGroup(tGroup);
		}
		
		Scene scene = new Scene(bPane, 800, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		stage.setScene(scene);
		stage.setTitle("Lotto");
	}
	
	public void generateButtons(int anz){
		for(int i = 0; i<anz; i++){
			
		}
	}
	
	public void start() {
		stage.show();
	}

	public void stop() {
		stage.hide();
	}
}
