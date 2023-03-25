package solitaire.gui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import solitaire.card.Card;
import solitaire.model.GameModel;
import solitaire.model.GameModel.CardDeck;
import solitaire.model.GameModelListener;

public class DiscardPileView extends HBox implements GameModelListener{
	
	private static final int PADDING = 5;
	private CardDragHandler dragHandler;
	
	public DiscardPileView() {
		setPadding(new Insets(PADDING));
		ImageView image = new ImageView(CardImages.getBack());
		image.setVisible(false);
		
		dragHandler = new CardDragHandler(image);
		image.setOnDragDetected(dragHandler);
		
		getChildren().add(image);
		GameModel.getInstance().addListener(this);
	}

	@Override
	public void gameStateChanged() {
		if(!GameModel.getInstance().canDraw(CardDeck.DISCARD)) {
			getChildren().get(0).setVisible(false);
		}else {
			getChildren().get(0).setVisible(true);
			Card topCard = GameModel.getInstance().peekDiscard();
			ImageView image = (ImageView) this.getChildren().get(0);
			image.setImage(CardImages.getCurrentImage(topCard));
			dragHandler.setCard(topCard);
		}
	}
}
