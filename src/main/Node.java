package main;

import java.util.ArrayList;

import structures.Data;
import structures.Move;

public class Node {	
	private ArrayList<Node> children;
	private Node parent;
	private Move move;
	private ArrayList<Move> moves;
	private int heuristicValue;

	public Node(Node parent, Move move){
		this.parent = parent;
		this.children = new ArrayList<Node>();
		this.move = move;
		this.heuristicValue = 0;
		this.moves = new ArrayList<Move>();
	}

	public Move getMove() {
		return move;
	}

	public void addChild(Node aNode){
		children.add(aNode);
	}

	public Node whoIsTheFather(){//Anakin is
		return parent;
	}

	public boolean isTarget() {
		if(move.getData().getNoPoles() == 1) return true;
		return false;
	}

	private void calcHeuristicValue() {
		heuristicValue = move.getData().getNoPoles();
	}

	public int getHeuristicValue() {
		calcHeuristicValue();
		return heuristicValue;
	}

	public ArrayList<Node> expandNode() {
		possibleMoves();

		for(Move mov : moves) {
			children.add(new Node(this, mov));
		}
		return children;
	}

	private ArrayList<Move> possibleMoves(){
		moves = new ArrayList<Move>();

		for(int row=0; row < move.getData().getNoRows(); row++) {
			for(int column=0; column < move.getData().getNoColumns(); column++) {
				if(move.getTableElement(row, column) == 1) {//traverse the data. If it is a pole check for valid moves  
					if(validHorizontalMoveLeft(row, column)) {
						moves.add(moveHorizontalMoveLeft(row, column));
					}

					if(validHorizontalMoveRight(row, column)) {
						moves.add(moveHorizontalMoveRight(row, column));
					}

					if(validVerticalMoveUp(row, column)) {
						moves.add(moveVerticalMoveUp(row, column));
					}

					if(validVerticalMoveBottom(row, column)) {
						moves.add(moveVerticalMoveBottom(row, column));
					}
				}
			}
		}
		return moves;
	}

	//HorizontalMoveLeft
	private boolean validHorizontalMoveLeft(int row, int column) {//searches to the Left of [row][column]
		try {
			if(move.getTableElement(row, column - 1) == 1 && move.getTableElement(row, column - 2) == 2)
				return true;
		}catch (Exception e) {
			//if exception then ignore
		}
		return false;
	}

	private Move moveHorizontalMoveLeft(int row, int column) {
		int[][] d = move.getTable();
		
		d[row][column] = 2;
		d[row][column - 1] = 2;
		d[row][column - 2] = 1;

		String moveDef = row + " " + column + " " + row + " " + (column - 2);
		Data tempData = new Data(d, move.getData().getNoPoles()-1);

		Move move = new Move(moveDef, tempData);	
		return move;
	}

	//HorizontalMoveRight
	private boolean validHorizontalMoveRight(int row, int column) {//searches to the Right of [row][column]
		try {
			if(move.getTableElement(row, column + 1) == 1 && move.getTableElement(row, column + 2) == 2)
				return true;
		}catch (Exception e) {
			//if exception then ignore
		}
		return false;
	}

	private Move moveHorizontalMoveRight(int row, int column) {
		int[][] d = move.getTable();

		d[row][column] = 2;
		d[row][column + 1] = 2;
		d[row][column + 2] = 1;

		String moveDef = row + " " + column + " " + row + " " + (column + 2);
		Data tempData = new Data(d, move.getData().getNoPoles()-1);

		Move move = new Move(moveDef, tempData);	
		return move;
	}
	//VerticalMoveUp
	private boolean validVerticalMoveUp(int row, int column) {//searches up from [row][column]
		try {
			if(move.getTableElement(row - 1, column) == 1 && move.getTableElement(row - 2, column) == 2)
				return true;
		}catch (Exception e) {
			//if exception then ignore
		}
		return false;
	}

	private Move moveVerticalMoveUp(int row, int column) {
		int[][] d = move.getTable();

		d[row][column] = 2;
		d[row - 1][column] = 2;
		d[row - 2][column] = 1;

		String moveDef = row + " " + column + " " + (row - 2) + " " + column;
		Data tempData = new Data(d, move.getData().getNoPoles()-1);

		Move move = new Move(moveDef, tempData);	
		return move;
	}

	//VerticalMoveBottom
	private boolean validVerticalMoveBottom(int row, int column) {//searches down from [row][column]
		try {
			if(move.getTableElement(row + 1, column) == 1 && move.getTableElement(row + 2, column) == 2)
				return true;
		}catch (Exception e) {
			//if exception then ignore
		}
		return false;
	}

	private Move moveVerticalMoveBottom(int row, int column) {
		int[][] d = new int[move.getData().getNoRows()][move.getData().getNoColumns()]; 
		d= move.getTable();

		d[row][column] = 2;
		d[row + 1][column] = 2;
		d[row + 2][column] = 1;

		String moveDef = row + " " + column + " " + (row + 2) + " " + column;
		int noPoles = move.getData().getNoPoles()-1;
		Data tempData = new Data(d, noPoles);

		Move move = new Move(moveDef, tempData);	
		return move;
	}

	public void printTrableau() {
		System.out.println("*");
		for(int row=0; row < move.getData().getNoRows(); row++) {
			for(int column=0; column < move.getData().getNoColumns(); column++) {
				System.out.print(move.getTableElement(row, column));
			}
			System.out.println();
		}
	}







}
