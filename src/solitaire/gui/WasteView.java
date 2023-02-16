package solitaire.gui;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class WasteView extends HBox{
	
	private static final int PADDING = 5;
	
	public WasteView() {
		setPadding(new Insets(PADDING));
		ImageView image = new ImageView(CardImages.getBack());
		image.setVisible(false);
		
		getChildren().add(image);
		
	}
}
