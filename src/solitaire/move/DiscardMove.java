package solitaire.move;

import solitaire.model.GameModel;

public class DiscardMove implements Move{
	
	GameModel gameModel;	

	public DiscardMove(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	@Override
	public void move() {
		if(gameModel.discard()) {
			
		}		
	}

}
