package solitaire.gui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import solitaire.card.Card;
import solitaire.model.GameModel;
import solitaire.model.GameModelListener;

public class WasteView extends HBox implements GameModelListener{
	
	private static final int PADDING = 5;
	
	public WasteView() {
		setPadding(new Insets(PADDING));
		ImageView image = new ImageView(CardImages.getBack());
		image.setVisible(false);
		
		getChildren().add(image);
		GameModel.getInstance().addListener(this);
	}

	@Override
	public void gameStateChanged() {
		getChildren().get(0).setVisible(true);
		Card topCard = GameModel.getInstance().peekWaste();
		ImageView image = (ImageView) this.getChildren().get(0);
		image.setImage(CardImages.getImage(topCard));
	}
}
