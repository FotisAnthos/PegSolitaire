package methods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import main.Node;

public class BestFirst {
	Node solutionNode, root;

	public BestFirst(Node root) {//BestFirst First Search
		this.solutionNode = null;
		this.root = root;	
	}

	public Node startSearch() {
		if(root == null) {
			System.err.println("Best First: root is null");
			return null;
		}

		//message what center is to be used for heuristic calculation
		int center_row = root.getData().getNoRows() / 2;
		int center_column = root.getData().getNoColumns() / 2 + 1;
		System.out.println("*** Center to be used: " + center_row + ", " + center_column + "***"); 

		Comparator<Node> comparator = new heuristicComparator();
		Queue<Node> fringe = new PriorityQueue<Node>(comparator);//PriorityQueue will sort the nodes based on their heuristic(min to max)
		//0. place the root in the queue
		fringe.add(root);

		while(!fringe.isEmpty()) {
			Node tempNode = fringe.poll();//1. take the node with the lowest heuristic value from the queue

			if(tempNode.isTarget()) {//2. check if the node is a target node
				solutionNode = tempNode;//2a. if yes appoint solution and return
				return solutionNode;
			}
			//2b. else expand and repeat
			ArrayList<Node> newNodes = tempNode.expandNode(true);
			for(Node n : newNodes) {
				fringe.add(n);//add the new node to the queue
			}
		}
		if(solutionNode == null) { 
			System.err.println("BestFirst: solution not found!!");
			System.exit(4);
		}
		return null;
	}

	class heuristicComparator implements Comparator<Node>
	{
		@Override
		public int compare(Node xNode, Node yNode)
		{
			return xNode.getHeuristicValue() - yNode.getHeuristicValue();
		}
	}
}
