package solitaire.model;

import java.util.prefs.Preferences;

import javafx.beans.property.SimpleIntegerProperty;


public class Score {
    private SimpleIntegerProperty currentScore;
    private SimpleIntegerProperty highScore;
    private Preferences prefs;

    public Score() {
        prefs = Preferences.userNodeForPackage(getClass());
        currentScore = new SimpleIntegerProperty(0);
        highScore = new SimpleIntegerProperty(prefs.getInt("highScore", 0));
    }

    public void addPoints(int points) {
        currentScore.set(currentScore.get() + points);
        if (currentScore.get() > highScore.get()) {
            highScore.set(currentScore.get());
            prefs.putInt("highScore", highScore.get());
        }
    }

    public SimpleIntegerProperty currentScoreProperty() {
        return currentScore;
    }

    public SimpleIntegerProperty highScoreProperty() {
        return highScore;
    }
}