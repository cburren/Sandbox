package client.mvc;
	

import java.util.ArrayList;

import client.additional.CardButton;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class Main extends Application {
	
	ArrayList<GridPane> lobbyScreens;
	Image icon1;
	Image icon2;
	Image icon3;
	ImageView imgView1;
	ImageView imgView2;
	ImageView imgView3;
	
	Pane backgroundPane;
	
	Label lblTitle;
	BorderPane root;
	
	HBox hbTop;
	HBox hbBottom;
	
	Button btnConnect;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new BorderPane();
			Scene scene = new Scene(root, 1100,600);
			scene.getStylesheets().add(getClass().getResource("../additional/lobby.css").toExternalForm());
			primaryStage.setMinWidth(1100);
			primaryStage.setMinHeight(800);

			HBox h1 = new HBox();	
			HBox h2 = new HBox();	
			hbTop = new HBox();
			VBox vbCenter = new VBox();
			HBox hbCenterButton = new HBox();
			hbBottom = new HBox();
			hbBottom.setMinHeight(70);
			
			CardButton b1 = new CardButton("test","hand");
			CardButton b2 = new CardButton("225","hand");
			CardButton b3 = new CardButton("test","hand");
			CardButton b4 = new CardButton("15","hand");
			CardButton b5 = new CardButton("test","hand");
			CardButton b6 = new CardButton("test","hand");
			
			Rectangle headerShape = new Rectangle();
			headerShape.setFill(Color.rgb(96, 96, 96));
			headerShape.heightProperty().bind(scene.heightProperty().divide(16));
			headerShape.widthProperty().bind(scene.widthProperty());
			headerShape.setEffect(new DropShadow());
			
			hbTop.getChildren().add(headerShape);
			hbTop.setMaxHeight(20);
			hbTop.setScaleY(6);
			hbTop.setAlignment(Pos.TOP_LEFT);
			
			vbCenter.setAlignment(Pos.TOP_CENTER);
			vbCenter.setStyle("-fx-background-color: white;");
			
			Button btnGerman = new Button("Starte das Spiel in Deutsch");
			Image imageDE = new Image(getClass().getResourceAsStream("../cardimages/germanFlag.png"), 60,40,false,false);
	        btnGerman.setGraphic(new ImageView(imageDE));
	        btnGerman.setGraphicTextGap(50);
	        btnGerman.setAlignment(Pos.CENTER_LEFT);
	        btnGerman.getStyleClass().add("languagebutton");
	        
	        Button btnEnglish = new Button("Start Game in English");
	        Image imageEN = new Image(getClass().getResourceAsStream("../cardimages/englishFlag.png"), 60,40,false,false);
	        btnEnglish.setGraphic(new ImageView(imageEN));
	        btnEnglish.setGraphicTextGap(50);
	        btnEnglish.setAlignment(Pos.CENTER_LEFT);
	        btnEnglish.getStyleClass().add("languagebutton");
			
			lobbyScreens = new ArrayList<GridPane>();
			icon1 = new Image(getClass().getResourceAsStream("../cardimages/iconLanguage.png"), 48,48,false,false);
			icon2 = new Image(getClass().getResourceAsStream("../cardimages/iconUser.png"), 48,48,false,false);
			icon3 = new Image(getClass().getResourceAsStream("../cardimages/iconConnection.png"), 48,48,false,false);
			
			imgView1 = new ImageView();
			imgView2 = new ImageView();
			imgView3 = new ImageView();
			
			imgView1.setImage(icon1);
			imgView2.setImage(icon2);
			imgView3.setImage(icon3);
			
			
			imgView1.setOpacity(1.0);
			imgView2.setOpacity(0.2);
			imgView3.setOpacity(0.2);
			
			
			backgroundPane = new Pane();
			backgroundPane.setStyle("-fx-background-color: white;");
			backgroundPane.setEffect(new DropShadow());
			
			lblTitle = new Label("Dominion");
			lblTitle.setFont(Font.font ("Verdana", 30));
			
			
			ColumnConstraints columnNormal = new ColumnConstraints();
		    columnNormal.setPercentWidth(50);
		    columnNormal.setHalignment(HPos.CENTER);
		    RowConstraints rowNormal = new RowConstraints();
		    rowNormal.setPercentHeight(40);
		    RowConstraints rowSmall = new RowConstraints();
		    rowSmall.setPercentHeight(20);
		    ColumnConstraints columnSmall = new ColumnConstraints();
		    columnSmall.setPercentWidth(5);
			
	        for(int i = 0; i<3;i++) {
	        	GridPane temp = new GridPane();
				temp.setMaxWidth(1200);
				temp.setMaxHeight(800);
				temp.setScaleX(0.9);
				temp.setVgap(15);
				temp.setAlignment(Pos.CENTER);
				temp.getColumnConstraints().addAll(columnSmall, columnNormal, columnNormal, columnNormal, columnSmall);
				temp.getRowConstraints().addAll(rowSmall,rowNormal,rowNormal,rowNormal,rowNormal);
				temp.setGridLinesVisible(false);
	        	lobbyScreens.add(temp);
	        }
	        setScreen(0);
			lobbyScreens.get(0).add(btnEnglish, 1, 2,3,1 );
			lobbyScreens.get(0).add(btnGerman, 1, 3,3,1);
			
			
			b6.setDisable(true);
			h1.setAlignment(Pos.BOTTOM_CENTER);
			h2.setAlignment(Pos.BOTTOM_CENTER);
			
			
			
			b3.setOnMouseClicked(e -> h1.getChildren().remove(b3));
			b4.setOnMouseClicked(e -> {
				h2.getChildren().add(b4);
				b4.setDisable(true);
				b4.setFullVisible();
				
			});
			b2.setOnMouseClicked(e -> {
				h2.getChildren().add(b2);
				b2.setDisable(true);
				b2.setFullVisible();
				
			});
			
			btnConnect = new Button("Connect to Server");
			
			btnEnglish.setOnMouseClicked(e -> {
				setScreen(1);
				imgView1.setOpacity(0.2);
				imgView2.setOpacity(1);
				imgView3.setOpacity(0.2);
				lblTitle.setText("Your Name");
				TextField playerName = new TextField();
				playerName.getStyleClass().add("playername");
				lobbyScreens.get(1).add(playerName, 2, 2);
				lobbyScreens.get(1).add(btnConnect,2,3);
				
			});
			
			btnConnect.setOnMouseClicked(e -> {
				setScreen(2);
				imgView1.setOpacity(0.2);
				imgView2.setOpacity(0.2);
				imgView3.setOpacity(1);
				lblTitle.setText("Game Settings");
			
			});
			
			primaryStage.setTitle("CGJNP: Dominion");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setScreen(int index) {
		GridPane temp = lobbyScreens.get(index);
		temp.getChildren().clear();
		temp.add(imgView1, 1, 0);
		temp.add(imgView2, 2, 0);
		temp.add(imgView3, 3, 0);
		temp.add(backgroundPane, 0,1,6,4);
		temp.add(lblTitle, 2, 1);
		root.setTop(hbTop);
		root.setBottom(hbBottom);
		root.setCenter(temp);
		root.setAlignment(temp, Pos.TOP_CENTER);
	}
}
