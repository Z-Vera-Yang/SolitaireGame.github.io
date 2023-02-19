package solitaire.gui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import solitaire.card.Card;
import solitaire.model.GameModel;
import solitaire.model.WorkingStackManager.Workingstack;

public class WorkingStackView extends StackPane{
	
	private static final int PADDING = 5;
	private static final int Y_OFFSET =17;
	private Workingstack index;

	public WorkingStackView(Workingstack index) {
		this.index = index;
		setPadding(new Insets(PADDING));
		
		buildLayout();
	}
	
	private void buildLayout() {
		int offset = 0;
		Card[] stack = GameModel.getInstance().getStack(index);
		for(Card card : stack) {
			final ImageView image = new ImageView(CardImages.getImage(card));
			image.setTranslateY(Y_OFFSET * offset);
			offset++;
			getChildren().add(image);
			System.out.println(index);
		}
	}
	

}
