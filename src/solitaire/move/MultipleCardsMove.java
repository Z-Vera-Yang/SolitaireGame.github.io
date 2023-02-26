package solitaire.move;

import solitaire.card.Card;
import solitaire.model.GameModel;
import solitaire.model.Location;

public class MultipleCardsMove implements Move{
	private Location source;
	private Location destination;
	private Card card;
	GameModel gameModel;
	public MultipleCardsMove(Location source, Location destination, Card card, GameModel gameModel) {
		this.source = source;
		this.destination = destination;
		this.card = card;
		this.gameModel = gameModel;
	}
	@Override
	public boolean move() {
		boolean success = gameModel.move(source, destination, card);
		if(success) return true;
		return false;
	}
}
