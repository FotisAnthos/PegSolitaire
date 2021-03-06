package structures;

import java.util.ArrayList;

public class Data {
	private ArrayList<ArrayList<Integer>> data;
	private int noPoles;

	public Data(ArrayList<ArrayList<Integer>> data, int noPoles) {
		this.data = data;
		this.noPoles = noPoles;
	}

	public int getTableElement(int row, int column) {
		if(row < 0 || row > data.size()-1) return -1;
		if(column < 0 || column > data.get(row).size()-1) return -1;
		return data.get(row).get(column);
	}

	public ArrayList<ArrayList<Integer>> getData() {//duplicate the data in the ArrayLists and return the result
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();//didn't find another way of doing a deep copy of the Data
		for(int i= 0; i < data.size(); i++) {
			ArrayList<Integer> inner = new ArrayList<Integer>();
			for(int j= 0; j < data.get(i).size(); j++) {
				inner.add(data.get(i).get(j));
			}
			temp.add(inner);
		}
		return temp;
	}

	public void setDataElement(int row, int column, int value) {
		this.data.get(row).set(column, value);
	}

	public int getNoPoles() {
		return noPoles;
	}

	public int getNoRows() {
		return data.size();
	}

	public int getNoColumns() {
		return data.get(0).size();
	}

	public void decreasePoles() {
		this.noPoles--;
	}
}
