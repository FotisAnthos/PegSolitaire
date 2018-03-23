package structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import main.Node;

public class IOHandle {

	private String input_path; 
	private String output_path; 

	public IOHandle(String input_path, String output_path) {
		this.input_path = input_path;
		this.output_path = output_path;
	}

	// This method reads a file containing a free cell puzzle and stores the numbers
	// Input:
	//			nothing
	// Output:
	//			true --> Successful read.
	//			false --> Unsuccessful read
	public Data retrieve() {
		int temp;
		int noPoles = 0;
		ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
		Data theData;

		try
		{
			FileReader fileReader = new FileReader(input_path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//for the first line that contains N & M
			String line = bufferedReader.readLine().trim().replaceAll(" ", "");

			@SuppressWarnings("unused")
			int noOfLines = Character.getNumericValue(line.charAt(0));//N is number of lines
			@SuppressWarnings("unused")
			int noOfColumns = Character.getNumericValue(line.charAt(1));//M is number of columns


			//for the rest of lines
			line = bufferedReader.readLine().trim().replaceAll(" ", "");
			
			while( line != null) {
				line = line.trim().replaceAll(" ", "");
				ArrayList<Integer> inner = new ArrayList<Integer>();
				for(int currColumn = 0; currColumn < line.length(); currColumn++) {
					temp = Character.getNumericValue(line.charAt(currColumn));
					inner.add(temp);
					if(temp == 1) noPoles++;
				}
				
				data.add(inner);
				line = bufferedReader.readLine();
			}
			
			theData = new Data(data, noPoles);
			
			bufferedReader.close();
			fileReader.close();
		}catch(IOException i){
			System.out.println("Failed to read file");
			i.printStackTrace();
			return null;
		}
		return theData;
	}

	public boolean save(Node solNode) {
		Stack<Node> solutionSteps = new Stack<Node>();
		List<String> lines = new ArrayList<String>();
		Node tempNode = solNode;
		int stepCount=-1;

		while(tempNode.whoIsTheFather().equals(null)) {//only root has parent == null
			solutionSteps.push(tempNode);
			tempNode = tempNode.whoIsTheFather();
		}

		stepCount = solutionSteps.size()-1;

		lines.add(Integer.toString(stepCount));

		while(!solutionSteps.isEmpty()) {
			lines.add(solutionSteps.pop().getMoveDescription());
		}

		Path file = Paths.get(output_path);
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
			return true;
		} catch (IOException e) {
			System.err.println("IOHandle could not write to output file!!!");
			e.printStackTrace();
			return false;
		} 
	}

}
