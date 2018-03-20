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
			System.err.println("Best First: root in null");
			return null;
		}
		Comparator<Node> comparator = new heuristicComparator();
		Queue<Node> fringe = new PriorityQueue<Node>(comparator);
		//0. place the root in the queue
		calcHeuristicValue(root);//no real need(?) //TODO
		fringe.add(root);

		while(!fringe.isEmpty()) {
			Node tempNode = fringe.poll();//1. take the node with the lowest heuristic value from the queue

			if(tempNode.isTarget()) {//2. check if the node is a target node
				solutionNode = tempNode;//2a. if yes appoint solution and return
				return solutionNode;
			}
			//2b. else expand and repeat
			ArrayList<Node> newNodes = tempNode.expandNode();
			for(Node n : newNodes) {
				calcHeuristicValue(n);
				fringe.add(n);
			}
		}
		if(solutionNode == null) { 
			System.err.println("BestFirst: solution not found!!");
			System.exit(4);
		}
		return null;
	}

	private void calcHeuristicValue(Node aNode) {
		//TODO
	}

	public Node getSolutionNode() {		
		return solutionNode;
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
