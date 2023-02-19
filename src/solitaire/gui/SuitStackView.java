package solitaire.gui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SuitStackView extends StackPane{
	
	private static final int PADDING = 5;
	private static final String BORDER_STYLE = "-fx-border-color: lightgray; -fx-border-width: 3; -fx-border-radius: 10.0;";
	
	public SuitStackView() {
		setPadding(new Insets(PADDING));
		setStyle(BORDER_STYLE);
		
		final ImageView image = new ImageView(CardImages.getBack());
		image.setVisible(false);
		getChildren().add(image);
	}
}
