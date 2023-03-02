package solitaire.card;

import java.util.Collections;
import java.util.Stack;

import solitaire.card.Card.Rank;
import solitaire.card.Card.Suit;

public class Deck {
	
	private Stack<Card> cards = new Stack<>();
	
	public void reset() {
		cards.clear();
		for(Suit suit: Suit.values()) {
			for(Rank rank: Rank.values()) {
				cards.add(Card.flyWeightFactory(rank, suit));
			}
		}		
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	public void push(Card card) {
		cards.push(card);
	}
	
	public Card draw() {
		assert !isEmpty();
		return this.cards.pop();
	}

	public void shuffle() {
		Collections.shuffle(cards);		
	}
	
	public int size() {
		return cards.size();
	}
}
