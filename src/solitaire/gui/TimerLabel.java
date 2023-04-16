package solitaire.gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerLabel extends Label {

    private Timer timer;
    private Timeline timeline;

    public TimerLabel(Timer timer) {
        this.timer = timer;
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> setText(formatTime(timer.getSeconds()))));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        setAlignment(Pos.CENTER_RIGHT);
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void reset() {
        timer.reset();
    }
}

