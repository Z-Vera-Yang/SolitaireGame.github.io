package solitaire.gui;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import solitaire.card.Card;


public class CardImages {
	
	private static Map<String, Image> cards = new HashMap<>();
	
	private static final String[] RANK_CODES = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
	private static final String[] SUIT_CODES = {"clubs", "diamonds", "spades", "hearts"};
	
	private static String getCode(Card card) {
		return SUIT_CODES[card.getSuit().ordinal()] + RANK_CODES[card.getRank().ordinal()];
	}
	
	public static Image getCurrentImage(Card card) {
//		System.out.println("here now " + card.toString());
		assert card != null;
		if (card.isFaceUp()) {
			return getImage(card);
		} else {
			return getBack();
		}
	}
	
	public static Image getImage(Card card) {
		assert card != null;
		return getImage(getCode(card));
	}
	
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
