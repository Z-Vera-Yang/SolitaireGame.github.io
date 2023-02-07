package solitaire.controller;

import java.util.ArrayList;

import solitaire.model.CardDeck;
import solitaire.model.CardPile;


public class Controller {
	
	public ArrayList<CardPile> piles;
	public ArrayList<CardPile> finalPiles;
	public CardPile drawPile, getPile;
	public ArrayList<CardPile> allPiles;
	public final int pileNumber = 7;
	public CardDeck deck;
	
	public Controller() {
		
	}
	
	public void setupGame() {
		
	}

}
