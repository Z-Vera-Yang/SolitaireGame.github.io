package solitaire.gui;



import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import solitaire.card.Card;
import solitaire.card.TempCard;
import solitaire.model.GameModel;
import solitaire.model.GameModel.CardDeck;
import solitaire.model.GameModelListener;

public class DiscardPileView extends HBox implements GameModelListener{
	
	private static final int PADDING = 5;
	private CardDragHandler dragHandler;
	
	public ImageView image;
	public ImageView image2;
	
	TempCard tempCard;
	

	Point2D dragDistance = null;

	
	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public DiscardPileView() {
		setPadding(new Insets(PADDING));
		tempCard = new TempCard(CardImages.getBack());
		tempCard.setVisible(false);
		getChildren().add(tempCard);
		GameModel.getInstance().addListener(this);
	}

	@Override
	public void gameStateChanged() {
		if(!GameModel.getInstance().canDraw(CardDeck.DISCARD)) {
			getChildren().get(0).setVisible(false);
			Node node = getChildren().get(0);
//			System.out.println(node);
		}else {
			getChildren().get(0).setVisible(true);
			Node node = getChildren().get(0);
//			System.out.println(node);
			Card topCard = GameModel.getInstance().peekDiscard();
			image2 = (ImageView) this.getChildren().get(0);
			image2.setImage(CardImages.getImage(topCard));
			tempCard.setCard(topCard);
//			TempCard tempCard = new TempCard(CardImages.getImage(topCard));
//   		tempCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
//			
		}
	}
}
