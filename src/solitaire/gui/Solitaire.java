package solitaire.gui;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import solitaire.card.Card.Suit;
import solitaire.model.GameModel;
import solitaire.model.Score;
import solitaire.model.SuitStackManager.SuitStack;
import solitaire.model.WorkingStackManager.Workingstack;

public class Solitaire extends Application{
	
	// set size of the game window and title 
	private static final int WIDTH = 850;
	private static final int HEIGHT = 620;
	private static final String TITLE ="Solitaire";
	private static final String VERSION ="1.0";
	private static final String GROUP = "CST8334 Group 14";
	private DeckView deckView = new DeckView();
	private DiscardPileView wasteView = new DiscardPileView();
	private SuitStackView[] suitStacks = new SuitStackView[Suit.values().length];
	private WorkingStackView[] stacks = new WorkingStackView[Workingstack.values().length];
	private Point2D dragDistance = null;
	private ScorePane scorePane = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle(TITLE+ " " + VERSION +"    " + GROUP);
		
		BorderPane root = new BorderPane();
		GridPane gridPane = new GridPane();
		
		gridPane.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCharacter().equals("u")) {
					GameModel.getInstance().undoLast();
				}
				event.consume();
			}
		});

		gridPane.setStyle("-fx-background-image: url('file:./images/background.jpg')");

		arg0.setResizable(false);

		MenuBar menuBar = new MenuBar();

		 // --- Menu Game
        Menu menuGame = new Menu("Game");
        MenuItem start = new MenuItem("Start");
        
        double width = CardImages.getBack().getWidth();
		double height = CardImages.getBack().getHeight();
		
		scorePane = ScorePane.getInstance();
		scorePane.setPrefWidth(width);// prefWidth for score pane with the size of card
		scorePane.setPrefHeight(height);// prefHeight for score pane fitting the size of card
		
        start.setOnAction((ActionEvent t) -> {
        	gridPane.add(deckView, 0, 0);
        	gridPane.add(wasteView, 1, 0);
        	gridPane.add(scorePane, 2, 0);
    		for(SuitStack index : SuitStack.values()) {
    			suitStacks[index.ordinal()] = new SuitStackView(index);
    			gridPane.add(suitStacks[index.ordinal()], 3+index.ordinal(), 0);
    		}
    		for(Workingstack index : Workingstack.values()) {
    			stacks[index.ordinal()] = new WorkingStackView(index);
    			gridPane.add(stacks[index.ordinal()], index.ordinal(), 1);
    		}
    		 start.setDisable(true);
    		 
        });

     // --- MenuItem Shuffle        
        MenuItem shuffle = new MenuItem("Shuffle");
        //GameModel.getInstance().reset();
        shuffle.setOnAction((ActionEvent t) -> {
        	GameModel.getInstance().reset(true);
        });

     // --- MenuItem Exit   
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });

        menuGame.getItems().addAll(start, shuffle, exit);
     // --- Menu Help
        Menu menuHelp = new Menu("Help");
     // --- MenuItem About
        MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				/*
				 * Implement dialog to be prompted when players asks for
				 * details.
				 */
//				System.out.println("print Solitaire help information");
				
			      Dialog<String> dialog = new Dialog<String>();
			      dialog.setTitle("Help");
			      ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
			      dialog.setContentText("The Solitaire application implemented by Group 14 of CST8334_450 and directed by professor Moe Osman, thank you.");
			      dialog.getDialogPane().getButtonTypes().add(type);
			      dialog.showAndWait();
			}
		});
		menuHelp.getItems().add(about);

        menuBar.getMenus().addAll(menuGame, menuHelp);
        menuBar.setStyle("-fx-background-color: #ededed;");
        //color options: Algonquin College green: #006341   blue:#4589bc, light green: #8be78b; light grey: #d3d3d3, lighter grey: #ededed
        root.setTop(menuBar);
        root.setCenter(gridPane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		arg0.setScene(scene);
		arg0.show();
	}

}
