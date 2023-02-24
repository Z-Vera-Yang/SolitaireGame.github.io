package solitaire.model;

import java.util.HashMap;
import java.util.Map;

import solitaire.card.Card;

public class SuitStackManager {
	
	private final Map<SuitStack, Card> suitStackMap = new HashMap<>();

	public enum SuitStack implements Location {
		StackClubs, StackDiamonds, StackSpades, StackHearts
	}
	
	public void add(Card card) {
		assert card != null;
		if(!suitStackMap.containsKey(SuitStack.values()[card.getSuit().ordinal()])) {
			suitStackMap.put(SuitStack.values()[card.getSuit().ordinal()], card);
		}else {
			suitStackMap.replace(SuitStack.values()[card.getSuit().ordinal()], card);
		}
	}
	
	public boolean canDraw(Location suitStack) {
		if(suitStackMap.containsKey(suitStack)) {
			return true;
		}
		return false;
	}
	
	public boolean canAdd(Card card) {
		assert card != null;
		if(!suitStackMap.containsKey(SuitStack.values()[card.getSuit().ordinal()])) {
			if(card.getRank() == Card.Rank.ACE) {
				return true;
			}
		}else {
			if(suitStackMap.get(SuitStack.values()[card.getSuit().ordinal()]).getRank().ordinal() + 1 == card.getRank().ordinal()) {
				return true;
			}
		}
		return false;
	}
	
	public Card viewSuitStack(SuitStack suitStack) {
		if(suitStackMap.containsKey(suitStack)) {
			return suitStackMap.get(suitStack);
		}else {
			return null;
		}
	}
}
