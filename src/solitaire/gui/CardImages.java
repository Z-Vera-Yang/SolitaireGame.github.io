package solitaire.gui;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;


public class CardImages {
	
	private static Map<String, Image> cards = new HashMap<>();
	
	private static Image getImage(String code) {
		Image image = cards.get(code);
		if(image==null) {
			String src = "file:./images/" + code + ".jpg";
			image = new Image(src);
			cards.put(code, image);
		}
		
		return image;
	}
	
	public static Image getBack() {
		return getImage("cardback");
	}

}
