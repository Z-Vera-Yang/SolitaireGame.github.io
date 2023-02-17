package solitaire.move;

import solitaire.model.GameModel;

public class DeckMove implements Move{
	
	GameModel gameModel;	

	public DeckMove(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	@Override
	public void move() {
		if(gameModel.discard()) {
			
		}		
	}

}
