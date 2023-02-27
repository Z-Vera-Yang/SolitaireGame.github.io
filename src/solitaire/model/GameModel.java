package solitaire.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import solitaire.card.Card;
import solitaire.card.Deck;
import solitaire.model.SuitStackManager.SuitStack;
import solitaire.model.WorkingStackManager.Workingstack;
import solitaire.move.DiscardMove;
import solitaire.move.Move;
import solitaire.move.MultipleCardsMove;
import solitaire.move.OneCardMove;
import solitaire.move.UndoManager;

public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private Deck deck = new Deck();
	private Stack<Card> discard;
	private List<GameModelListener> listenerList = new ArrayList<>();
	private WorkingStackManager workingStackManager;
	private SuitStackManager suitStackManager;
	private UndoManager undoManager = new UndoManager(GameModel.getInstance());
	
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
		suitStackManager = new SuitStackManager();
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
	
	public Card peekSuitStack(SuitStack index) {
		return suitStackManager.viewSuitStack(index);
	}
	
	public Move getDiscardMove() {
		return new DiscardMove(getInstance());
	}
	
	public boolean canDraw(Location location) {
		if(location instanceof Workingstack) {
			if(workingStackManager.canDraw((Workingstack) location)) {
				return true;
			}
		}
		if(location instanceof SuitStack) {
			if(suitStackManager.canDraw(location)) {
				return true;
			}
		}
		if(location.equals(CardDeck.DECK)) {
			if(!deck.isEmpty()) {
				return true;
			}
		}		
		if(location.equals(CardDeck.DISCARD)) {
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
	
	public boolean move(Location source, Location destination, Card card) {
		if(canDraw(source) && canAdd(card, destination)) {
			workingStackManager.addMultiple(workingStackManager.drawMutiple(card, (Workingstack) source), (Workingstack) destination);
			notifyListener();
			return true;
		}
		return false;
	}
	
	public boolean move(Location source, Location destination) {
		if(source instanceof Workingstack && destination instanceof SuitStack) {
			if(canDraw(source) && suitStackManager.canAdd(workingStackManager.getCards((Workingstack) source).peek())) {
				suitStackManager.add(workingStackManager.draw((Workingstack) source));
				notifyListener();
				return true;
			}
		}	
		if(source.equals(CardDeck.DISCARD) && destination instanceof SuitStack) {
			if(canDraw(source) && canAdd(discard.peek(), destination)) {
				suitStackManager.add(discard.pop());
				notifyListener();
				return true;
			}		
		}
		if(source.equals(CardDeck.DISCARD) && destination instanceof Workingstack) {
			workingStackManager.add(discard.pop(), (Workingstack) destination);
			notifyListener();
			return true;
		}
		if(source instanceof Workingstack && destination instanceof Workingstack) {
			workingStackManager.add(workingStackManager.draw((Workingstack) source), (Workingstack) destination);
			notifyListener();
			return true;
		}
		return false;
	}
	
	public Move getCardMove(Card top, Location destination) {
		if(top.equals(peekDiscard())) {
			return new OneCardMove(CardDeck.DISCARD, destination, getInstance());
		}
		for(Workingstack ws : Workingstack.values()) {
			if(!workingStackManager.getCards(ws).isEmpty() && workingStackManager.getCards(ws).peek().equals(top)) {
				return new OneCardMove(ws, destination, getInstance());
			}
			for(Card c : workingStackManager.getCards(ws)) {
				if(c.equals(top)) {
					return new MultipleCardsMove(ws, destination, c, getInstance());
				}
			}
		}
		return null;
	}
	
	public boolean canAdd(Card top, Location destination) {
		if(destination instanceof Workingstack) {
			if(workingStackManager.canAdd(top, (Workingstack) destination)) {
				return true;
			}
		}
		if(destination instanceof SuitStack) {
			if(suitStackManager.canAdd(top)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean undo(Location source, Location destination) {
		if(source instanceof CardDeck && destination instanceof Workingstack) {
			discard.push(workingStackManager.draw((Workingstack) destination));
			notifyListener();
			return true;
		}
		return false;
	}
	
	public void logMove(Move move) {
		undoManager.addMove(move);
	}
	public void undoLast() {
		undoManager.undo();
	}
}
