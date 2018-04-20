package main;

import java.util.ArrayList;
import structures.Data;

public class Node {	
	private ArrayList<Node> children;
	private Node parent;
	private String moveDescription;
	private Data data;//the positions of the poles 0-> N/A position, 2-> free position, 1->pole in position
	private int heuristicValue;

	public Node(Node parent, String moveDescription, Data data, int heur){
		this.parent = parent;
		this.children = new ArrayList<Node>();
		this.moveDescription = moveDescription;
		this.data = data;
		this.heuristicValue = heur;
	}

	public Node(Data data){//Only for the root Node
		this.parent = null;
		this.children = new ArrayList<Node>();
		this.moveDescription = "root";
		this.data = data;
		this.heuristicValue = 0;

		for(int row=0; row < data.getNoRows()-1; row++) {
			for(int column=0; column < data.getNoColumns()-1; column++) {
				if(data.getTableElement(row, column) == 1) {//traverse the data. If it is a pole calculate heuristic
					this.heuristicValue += calcHeuristicValue(row, column);
				}
			}
		}

	}

	public ArrayList<Node> expandNode(Boolean calc){//if true, heuristic values will be calculated

		for(int row=0; row < data.getNoRows()-1; row++) {
			for(int column=0; column < data.getNoColumns()-1; column++) {
				if(data.getTableElement(row, column) == 1) {//traverse the data. If it is a pole check for valid moves  
					if(validHorizontalMoveLeft(row, column)) {//if there is a valid move to the Left
						children.add(moveHorizontalMoveLeft(row, column, calc));//prepare a Node depicting the move and add it to this Node's children
					}
					if(validHorizontalMoveRight(row, column)) {
						children.add(moveHorizontalMoveRight(row, column, calc));
					}
					if(validVerticalMoveUp(row, column)) {
						children.add(moveVerticalMoveUp(row, column, calc));
					}
					if(validVerticalMoveBottom(row, column)) {
						children.add(moveVerticalMoveBottom(row, column, calc));
					}

				}
			}
		}
		return children;
	}

	//HorizontalMoveLeft
	private boolean validHorizontalMoveLeft(int row, int column) {//searches to the Left of [row][column]
		if(data.getTableElement(row, column - 1) == 1 && data.getTableElement(row, column - 2) == 2)
			return true;//the row/position is definitely 1 by now, it is checked in the expandNode method
		return false;
	}

	private Node moveHorizontalMoveLeft(int row, int column, boolean calc) {
		Data tempData = new Data(data.getData(), data.getNoPoles());//prepare an image of the data based on the parent

		tempData.setDataElement(row, column, 2);	//make the changes that represent the new data image
		tempData.setDataElement(row, column-1, 2);
		tempData.setDataElement(row, column-2, 1);
		tempData.decreasePoles();//decrease pole number by 1

		String moveDef = row + " " + column + " " + row + " " + (column - 2);

		int heur = 0;
		int pastHeur = 0;
		int newHeur = 0;
		if(calc){
			pastHeur = calcHeuristicValue(row, column-1) + calcHeuristicValue(row, column);
			newHeur = calcHeuristicValue(row, column-2);
			heur = heuristicValue - pastHeur + newHeur;
		}
		Node node = new Node(this, moveDef, tempData, heur);
		return node;
	}

	//HorizontalMoveRight
	private boolean validHorizontalMoveRight(int row, int column) {//searches to the Right of [row][column]
		if(data.getTableElement(row, column + 1) == 1 && data.getTableElement(row, column + 2) == 2)
			return true;
		return false;
	}

	private Node moveHorizontalMoveRight(int row, int column, boolean calc) {
		Data tempData = new Data(data.getData(), data.getNoPoles());//prepare an image of the data based on the parent

		tempData.setDataElement(row, column, 2);		//make the changes that represent the new data image
		tempData.setDataElement(row, column+1, 2);
		tempData.setDataElement(row, column+2, 1);
		tempData.decreasePoles();//decrease pole number by 1

		String moveDef = row + " " + column + " " + row + " " + (column + 2);

		int heur = 0;
		int pastHeur = 0;
		int newHeur = 0;
		if(calc){
			pastHeur = calcHeuristicValue(row, column+1) + calcHeuristicValue(row, column);
			newHeur = calcHeuristicValue(row, column+2);
			heur = heuristicValue - pastHeur + newHeur;
		}
		Node node = new Node(this, moveDef, tempData, heur);	
		return node;
	}

	//VerticalMoveUp
	private boolean validVerticalMoveUp(int row, int column) {//searches up from [row][column]
		if(data.getTableElement(row - 1, column) == 1 && data.getTableElement(row - 2, column) == 2)
			return true;
		return false;
	}

	private Node moveVerticalMoveUp(int row, int column, boolean calc) {
		Data tempData = new Data(data.getData(), data.getNoPoles());//prepare an image of the data based on the parent

		tempData.setDataElement(row, column, 2);		//make the changes that represent the new data image
		tempData.setDataElement(row-1, column, 2);
		tempData.setDataElement(row-2, column, 1);
		tempData.decreasePoles();//decrease pole number by 1

		String moveDef = row + " " + column + " " + (row - 2) + " " + column;

		int heur = 0;
		int pastHeur = 0;
		int newHeur = 0;
		if(calc){
			pastHeur = calcHeuristicValue(row-1, column) + calcHeuristicValue(row, column);
			newHeur = calcHeuristicValue(row-2, column);
			heur = heuristicValue - pastHeur + newHeur;
		}
		Node node = new Node(this, moveDef, tempData, heur);

		return node;
	}

	//VerticalMoveBottom
	private boolean validVerticalMoveBottom(int row, int column) {//searches down from [row][column]
		if(data.getTableElement(row + 1, column) == 1 && data.getTableElement(row + 2, column) == 2)
			return true;
		return false;
	}

	private Node moveVerticalMoveBottom(int row, int column, boolean calc) {
		Data tempData = new Data(data.getData(), data.getNoPoles());//prepare an image of the data based on the parent

		tempData.setDataElement(row, column, 2);		//make the changes that represent the new data image
		tempData.setDataElement((row + 1), column, 2);
		tempData.setDataElement((row + 2), column, 1);
		tempData.decreasePoles();//decrease pole number by 1

		String moveDef = row + " " + column + " " + (row + 2) + " " + column;

		int heur = 0;
		int pastHeur = 0;
		int newHeur = 0;
		if(calc){
			pastHeur = calcHeuristicValue(row+1, column) + calcHeuristicValue(row, column);
			newHeur = calcHeuristicValue(row+2, column);
			heur = heuristicValue - pastHeur + newHeur;
		}
		Node node = new Node(this, moveDef, tempData, heur);

		return node;
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

	private int calcHeuristicValue(int row, int column) {
		//int center_row = data.getNoRows() / 2;
		//int center_column = data.getNoColumns() / 2 + 1;
		//heuristicValue = Math.abs( center_row - center_column ) + Math.abs(row - column); 
		return Math.abs( (data.getNoRows()/2)-(data.getNoColumns()/2+1) ) + Math.abs(row - column); //as a heuristic, distance from a center is calculated as follows: heuristic = |center_X - center_Y| + |pole_Xpos - pole_Ypos|
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public String getMoveDescription() {
		return moveDescription;
	}

	public void printTrableau() {//prints the position data if requested
		System.out.println(moveDescription);
		for(int row=0; row < data.getNoRows(); row++) {
			for(int column=0; column < data.getNoColumns(); column++) {
				System.out.print(data.getTableElement(row, column));
			}
			System.out.println();
		}
	}

	public Data getData() {
		return data;
	}

}
