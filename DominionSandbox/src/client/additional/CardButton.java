package client.additional;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardButton extends Button {
private String cardName;
private String location;
private Button rootButton;
private String normalURL;
private String hoverURL;
private static final int HEIGHT = 223;
private static final int WIDTH = 150;
private int counter;

	
	public CardButton(String cardName, String location) {
		super(location);
		super.getStylesheets().add(getClass().getResource("../additional/button.css").toExternalForm());
		super.setMinSize(WIDTH, HEIGHT+50);
		super.setStyle("-fx-background-image: url('/client/cardimages/testkarte.jpg'); -fx-background-size:"+Integer.toString(WIDTH)+"px "+Integer.toString(HEIGHT)+"px;");
		super.setOnMouseEntered(e -> super.setStyle("-fx-background-image: url('/client/cardimages/testkarte_rover.jpg');-fx-background-size:"+Integer.toString(WIDTH)+"px "+Integer.toString(HEIGHT)+"px;"));
		super.setOnMouseExited(e -> super.setStyle("-fx-background-image: url('/client/cardimages/testkarte.jpg');-fx-background-size:"+Integer.toString(WIDTH)+"px "+Integer.toString(HEIGHT)+"px;"));
	
		
		super.setOnMouseClicked(e -> super.setText("4"));
		super.setOnMouseClicked(e -> super.setVisible(false));
		
		//super.setTooltip(new Tooltip(cardName));
		
		
		this.cardName = cardName;
		this.location = location;
		//this.rootButton = new Button("Test");
		
		Image image = new Image("/client/cardimages/testkarte.jpg", 800, 400, true, true);

		Tooltip tooltip = new Tooltip();
		tooltip.setGraphic(new ImageView(image));
		tooltip.setText("KARTENNAME");
		tooltip.centerOnScreen();

		super.setTooltip(tooltip);
		
	}


	public void setFullVisible() {
		int newWidth = (int) (WIDTH*0.8);
		int newHeight = (int) (HEIGHT*0.8);
		super.setMinSize(WIDTH*0.8, HEIGHT*0.8);
		super.setOpacity(1.0);
		super.setText("");
		super.setStyle("-fx-background-image: url('/client/cardimages/testkarte.jpg'); -fx-background-size:"+Integer.toString(newWidth)+"px "+Integer.toString(newHeight)+"px;");
	}

}
