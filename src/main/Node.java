package main;

import java.util.ArrayList;
import structures.Data;

public class Node {	
	private ArrayList<Node> children;
	private Node parent;
	private String moveDescription;
	private Data data;
	private int heuristicValue;

	public Node(Node parent, String moveDescription, Data data){
		this.parent = parent;
		this.children = new ArrayList<Node>();
		this.moveDescription = moveDescription;
		this.data = data;
		this.heuristicValue = 0;
	}

	public void addChild(Node aNode){
		children.add(aNode);
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

	public ArrayList<Node> expandNode(){

		for(int row=0; row < data.getNoRows()-1; row++) {
			for(int column=0; column < data.getNoColumns()-1; column++) {
				if(data.getTableElement(row, column) == 1) {//traverse the data. If it is a pole check for valid moves  
					if(validHorizontalMoveLeft(row, column)) {
						children.add(moveHorizontalMoveLeft(row, column));
					}
					if(validHorizontalMoveRight(row, column)) {
						children.add(moveHorizontalMoveRight(row, column));
					}
					if(validVerticalMoveUp(row, column)) {
						children.add(moveVerticalMoveUp(row, column));
					}
					if(validVerticalMoveBottom(row, column)) {
						children.add(moveVerticalMoveBottom(row, column));
					}
				}
			}
		}
		return children;
	}

	//HorizontalMoveLeft
	private boolean validHorizontalMoveLeft(int row, int column) {//searches to the Left of [row][column]
		if(data.getTableElement(row, column - 1) == 1 && data.getTableElement(row, column - 2) == 2)
			return true;
		return false;
	}

	private Node moveHorizontalMoveLeft(int row, int column) {
		Data tempData = new Data(data.getData(), data.getNoPoles());

		tempData.setDataElement(row, column, 2);
		tempData.setDataElement(row, column-1, 2);
		tempData.setDataElement(row, column-2, 1);
		tempData.decreasePoles();

		String moveDef = row + " " + column + " " + row + " " + (column - 2);

		Node node = new Node(this, moveDef, tempData);	
		return node;
	}

	//HorizontalMoveRight
	private boolean validHorizontalMoveRight(int row, int column) {//searches to the Right of [row][column]
		if(data.getTableElement(row, column + 1) == 1 && data.getTableElement(row, column + 2) == 2)
			return true;
		return false;
	}

	private Node moveHorizontalMoveRight(int row, int column) {
		Data tempData = new Data(data.getData(), data.getNoPoles());

		tempData.setDataElement(row, column, 2);
		tempData.setDataElement(row, column+1, 2);
		tempData.setDataElement(row, column+2, 1);
		tempData.decreasePoles();

		String moveDef = row + " " + column + " " + row + " " + (column + 2);

		Node node = new Node(this, moveDef, tempData);	
		return node;
	}
	//VerticalMoveUp
	private boolean validVerticalMoveUp(int row, int column) {//searches up from [row][column]
		if(data.getTableElement(row - 1, column) == 1 && data.getTableElement(row - 2, column) == 2)
			return true;
		return false;
	}

	private Node moveVerticalMoveUp(int row, int column) {
		Data tempData = new Data(data.getData(), data.getNoPoles());

		tempData.setDataElement(row, column, 2);
		tempData.setDataElement(row-1, column, 2);
		tempData.setDataElement(row-2, column, 1);
		tempData.decreasePoles();

		String moveDef = row + " " + column + " " + (row - 2) + " " + column;

		Node node = new Node(this, moveDef, tempData);	
		return node;
	}

	//VerticalMoveBottom
	private boolean validVerticalMoveBottom(int row, int column) {//searches down from [row][column]
		if(data.getTableElement(row + 1, column) == 1 && data.getTableElement(row + 2, column) == 2)
			return true;

		return false;
	}

	private Node moveVerticalMoveBottom(int row, int column) {
		Data tempData = new Data(data.getData(), data.getNoPoles());

		tempData.setDataElement(row, column, 2);
		tempData.setDataElement((row + 1), column, 2);
		tempData.setDataElement((row + 2), column, 1);
		tempData.decreasePoles();

		String moveDef = row + " " + column + " " + (row + 2) + " " + column;

		Node node = new Node(this, moveDef, tempData);	
		return node;
	}

	public void printTrableau() {
		System.out.println(moveDescription);
		for(int row=0; row < data.getNoRows(); row++) {
			for(int column=0; column < data.getNoColumns(); column++) {
				System.out.print(data.getTableElement(row, column));
			}
			System.out.println();
		}
		
	}

	public String getMoveDescription() {
		return moveDescription;
	}

}
