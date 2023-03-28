package solitaire.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import solitaire.card.Card;
import solitaire.card.TempCard;
import solitaire.model.GameModel;
import solitaire.model.GameModelListener;
import solitaire.model.WorkingStackManager.Workingstack;

public class WorkingStackView extends StackPane implements GameModelListener {
	
	private static final int PADDING = 5;
	private static final int Y_OFFSET = 30;
	private Workingstack index;
	
	public WorkingStackView(Workingstack index) {
		this.index = index;
		setPadding(new Insets(PADDING));
		buildLayout();
		GameModel.getInstance().addListener(this);
	}
	
	private void buildLayout() {
		getChildren().clear();
		int offset = 0;
		Card[] stack = GameModel.getInstance().getStack(index);
		if (stack.length >0 && !stack[stack.length-1].isFaceUp()) {
			stack[stack.length-1].flip();
		}
		for(Card card : stack) {
			ImageView image = new ImageView(CardImages.getCurrentImage(card));
			image.setTranslateY(Y_OFFSET * offset);
			offset++;
			getChildren().add(image);
			
			setOnDragOver(createDragOverHandler());
			setOnDragDropped(createDragDroppedHandler());
			image.setOnDragDetected(createDragDetectedHandler(image,card));
		}
	}
	
	private EventHandler<MouseEvent> createDragDetectedHandler(final ImageView imageView, final Card card) {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
				WritableImage wi = new WritableImage(105, 150);
				imageView.snapshot(null, wi);
				double centerX = CardImages.getBack().getWidth() / 2;
				double centerY = CardImages.getBack().getWidth() / 2;
				db.setDragViewOffsetX(centerX);
	            db.setDragViewOffsetY(centerY);
				db.setDragView(wi);
				ClipboardContent cc = new ClipboardContent();
				cc.putString(card.getIDString());
				db.setContent(cc);
				event.consume();
			}			
		};		
	}
	
	private EventHandler<DragEvent> createDragOverHandler(){		
		return new EventHandler<DragEvent>() {		
			@Override
			public void handle(DragEvent event) {
				if(GameModel.getInstance().canAdd(Card.get(event.getDragboard().getString()), index)) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
				event.consume();				
			}
		};
	}
	
	private EventHandler<DragEvent> createDragDroppedHandler() {
		return new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if(db.hasString()) {
					GameModel.getInstance().getCardMove(Card.get(db.getString()), index).move();
					ScorePane.getInstance().addPoints(1);
					success = true;
				}
				event.setDropCompleted(success);
			}
			
		};		
	}

	@Override
	public void gameStateChanged() {
		buildLayout();
	}

}
