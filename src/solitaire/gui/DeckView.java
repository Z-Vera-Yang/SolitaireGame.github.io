package solitaire.gui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DeckView extends HBox{
	
	private static final String BUTTON_STYLE_NORMAL 
	= "-fx-background-color: transparent; -fx-padding: 5,5,5,5;";
	
	public DeckView() {
		Button button = new Button();
		button.setGraphic(new ImageView(CardImages.getBack()));
		button.setStyle(BUTTON_STYLE_NORMAL);
	
		getChildren().add(button);
	}
}
