package solitaire.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import solitaire.card.Card;
import solitaire.card.Deck;
import solitaire.model.WorkingStackManager.Workingstack;
import solitaire.move.DiscardMove;
import solitaire.move.Move;

public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private Deck deck = new Deck();
	private Stack<Card> discard;
	private List<GameModelListener> listenerList = new ArrayList<>();
	private WorkingStackManager workingStackManager;
	
	public enum CardDeck implements Location{
		DECK, DISCARD
	}
	
	public GameModel() {
		
	}
	
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
	
	public void reset() {
		deck.reset();
		deck.shuffle();
		discard = new Stack<Card>();
		workingStackManager = new WorkingStackManager(deck);
		notifyListener();
	}

	public boolean discard() {
		if(!this.deck.isEmpty()) {
			discard.add(deck.draw());
			notifyListener();
			return true;
		}
		return false;
	}

	public Card peekDiscard() {
		if(discard.isEmpty()) {
			return null;
		}
		return discard.peek();
	}
	
	public Move getDiscardMove() {
		return new DiscardMove(getInstance());
	}
	
	public boolean canDraw(Location source) {
		if(source.equals(CardDeck.DECK)) {
			if(!deck.isEmpty()) {
				return true;
			}
		}		
		if(source.equals(CardDeck.DISCARD)) {
			if(!discard.isEmpty()) {
				return true;
			}
		}		
		return false;
	}
	
	public Card[] getStack(Workingstack index) {
		Card[] cards = new Card[workingStackManager.getWorkingStack(index).size()];
		for(int i=0; i<workingStackManager.getWorkingStack(index).size(); i++) {
			cards[i] = workingStackManager.getWorkingStack(index).get(i);
		}
		return cards;
	}
}
