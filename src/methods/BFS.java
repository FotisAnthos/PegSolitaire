package methods;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import main.Node;

public class BFS {
	Node solutionNode;

	public BFS(Node root) {//Breadth First Search
		
		if(root == null) {
			System.err.println("DFS: root in null");
			return;
		}
		
		Queue<Node> fringe = new PriorityQueue<Node>();
		Node tempNode;
		//0. place the root in the queue
		fringe.add(root);

		while (!fringe.isEmpty()) {
			tempNode = fringe.poll();//1. take the first node from the queue

			if(tempNode.isTarget()) {//2. check if the node is a target node
				solutionNode = tempNode;//2a. if yes appoint solution and return
				return;
			}
			//2b. else expand and repeat
			ArrayList<Node> newNodes = tempNode.expandNode();
			for(Node n : newNodes) {
				fringe.add(n);
			}
		}
	}
	
	public Node getSolutionNode() {
		if(solutionNode.equals(null)) System.err.println("DFS solutionNode is null!!");
		return solutionNode;
	}
}
