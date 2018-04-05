package main;

import java.util.Stack;
import methods.*;
import structures.Data;
import structures.IOHandle;

public class Game {

	private IOHandle io;

	private String method;

	public Game(String input_path, String output_path, String method) {//Game constructor //reads input file and starts the search
		this.method = method;

		io = new IOHandle(input_path, output_path);
		Data data = io.retrieve();
		if(data == null) System.err.println("Data is null!!!");

		startSearch(data);
	}

	private void startSearch(Data data) { //starts the kind of search that was requested and sends the results to be saved in the output file
		Node root = new Node(null, "root", data);
		
		System.out.println("Solving Peg Solitaire puzzle with method: " + this.method + " first ...\n");
		long tStart = System.currentTimeMillis();	//Marking the start of the attempt

		if(method.equals("breadth")) {
			BFS bfs = new BFS(root);
			Node sol = bfs.startSearch();
			if(sol == null) {
				System.out.println("Solution Not Found!!");
				System.exit(2);
			}
			io.save(sol);
			//printSolutionDebug(bfs.getSolutionNode());
		}
		else if(method.equals("depth")) {
			DFS dfs = new DFS(root);
			Node sol = dfs.startSearch();
			//printSolutionDebug(sol);
			if(sol == null) {
				System.out.println("Solution Not Found!!");
				System.exit(2);
			}
			io.save(sol);
		}
		else if(method.equals("best")) {
			BestFirst bestFirst = new BestFirst(root);
			Node sol = bestFirst.startSearch();
			if(sol == null) {
				System.out.println("Solution Not Found!!");
				System.exit(2);
			}
			io.save(sol);
			//printSolutionDebug(sol);
		}
		else {
			System.out.println("Methods: chosenMethod not recognised");
			System.exit(3);
		}

		long tEnd = System.currentTimeMillis(); 	//Marking the end of the attempt 
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println("Solving free Cell puzzle with " + this.method + " took " + Double.toString(elapsedSeconds) + "minutes.");
	}

	public void printSolutionDebug(Node solNode) {//prints to the console the same data that are written in the output file 
		Stack<Node> solutionSteps = new Stack<Node>();
		Node tempNode = solNode;
		int stepCount=-1;

		while(true) {//only root has parent == null
			if(tempNode.getMoveDescription().equals("root")) {
				break;
			}
			solutionSteps.push(tempNode);
			tempNode = tempNode.whoIsTheFather();
		}

		stepCount = solutionSteps.size();
		System.out.println("***Solution Start***");
		System.out.println(stepCount);

		while(!solutionSteps.isEmpty()) {
			System.out.println(solutionSteps.pop().getMoveDescription());
		}
		System.out.println("***Solution End***");
	}
}
