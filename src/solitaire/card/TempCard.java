package solitaire.card;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class TempCard extends ImageView {

    private double xOffset = 0;
    private double yOffset = 0;
    private Card card;
    public TempCard(Image image) {
    	
        super(image);
        setOnDragDetected(event -> {
    		   Dragboard db = startDragAndDrop(TransferMode.MOVE);
               ClipboardContent content = new ClipboardContent();
               content.putImage(getImage());
               content.putString(card.getIDString());
               db.setContent(content);
               xOffset = event.getX();
               yOffset = event.getY();
               event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != this && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        setOnDragEntered(event -> {
            setOpacity(0.7);
        });

        setOnDragExited(event -> {
            setOpacity(1.0);
        });

        setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        setOnMouseDragged(event -> {
            relocate(event.getSceneX() - xOffset, event.getSceneY() - yOffset);
        });
    }
    
   public void setCard(Card card)
	{
		this.card = card;
	}

}
