package solitaire.model;

import java.util.Iterator;
import java.util.Stack;

import solitaire.card.Card;
import solitaire.card.Deck;

public class WorkingStack implements Iterable<Card>{
	
	private final Stack<Card> workingStack = new Stack<>();
	
	public WorkingStack(Deck deck, int num) {
		for(int i=0; i<num; i++) {
			Card curr = deck.draw();
			if (curr.isFaceUp()) {
				curr.flip();
			}
			workingStack.add(curr);
		}
	}
	
	public void push(Card card) {
		workingStack.push(card);
	}
	
	public boolean isEmpty() {
		return workingStack.isEmpty();
	}
	
	public Card peek() {
		assert !workingStack.isEmpty();
		return workingStack.peek();
	}
	
	public Card draw() {
		assert !workingStack.isEmpty();
		return workingStack.pop();
	}

	@Override
	public Iterator<Card> iterator() {
		return workingStack.iterator();
	}

}
