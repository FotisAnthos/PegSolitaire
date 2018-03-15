package methods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import main.Node;

public class BestFirst {
	Node solutionNode;

	public BestFirst(Node root) {//BestFirst First Search

		if(root == null) {
			System.err.println("Best First: root in null");
			return;
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
				return;
			}
			//2b. else expand and repeat
			ArrayList<Node> newNodes = tempNode.expandNode();
			for(Node n : newNodes) {
				calcHeuristicValue(n);
				fringe.add(n);
			}
		}
	}

	private void calcHeuristicValue(Node aNode) {
		//TODO
	}

	public Node getSolutionNode() {
		if(solutionNode.equals(null)) System.err.println("BestFirst solutionNode is null!!");
		return solutionNode;
	}

	class heuristicComparator implements Comparator<Node>
	{
		@Override
		public int compare(Node x, Node y)
		{
			return x.getHeuristicValue() - y.getHeuristicValue();
		}
	}

}
