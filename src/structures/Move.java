package structures;

public class Move {
	private String moveDescription;
	private Data data;


	public Move(String moveDescription, Data data) {
		this.moveDescription = moveDescription;
		this.data = data;
	}


	public String getMoveDescription() {
		return moveDescription;
	}
	
	public int getTableElement(int row, int column) {
		return data.getData()[row][column];		
	}
	
	public int[][] getTable(){
		return data.getData();
	}

	public Data getData() {
		return data;
	}
}
