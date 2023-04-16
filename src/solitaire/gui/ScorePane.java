package solitaire.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import solitaire.model.GameModel;
import solitaire.model.Score;

public class ScorePane extends VBox {
    private static Score score;
    private Label currentScoreLabel;
    private Label highScoreLabel;
    private static  ScorePane INSTANCE;
    
    public static Timer timer;
    private TimerLabel timerLabel;
    
    private ScorePane() {
        score = new Score();
        currentScoreLabel = new Label();
        highScoreLabel = new Label();
        currentScoreLabel.textProperty().bind(score.currentScoreProperty().asString("Current score: %d"));
        highScoreLabel.textProperty().bind(score.highScoreProperty().asString("High score: %d"));
        
        timer = new Timer();
        timerLabel = new TimerLabel(timer);
        
        getChildren().addAll(currentScoreLabel, highScoreLabel, timerLabel);
        timer.start();
        setPadding(new Insets(2));
        setSpacing(2);
    }
    
    public static synchronized ScorePane getInstance() {
    	if(INSTANCE == null) {
    		INSTANCE = new ScorePane();
    	}
		return INSTANCE;
	}
    
    public static void addPoints(int points) {
        score.addPoints(points);
    }
}