package methods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import main.Node;

public class BFS {
	Node solutionNode, root;

	public BFS(Node root) {//Breadth First Search
		this.solutionNode = null;
		this.root = root;
	}

	public Node startSearch() {
		if(root == null) {
			System.err.println("BFS: root is null");
			return null;
		}

		Queue<Node> fringe = new LinkedList<Node>();//create the Queue
		Node tempNode;
		//0. place the root in the queue
		fringe.add(root);

		while (!fringe.isEmpty()) {
			tempNode = fringe.poll();//1. take the first node from the queue

			if(tempNode.isTarget()) {//2. check if the node is a target node
				solutionNode = tempNode;//2a. if yes appoint solution and return
				return solutionNode;
			}
			//2b. else expand and repeat
			ArrayList<Node> newNodes = tempNode.expandNode();
			for(Node n : newNodes) {
				fringe.add(n);//add the new Nodes to the queue
			}
		}
		return null;
	}
}

