package solitaire.gui;

import java.awt.Event;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class DeckView extends HBox{
	
	private static final String BUTTON_STYLE_NORMAL 
	= "-fx-background-color: transparent; -fx-padding: 5,5,5,5;";
	
	private static final String BUTTON_STYLE_PRESSED 
	= "-fx-background-color: transparent; -fx-padding: 6,4,4,6;";
	
	public DeckView() {
		Button button = new Button();
		button.setGraphic(new ImageView(CardImages.getBack()));
		button.setStyle(BUTTON_STYLE_NORMAL);
		
		button.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				((Button) event.getSource()).setStyle(BUTTON_STYLE_PRESSED);
			}
		});
		
		button.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				((Button) event.getSource()).setStyle(BUTTON_STYLE_NORMAL);
			}
			
		});
	
		getChildren().add(button);
	}
}
