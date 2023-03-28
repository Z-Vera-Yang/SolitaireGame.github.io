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
    private static final ScorePane INSTANCE = new ScorePane();
    public ScorePane() {
        score = new Score();
        currentScoreLabel = new Label();
        highScoreLabel = new Label();
        currentScoreLabel.textProperty().bind(score.currentScoreProperty().asString("Current score: %d"));
        highScoreLabel.textProperty().bind(score.highScoreProperty().asString("High score: %d"));
        getChildren().addAll(currentScoreLabel, highScoreLabel);
        setPadding(new Insets(2));
        setSpacing(2);
    }
    
    public static ScorePane getInstance() {
		return INSTANCE;
	}
    
    public static void addPoints(int points) {
        score.addPoints(points);
    }
}