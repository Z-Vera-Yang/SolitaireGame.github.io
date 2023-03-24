package solitaire.model;

import java.util.Stack;
import solitaire.card.Card;
import solitaire.card.Deck;

public class WorkingStackManager {
	public enum Workingstack implements Location {
		Stack1, Stack2, Stack3, Stack4, Stack5, Stack6, Stack7
	}
	
	private final WorkingStack[] workingStacks = new WorkingStack[Workingstack.values().length];
	
	public WorkingStackManager(Deck deck) {
		for(int i=0; i<workingStacks.length; i++) {
			workingStacks[i] = new WorkingStack(deck, Workingstack.Stack1.ordinal() + 1 + i);
		}
	}
	
	public Stack<Card> getWorkingStack(Workingstack index) {
		Stack<Card> stack = new Stack<>();
		for(Card card : this.workingStacks[index.ordinal()]) {
			stack.push(card);
		}
		return stack;
	}
	
	public void add(Card card, Workingstack index) {
		workingStacks[index.ordinal()].push(card);
	}
	
	public boolean canAdd(Card card, Workingstack index) {
		assert card != null; 
		if(workingStacks[index.ordinal()].isEmpty()) {
			if(card.getRank().ordinal() == 12) {
				return true;
			}			
		} else {
			// rules for moving card to working stack
			if((card.getSuit().ordinal() + workingStacks[index.ordinal()].peek().getSuit().ordinal()) % 2 != 0) {
				if(card.getRank().ordinal() == workingStacks[index.ordinal()].peek().getRank().ordinal() - 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Card draw(Workingstack index) {
		Card card = workingStacks[index.ordinal()].draw();
		return card;
	}
	
	public Stack<Card> getCards(Workingstack index) {
		Stack<Card> stack = new Stack<>();
		for(Card card : workingStacks[index.ordinal()]) {
			stack.push(card);
		}
		return stack;
	}
	
	public boolean canDraw(Workingstack index) {
		if(workingStacks[index.ordinal()].isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void addMultiple(Stack<Card> stack, Workingstack index) {
		assert canAdd(stack.lastElement(), index);
		while(!stack.isEmpty()) {
			workingStacks[index.ordinal()].push(stack.pop());
		}
	}
	
	public Stack<Card> drawMutiple(Card card, Workingstack index) {
		assert canDraw(index);
		Stack<Card> stack = new Stack<>();
		while(card != workingStacks[index.ordinal()].peek()) {
			stack.push(workingStacks[index.ordinal()].draw());
		}
		stack.push(workingStacks[index.ordinal()].draw());
		return stack;
	}
}
