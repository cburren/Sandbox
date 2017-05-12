package LottoMVC;

import com.sun.prism.paint.Color;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
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
	
	ToggleButton[] hauptZahlen = new ToggleButton[MAXNR];
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
	Button btnRemoveTip;
	
	BorderPane bPane;
	GridPane gpCenter;
	
	TilePane tPane;
	TilePane tPaneZusatz;
	
	VBox vbRight;
	VBox vbCenter;
	VBox vbLeft;
	
	HBox buttonBox;
	
	public Effect myEffect;
	
	
	ListView tipList;
	
	public LottoView(Stage stage, LottoModel model){
		this.model = model;
		this.stage = stage;
	    myEffect = new BoxBlur(10, 10, 3);
		
		bPane = new BorderPane();
		
		// ************************** MITTE  ******************************
		
		vbCenter = new VBox();
		vbCenter.setAlignment(Pos.CENTER);
		vbCenter.setSpacing(30);
		
		tPane = new TilePane();
		tPane.setAlignment(Pos.CENTER);
		tPane.setMaxSize(280, 700);
		tPane.setMinSize(260,150);
		
		tPaneZusatz = new TilePane();
		tPaneZusatz.setAlignment(Pos.CENTER);
		tPaneZusatz.setMaxSize(280, 700);
		
		buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(15);
		
		btnRandom = new Button("Zufällig");
		btnTipp = new Button("Tipp abgeben");
		btnZiehung = new Button ("Ziehung starten");
		btnTipp.setDisable(true);
		btnZiehung.setDisable(true);
		
		
		buttonBox.getChildren().addAll(btnRandom, btnTipp, btnZiehung);
		
		lblStatus = new Label("Status kommt hier");
		
		
		vbCenter.getChildren().addAll(tPane, tPaneZusatz, buttonBox,lblStatus);
		
		//********************** LINKS ************************
		
		vbLeft = new VBox();
		vbLeft.setAlignment(Pos.CENTER);
		lblOutOf = new Label ("0 / " + MAXCHOICE);
		lblOutOfText = new Label("Zahlen gewählt:");
		lblTips = new Label ("0");
		lblTipsText = new Label ("Gesetzte Tipps:");
		lblMoney = new Label ("2500.- CHF");
		lblMoneyText = new Label ("Kontostand:");
		
		
		lblOutOf.setStyle("-fx-font-size: 22px;");
		lblTips.setStyle("-fx-font-size: 22px;");
		lblMoney.setStyle("-fx-font-size: 22px;");
		vbLeft.getChildren().addAll(lblOutOfText, lblOutOf, lblTipsText, lblTips, lblMoneyText, lblMoney);
		
		
		
		//********************* RECHTS ********************************
		vbRight = new VBox();
		vbRight.setAlignment(Pos.CENTER);
		tipList = new ListView();
		vbRight.setSpacing(40);
		btnRemoveTip = new Button("Tipp entfernen");
		btnRemoveTip.setVisible(false);
		vbRight.getChildren().addAll(btnRemoveTip,tipList);
		
		
		gpCenter = new GridPane();
		gpCenter.setAlignment(Pos.CENTER);
		gpCenter.setConstraints(vbLeft, 0, 1);
		gpCenter.setConstraints(vbCenter, 1, 1);
		gpCenter.setConstraints(vbRight,2,1);
		
		ColumnConstraints col1 = new ColumnConstraints();
	    col1.setPercentWidth(25);
	    col1.setHalignment(HPos.LEFT);
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setPercentWidth(50);
	    ColumnConstraints col3 = new ColumnConstraints();
	    col3.setPercentWidth(25);
	    gpCenter.getColumnConstraints().addAll(col1,col2,col3);
		
		gpCenter.getChildren().addAll(vbLeft,vbCenter,vbRight);
		
		
		bPane.setAlignment(gpCenter, Pos.CENTER);
		bPane.setCenter(gpCenter);
		
		for (int i = 0; i < MAXNR; i++){
			//Feld zum Auswählen der Zahlen instanzieren
			hauptZahlen[i] = new ToggleButton(""+(i+1));
			hauptZahlen[i].setId("no"+(i+1));
			hauptZahlen[i].getStyleClass().add("zahlenToggles");
			tPane.getChildren().add(hauptZahlen[i]);
		}
		
		tGroup = new ToggleGroup();
		for(int i = 0; i < MAXZUSATZ; i++){
			zusatzZahlen[i] = new ToggleButton(""+(i+1));
			zusatzZahlen[i].setId("no"+(i+1));
			zusatzZahlen[i].getStyleClass().add("zahlenToggles");
			tPaneZusatz.getChildren().add(zusatzZahlen[i]);
			zusatzZahlen[i].setToggleGroup(tGroup);
		}
		
		Scene scene = new Scene(bPane, 850, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		stage.setScene(scene);
		stage.setTitle("Lotto");
		stage.getIcons().add(new Image("media/kleeblatt_icon.png"));

	}
	
	public void start() {
		stage.show();
	}

	public void stop() {
		stage.hide();
	}
}
