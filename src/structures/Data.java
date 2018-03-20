package structures;

public class Data {
	private int[][] data;
	private int noPoles;
	private int noRows;
	private int noColumns;
	
	public Data(int n, int m, int[][] data, int noPoles) {//Used only once, when problem is read from input
		this.data = data;
		this.noPoles = noPoles;
		this.noRows = n;
		this.noColumns = m;
	}

	public Data(int[][] data, int noPoles) {//To be used when calculating new moves
		this.data = data;
		this.noPoles = noPoles;
	}

	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
	}

	public int getNoPoles() {
		return noPoles;
	}

	public void setNoPoles(int noPoles) {
		this.noPoles = noPoles;
	}

	public int getNoRows() {
		return noRows;
	}

	public int getNoColumns() {
		return noColumns;
	}

}
