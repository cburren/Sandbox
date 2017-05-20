package LottoMVC;

import java.math.BigInteger;
import java.util.ArrayList;

import com.sun.prism.paint.Color;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LottoView {
	final private LottoModel model;
	final private Stage stage;
	
	final int MAXNR = 35;
	final int MAXZUSATZ = 6;
	final int MAXCHOICE = 6;
	int tipWidth = 30*(MAXCHOICE+1);
	int tipHeight = 30;
	
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
	
	
	// ********************** ZIEHUNG **********
	
	VBox vbTips;
	VBox vbZahlen;
	VBox vbResults;
	HBox hbWin;
	
	Button btnNew;
	
	
	TilePane tip1 = new TilePane();
	TilePane luckyNumbers = new TilePane();
	
	ToggleButton[] tbTip1 = new ToggleButton[MAXCHOICE*2+2];
	ToggleButton[] tbTip2 = new ToggleButton[MAXCHOICE+1];
	
	ArrayList<ToggleButton> tipButtons = new ArrayList<ToggleButton>();
	
	
	GridPane gpZiehung;
	
	
	Label winTips;
	Label lblwinCalc;
	Label lblwinSum;
	VBox vbwinRichtige;
	VBox vbwinAnzahl;
	VBox vbwinGewinn;
	
	
	
	//**********WELCOME******
	
	HBox hbTop;
	Label lblTitle;
	
	
	ToggleButton btnEnglish;
	ToggleButton btnDeutsch;
	ToggleButton btnFranz;
	ToggleGroup tgSprachen;
	HBox hbBottom;
	
	
	// WELCOME CENTER
	VBox vbWelcome;
	HBox hbWelcomeStats;
	VBox vbStatsAnz;
	VBox vbStatsWahr;
	VBox vbStatsGewinn;
	
	
	Button btnNext;
	
	
	
	public LottoView(Stage stage, LottoModel model){
		this.model = model;
		this.stage = stage;
	    myEffect = new BoxBlur(10, 10, 3);
		
		bPane = new BorderPane();
		
		//**********************WELCOME****************************
		hbBottom = new HBox();
		btnEnglish = new ToggleButton("English");
		btnDeutsch = new ToggleButton("Deutsch");
		btnFranz = new ToggleButton("Francais");
		tgSprachen = new ToggleGroup();
		btnEnglish.setToggleGroup(tgSprachen);
		btnDeutsch.setToggleGroup(tgSprachen);
		btnFranz.setToggleGroup(tgSprachen);
		
		
		hbBottom.getChildren().addAll(btnEnglish, btnDeutsch, btnFranz);
		hbBottom.setMinHeight(50);
		hbBottom.setAlignment(Pos.TOP_LEFT);
		hbBottom.setPadding(new Insets(10, 50, 50, 50));
		hbBottom.setSpacing(5);
		//hbBottom.setStyle("-fx-border-color: green");
		
		hbTop = new HBox();
		lblTitle = new Label("Spielinformationen");
		lblTitle.setStyle("-fx-font-size: 24px;");
		
		hbTop.getChildren().add(lblTitle);
		hbTop.setMinHeight(100);
		hbTop.setAlignment(Pos.CENTER);
		hbTop.setPadding(new Insets(50, 50, 10, 50));
		//hbTop.setStyle("-fx-border-color: green");
		
		
		// **************************** WELCOME STATS *************************
		
		vbWelcome = new VBox();
		hbWelcomeStats = new HBox();
		vbStatsAnz = new VBox();
		vbStatsWahr = new VBox();
		vbStatsGewinn = new VBox();
		
		
		
		btnNext = new Button("Weiter");
		
		
		hbWelcomeStats.setAlignment(Pos.CENTER);
		hbWelcomeStats.setSpacing(30);
		vbStatsAnz.setAlignment(Pos.CENTER_LEFT);
		vbStatsWahr.setAlignment(Pos.CENTER_LEFT);
		vbStatsGewinn.setAlignment(Pos.CENTER_RIGHT);
		vbWelcome.setAlignment(Pos.CENTER);
		vbWelcome.setSpacing(15);
		
		
		Label anzTitel = new Label("Anzahl Richtige");
		Label wahrTitel = new Label("Wahrscheinlichkeit");
		Label winTitel = new Label("Gewinn");
		
		vbStatsAnz.getChildren().add(anzTitel);
		vbStatsWahr.getChildren().add(wahrTitel);
		vbStatsGewinn.getChildren().add(winTitel);
		
		
		for(int i = model.MAXCHOICE;0<=i;i--){
			Label temp = new Label(i+"+1:");
			Label temp2 = new Label(i+":");
			
			vbStatsAnz.getChildren().addAll(temp,temp2);
			
			Label wahr = new Label("1 : "+model.wahr[i*2+1]);
			Label wahr1 = new Label("1 : "+model.wahr[i*2]);
	
			
			vbStatsWahr.getChildren().addAll(wahr,wahr1);
			
			Label win = new Label(model.gewinne[i*2+1]+".-");
			Label win1 = new Label(model.gewinne[i*2]+".-");
			
			vbStatsGewinn.getChildren().addAll(win, win1);
			
		}
		
		hbWelcomeStats.getChildren().addAll(vbStatsAnz,vbStatsWahr, vbStatsGewinn);
		vbWelcome.getChildren().addAll(hbWelcomeStats,btnNext);
		
		
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
		//vbCenter.setStyle("-fx-border-color: green");
		
		//********************** LINKS ************************
		
		vbLeft = new VBox();
		vbLeft.setAlignment(Pos.CENTER);
		lblOutOf = new Label ("0 / " + MAXCHOICE);
		lblOutOfText = new Label("Zahlen gewählt:");
		lblTips = new Label ("0");
		lblTipsText = new Label ("Gesetzte Tipps:");
		lblMoney = new Label (model.bet+".- CHF");
		lblMoneyText = new Label ("Einsatz:");
		
		
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
		//gpCenter.setMargin(vbRight, new Insets(0, 0, 0, 0));
		//gpCenter.setStyle("-fx-border-color: green");
		
		ColumnConstraints col1 = new ColumnConstraints();
	    col1.setPercentWidth(25);
	    col1.setHalignment(HPos.LEFT);
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setPercentWidth(50);
	    ColumnConstraints col3 = new ColumnConstraints();
	    col3.setPercentWidth(25);
	    gpCenter.getColumnConstraints().addAll(col1,col2,col3);
		
		gpCenter.getChildren().addAll(vbLeft,vbCenter,vbRight);
		
		
		
		//**********************************************ZIEHUNG**************************************
		
		
		vbTips = new VBox();
		vbZahlen = new VBox();
		vbResults = new VBox();
		
		vbTips.setAlignment(Pos.CENTER);
		vbZahlen.setAlignment(Pos.CENTER);
		vbZahlen.setSpacing(50);
		vbResults.setAlignment(Pos.CENTER);
		
		tip1.setAlignment(Pos.CENTER);
		luckyNumbers.setAlignment(Pos.CENTER);
		
		
		
		gpZiehung = new GridPane();
		gpZiehung.setAlignment(Pos.CENTER);
		gpZiehung.setConstraints(vbTips, 0, 1);
		gpZiehung.setConstraints(vbZahlen, 1, 1);
		gpZiehung.setConstraints(vbResults,2,1);
		
		gpZiehung.getColumnConstraints().addAll(col1,col2,col3);
		gpZiehung.getChildren().addAll(vbTips, vbZahlen, vbResults);
		
		tip1.setMinSize(tipWidth, tipHeight);
		tip1.setMaxSize(tipWidth, tipHeight);
		
		hbWin = new HBox();
		vbwinRichtige = new VBox();
		vbwinAnzahl = new VBox();
		vbwinGewinn = new VBox();
		vbwinAnzahl.setAlignment(Pos.CENTER);
		vbwinGewinn.setAlignment(Pos.CENTER_RIGHT);
		hbWin.setAlignment(Pos.CENTER);
		hbWin.setSpacing(15);
		
		lblwinCalc = new Label();
		lblwinSum = new Label();
		lblwinCalc.setStyle("-fx-font-size: 16px;");
		lblwinSum.setStyle("-fx-font-size: 24px;");
		
		
		hbWin.getChildren().addAll(vbwinRichtige,vbwinAnzahl,vbwinGewinn);
		vbResults.getChildren().add(hbWin);
		
		btnNew = new Button("Neue Runde");
		
		vbTips.getChildren().addAll(tip1);
		vbZahlen.getChildren().addAll(luckyNumbers,lblwinCalc, lblwinSum);
		
		
		bPane.setAlignment(vbWelcome, Pos.CENTER);
		bPane.setCenter(vbWelcome);
		bPane.setBottom(hbBottom);
		bPane.setTop(hbTop);
		
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
		
		Scene scene = new Scene(bPane, 950, 600);
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
	
	public void addTipButtons(ArrayList<Integer> list){
		for (int i = 0; i<list.size(); i++){
			ToggleButton temp;
			
			if(list.get(i).intValue()<0){
				temp = new ToggleButton("+"+(list.get(i).intValue()*-1));
				temp.getStyleClass().add("tipButtons");
				temp.setStyle("-fx-font-weight: bold; -fx-background-color: rgb(253,220,206);");
			}else{
			    temp = new ToggleButton(""+(list.get(i).intValue()));
			    temp.getStyleClass().add("tipButtons");
			}
			temp.setId(""+list.get(i).intValue());
			temp.setDisable(true);
			tipButtons.add(temp);
			tip1.getChildren().add(temp);
			tip1.setAlignment(Pos.CENTER);
		}
		
	}
	
	public void showLuckyNumbers(ArrayList<Integer> list){
		
		for(int i:list){
			ToggleButton temp;
			if(i <0){
				temp = new ToggleButton("+"+(i*-1));
				temp.getStyleClass().add("luckyNumbers");
				temp.setStyle("-fx-font-weight: bold; -fx-background-color: rgb(251,255,70);");
			}else{
			    temp = new ToggleButton(""+(i));
			    temp.getStyleClass().add("luckyNumbers");
			}
			temp.setDisable(true);
			luckyNumbers.getChildren().add(temp);
		}
		
		for(int i = 0; i<tipButtons.size();i++){
			if(model.luckyNumbers.contains(Integer.parseInt(tipButtons.get(i).getId()))){
				tipButtons.get(i).setStyle(null);
				tipButtons.get(i).setSelected(true);
			}
		}
		
	}
	
	public void showWinResults(){
		
		Label anzTitel = new Label("Richtige");
		Label wahrTitel = new Label("Anzahl");
		Label winTitel = new Label("Gewinn");
		
		vbwinRichtige.getChildren().add(anzTitel);
		vbwinAnzahl.getChildren().add(wahrTitel);
		vbwinGewinn.getChildren().add(winTitel);
		
		
		for(int i = model.MAXCHOICE;model.MINRICHTIGE<=i;i--){
			Label temp = new Label(i+"+1:");
			Label temp2 = new Label(i+":");
			
			vbwinRichtige.getChildren().addAll(temp,temp2);
			
			Label anz = new Label(model.treffer[(i)*2+1]+" x");
			Label anz1 = new Label(model.treffer[(i)*2]+" x");
			
			vbwinAnzahl.getChildren().addAll(anz, anz1);
			
			int tipgewinn = model.gewinne[i*2+1]*model.treffer[(i)*2+1];
			int tipgewinn1 = model.gewinne[i*2]*model.treffer[(i)*2];
			
			Label win = new Label(model.gewinne[i*2+1]*model.treffer[(i)*2+1]+".-");
			Label win1 = new Label(model.gewinne[i*2]*model.treffer[(i)*2]+".-");
			
			model.gewinn += tipgewinn+tipgewinn1;
			vbwinGewinn.getChildren().addAll(win, win1);
		}
		
		lblwinCalc.setText(""+model.gewinn+".- CHF Gewinn\n- "+model.bet+".- CHF Einsatz");
		lblwinCalc.setTextAlignment(TextAlignment.RIGHT);
		if(model.gewinn<model.bet)
			lblwinSum.setStyle("-fx-background-color: rgb(255,83,83); -fx-font-size: 24px;");
		else
			lblwinSum.setStyle("-fx-background-color: rgb(113,225,0); -fx-font-size: 24px;");
		lblwinSum.setText(" " +(model.gewinn-model.bet)+".- CHF ");
	}
	
}
