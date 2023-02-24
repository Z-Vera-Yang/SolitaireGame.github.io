package solitaire.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import solitaire.card.Card.Suit;
import solitaire.model.SuitStackManager.SuitStack;
import solitaire.model.WorkingStackManager.Workingstack;

public class Solitaire extends Application{
	
	private static final int WIDTH = 850;
	private static final int HEIGHT = 531;
	private static final String TITLE ="Solitaire";
	private static final String VERSION ="1.0";
	private static final String GROUP = "CST8334 Group 5";
	
	private DeckView deckView = new DeckView();
	private DiscardPileView wasteView = new DiscardPileView();
	private SuitStackView[] suitStacks = new SuitStackView[Suit.values().length];
	private WorkingStackView[] stacks = new WorkingStackView[Workingstack.values().length];

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle(TITLE+ " " + VERSION +"    " + GROUP);
		
		GridPane root = new GridPane();
		root.setStyle("-fx-background-image: url('file:./images/background.jpg')");
		
		root.add(deckView, 0, 0);
		root.add(wasteView, 1, 0);
		for(SuitStack index : SuitStack.values()) {
			suitStacks[index.ordinal()] = new SuitStackView(index);
			root.add(suitStacks[index.ordinal()], 3+index.ordinal(), 0);
		}
		for(Workingstack index : Workingstack.values()) {
			stacks[index.ordinal()] = new WorkingStackView(index);
			root.add(stacks[index.ordinal()], index.ordinal(), 1);
		}
		
		arg0.setResizable(false);
		arg0.setScene(new Scene(root, WIDTH, HEIGHT));	
		arg0.show();
	}

}
