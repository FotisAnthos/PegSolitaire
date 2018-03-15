package methods;

import java.util.ArrayList;
import java.util.Stack;

import main.Node;

public class DFS {
	Node solutionNode;

	public DFS(Node root) {//Depth First Search
		
		if(root == null) {
			System.err.println("DFS: root in null");
			return;
		}
		
		Stack<Node> fringe = new Stack<Node>();
		//0. place the root in the queue
		fringe.push(root);
		
		while(!fringe.isEmpty()) {
			Node tempNode = fringe.pop();//1. take the first node from the stack

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
