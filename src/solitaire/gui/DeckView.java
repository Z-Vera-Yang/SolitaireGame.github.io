package solitaire.gui;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import solitaire.model.GameModel;
import solitaire.model.GameModel.CardDeck;
import solitaire.model.GameModelListener;

public class DeckView extends HBox implements GameModelListener{
	
	private static final String BUTTON_STYLE_NORMAL 
	= "-fx-background-color: transparent; -fx-padding: 5,5,5,5;";
	
	private static final String BUTTON_STYLE_PRESSED 
	= "-fx-background-color: transparent; -fx-padding: 6,4,4,6;";
	private static final int IMAGE_FONT_SIZE = 12;
	
	public DeckView() {
		GameModel.getInstance().reset();
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
				
				if(!GameModel.getInstance().canDraw(CardDeck.DECK)) {
					GameModel.getInstance().reset();
				}else {
					GameModel.getInstance().getDeckMove().move();
				}	
			}			
		});	
		getChildren().add(button);
		GameModel.getInstance().addListener(this);
	}

	@Override
	public void gameStateChanged() {
		if(!GameModel.getInstance().canDraw(CardDeck.DECK)) {
			((Button) getChildren().get(0)).setGraphic(createNewGrameImage());
		}else {
			((Button) getChildren().get(0)).setGraphic(new ImageView(CardImages.getBack()));
		}	
	}

	private Canvas createNewGrameImage() {
		double width = CardImages.getBack().getWidth();
		double height = CardImages.getBack().getHeight();
		Canvas canvas = new Canvas(width, height);
		GraphicsContext context = canvas.getGraphicsContext2D();
		
		// using context to set the look of what we need
		context.setTextAlign(TextAlignment.CENTER);
		context.setFill(Color.DARKKHAKI);
		context.setFont(Font.font(Font.getDefault().getName(), IMAGE_FONT_SIZE));
		
		context.fillText("Start again?", Math.round(width / 2), IMAGE_FONT_SIZE); // add wording when last card is showing
		context.setStroke(Color. DARKGREEN); // add icon when no card can show from deck
		context.setLineWidth(10);
		context.strokeOval(width / 4, height / 2 - width/4 + IMAGE_FONT_SIZE, width / 2, width / 2);
		
		return canvas;
	}
}
