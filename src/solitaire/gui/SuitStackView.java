package solitaire.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import solitaire.card.Card;
import solitaire.model.GameModel;
import solitaire.model.GameModelListener;
import solitaire.model.SuitStackManager.SuitStack;

public class SuitStackView extends StackPane implements GameModelListener{
	
	private static final int PADDING = 5;
	private static final String BORDER_STYLE = "-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 10.0;";
	private SuitStack index;
	private CardDragHandler dragHandler;
	
	public SuitStackView(SuitStack index) {
		this.index = index;
		setPadding(new Insets(PADDING));
		setStyle(BORDER_STYLE);
		
		final ImageView image = new ImageView(CardImages.getBack());
		image.setVisible(false);
		getChildren().add(image);
		dragHandler = new CardDragHandler(image);
		image.setOnDragDetected(dragHandler);
		setOnDragOver(createOnDragOverHandler());
		setOnDragDropped(createOnDragDroppedHandler());
		GameModel.getInstance().addListener(this);
	}
	
	private EventHandler<DragEvent> createOnDragOverHandler() {
		return new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();
			}	
		};
	}
	
	private EventHandler<DragEvent> createOnDragDroppedHandler() {
		return new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if(db.hasString()) {
					GameModel.getInstance().getCardMove(Card.get(db.getString()), index).move();
					ScorePane.getInstance().addPoints(10);
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}	
		};
	}

	@Override
	public void gameStateChanged() {
		if(!GameModel.getInstance().canDraw(index)) {
			getChildren().get(0).setVisible(false);
		}else {
			getChildren().get(0).setVisible(true);
			Card topCard = GameModel.getInstance().peekSuitStack(index);
			ImageView image = (ImageView) getChildren().get(0);
			image.setImage(CardImages.getCurrentImage(topCard));
			dragHandler.setCard(topCard);
		}		
	}
}
