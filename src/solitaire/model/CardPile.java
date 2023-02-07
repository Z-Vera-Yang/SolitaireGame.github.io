package solitaire.model;

import java.util.ArrayList;

import javax.swing.JLayeredPane;

import solitaire.model.Card.Suit;

public class CardPile extends JLayeredPane {
	
	Card base;
	ArrayList<Card> cards;
	int offset = 15;
	Suit suitFilter;
	int width;
	CardPile parent;
	PileType type;
	
	enum PileType {Normal, Draw, Get, Final};
	
	public CardPile(int width) {
		
	}

}
