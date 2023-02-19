package solitaire.model;

import java.util.Stack;
import solitaire.card.Card;
import solitaire.card.Deck;

public class WorkingStackManager {
	public enum Workingstack{
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
}
