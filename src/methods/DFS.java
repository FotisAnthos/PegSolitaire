package methods;

import java.util.ArrayList;
import java.util.Stack;

import main.Node;

public class DFS {
	Node solutionNode, root;
	ArrayList<Node> newNodes;

	public DFS(Node root) {//Depth First Search
		this.solutionNode = null;
		this.root = root;
	}

	public Node startSearch() {
		Node tempNode;
		ArrayList<Node> newNodes = new ArrayList<Node>();
		Stack<Node> fringe = new Stack<Node>();

		if(root == null) {
			System.err.println("DFS: root in null");
			return null;
		}

		//0. place the root in the stack
		fringe.push(root);

		while(!fringe.empty()) {
			tempNode = fringe.pop();//1. take the first node from the stack
			tempNode.printTrableau();
			
			if(tempNode.isTarget()) {//2. check if the node is a target node
				solutionNode = tempNode;//2a. if yes appoint solution and return
				return solutionNode;
			}
			//2b. else expand and repeat
			newNodes = tempNode.expandNode();
			
			for(Node n : newNodes) {
				fringe.push(n);
			}
		}
		
		if(solutionNode == null) { 
			System.err.println("DFS: no solution found!!");
			System.exit(4);
		}
		
		return null;
	}

	public Node getSolutionNode() {
		return this.solutionNode;
	}
}
