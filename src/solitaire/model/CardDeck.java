package solitaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import solitaire.model.Card.Suit;

public class CardDeck {
	
	public ArrayList<Card> cards;
	
	public Card drawCard() {
		Card c = cards.get(0);
		cards.remove(0);

		return c;
	}
	public CardDeck() {
		cards = new ArrayList<Card>();
		
		for(Suit suit : Suit.values()) {
			for(int value = 1; value <= 14; ++value) {
				if(value != 11)
					cards.add(new Card(value, suit));
			}
		}
		
	}
	public void shuffle() {
		Random randIndex = new Random();
		int size = cards.size();		
		for(int shuffles = 1; shuffles <= 20; ++shuffles)
			for (int i = 0; i < size; i++) 
				Collections.swap(cards, i, randIndex.nextInt(size));		
	}
}
