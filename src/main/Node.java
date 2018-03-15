package main;

import java.util.ArrayList;

import structures.Data;
import structures.Move;

public class Node {	
	private ArrayList<Node> children;
	private Node parent;
	private Data data;
	private Move move;
	private ArrayList<structures.Move> moves;
	private int heuristicValue;

	public Node(Node parent, Move move, Data data){
		this.parent = parent;
		this.children = null;
		this.move = move;
		this.data = data;
		this.heuristicValue = 0;
	}

	public Move getMove() {
		return move;
	}

	public void addChild(Node aNode){
		children.add(aNode);
	}

	public void addChildren(ArrayList<Node> chilrn) {
		for(Node n : chilrn) {
			children.add(n);
		}
	}

	public int NoOfChildren(){
		return children.size();
	}

	public Node whoIsTheFather(){//Anakin is
		return parent;
	}

	public boolean isTarget() {
		if(data.getNoPoles() == 1) return true;
		return false;
	}
	
	private void calcHeuristicValue() {
		heuristicValue = data.getNoPoles();
	}
	
	public int getHeuristicValue() {
		calcHeuristicValue();
		return heuristicValue;
	}

	public ArrayList<Node> expandNode() {
		ArrayList<Node> newNodes = new ArrayList<Node>();
		possibleMoves();
		for(Move mov : moves) {
			newNodes.add(new Node(parent, mov, mov.getData()));
		}
		return newNodes;
	}

	private ArrayList<Move> possibleMoves(){
		moves = new ArrayList<Move>();

		for(int x=0; x<data.getNoRows(); x++) {
			for(int y=0; y<data.getNoColumns(); y++) {
				if(validHorizontalMoveLeftToRight(x, y)) {
					moves.add(moveHorizontalMoveLeftToRight(x, y));
				}

				if(validHorizontalMoveRightToLeft(x, y)) {
					moves.add(moveHorizontalMoveRightToLeft(x, y));
				}

				if(validVerticalMoveBottomUp(x, y)) {
					moves.add(moveVerticalMoveBottomUp(x, y));
				}

				if(validVerticalMoveUpBottom(x, y)) {
					moves.add(moveVerticalMoveUpBottom(x, y));
				}
			}
		}
		return moves;
	}
	
	//****************Creation of valid moves*****************//
	private Move moveHorizontalMoveLeftToRight(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];

		d[x][y] = 2;
		d[x][y+1] = 2;
		d[x][y+2] = 1;
		String moveDef = x + " " + y + " " + x + " " + (y+2);
		Data tempData = new Data(d, data.getNoPoles()-1);

		Move move = new Move(parent, moveDef, tempData);	
		return move;
	}

	private Move moveHorizontalMoveRightToLeft(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];
		
		d[x][y] = 2;
		d[x][y-1] = 2;
		d[x][y-2] = 1;
		
		String moveDef = x + " " + y + " " + x + " " + (y-2);
		Data tempData = new Data(d, data.getNoPoles()-1);

		Move move = new Move(parent, moveDef, tempData);	
		return move;
	}

	private Move moveVerticalMoveBottomUp(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];
		
		d[x][y] = 2;
		d[x+1][y] = 2;
		d[x+2][y] = 1;
		
		String moveDef = x + " " + y + " " + (x+2) + " " + y;
		Data tempData = new Data(d, data.getNoPoles()-1);

		Move move = new Move(parent, moveDef, tempData);	
		return move;
	}

	private Move moveVerticalMoveUpBottom(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];
		
		d[x][y] = 2;
		d[x-1][y] = 2;
		d[x-2][y] = 1;
		
		String moveDef = x + " " + y + " " + (x-2) + " " + y;
		Data tempData = new Data(d, data.getNoPoles()-1);

		Move move = new Move(parent, moveDef, tempData);	
		return move;
	}
	//****************End of creation of valid moves*****************//
	
	//****************Moves validation*****************//
	private boolean validHorizontalMoveLeftToRight(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];
		if((d[x][y] == 1) && (d[x][y+1] == 1 && (d[x][y+2] == 2)))
			return true;
		return false;
	}

	private boolean validHorizontalMoveRightToLeft(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];
		if((d[x][y] == 1) && (d[x][y-1] == 1 && (d[x][y-2] == 2)))
			return true;
		return false;
	}

	private boolean validVerticalMoveBottomUp(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];
		if((d[x][y] == 1) && (d[x+1][y] == 1 && (d[x+2][y] == 2)))
			return true;
		return false;
	}

	private boolean validVerticalMoveUpBottom(int x, int y) {
		int[][] d = new int[data.getNoRows()][data.getNoColumns()];
		if((d[x][y] == 1) && (d[x-1][y] == 1 && (d[x-2][y] == 2)))
			return true;
		return false;
	}
	//****************End of Moves validation*****************//

}
