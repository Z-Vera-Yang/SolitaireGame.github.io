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
			return true;
		} else {
			if(card.getSuit().ordinal() + workingStacks[index.ordinal()].peek().getSuit().ordinal() % 2 != 0) {
				if(card.getRank().ordinal() == workingStacks[index.ordinal()].peek().getRank().ordinal() - 1) {
					return true;
				}
			}
		}
		return false;
	}
}
