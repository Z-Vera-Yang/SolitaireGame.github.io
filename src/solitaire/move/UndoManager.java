package solitaire.move;

import java.util.Stack;

import solitaire.model.GameModel;

public class UndoManager {
	Stack<Move> history = new Stack<>();
	GameModel gameModel;
	public UndoManager(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	public void addMove(Move move) {
		history.add(move);
	}
	public boolean undo() {
		if(!history.isEmpty()) {
			history.pop().undo();
		}
		return false;
	}
}
