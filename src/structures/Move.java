package structures;

import main.Node;

public class Move {
	//a "log" of a possible move found at SearchTree.possibleMoves
	private String moveDescription;
	private Data data;


	public Move(Node parent, String moveDescription, Data data) {
		this.moveDescription = moveDescription;
		this.data = data;
	}


	public String getMoveDescription() {
		return moveDescription;
	}


	public Data getData() {
		return data;
	}
}
