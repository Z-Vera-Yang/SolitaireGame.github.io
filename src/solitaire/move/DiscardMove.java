package solitaire.move;

import solitaire.model.GameModel;

public class DiscardMove implements Move{
	
	GameModel gameModel;	

	public DiscardMove(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	@Override
	public boolean move() {
		if(gameModel.discard()) {
			
		}
		return false;
	}

	@Override
	public boolean undo() {
		return false;
	}

}
