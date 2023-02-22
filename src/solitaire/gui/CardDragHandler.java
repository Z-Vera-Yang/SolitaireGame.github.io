package solitaire.gui;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import solitaire.card.Card;

public class CardDragHandler implements EventHandler<MouseEvent>{
	
	private Card card;
	private ImageView view;

	CardDragHandler(ImageView view)
	{
		this.view = view;
	}

	void setCard(Card card)
	{
		this.card = card;
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		Dragboard dragBoard = view.startDragAndDrop(TransferMode.ANY);
		ClipboardContent clipContent = new ClipboardContent();
		clipContent.putString(card.getIDString());
		dragBoard.setContent(clipContent);
		event.consume();
		
	}

}
