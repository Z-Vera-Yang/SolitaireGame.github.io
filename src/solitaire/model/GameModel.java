package solitaire.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import solitaire.card.Card;
import solitaire.card.Deck;
import solitaire.move.DeckMove;
import solitaire.move.Move;

public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private Deck deck = new Deck();
	private Stack<Card> waste;
	private List<GameModelListener> listenerList = new ArrayList<>();
	
	public static GameModel getInstance() {
		return INSTANCE;
	}
	
	public void addListener(GameModelListener listener) {
		listenerList.add(listener);
	}
	
	private void notifyListener() {
		for(GameModelListener listener : listenerList) {
			listener.gameStateChanged();
		}
	}
	
	public Move getDeckMove() {
		return new DeckMove(getInstance());
	}
	
	public void reset() {
		deck.reset();
		waste = new Stack<Card>();
	}

	public boolean discard() {
		if(!this.deck.isEmpty()) {
			waste.add(deck.draw());
			notifyListener();
			return true;
		}
		return false;
	}

	public Card peekWaste() {
		if(waste.isEmpty()) {
			return null;
		}
		return waste.peek();
	}
}
