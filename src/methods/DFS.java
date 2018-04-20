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

		if(root == null) {//just checking
			System.err.println("DFS: root is null");
			return null;
		}

		//0. place the root in the stack
		fringe.push(root);//place root Node in the Stack

		while(!fringe.empty()) {
			tempNode = fringe.pop();//1. take the first node from the stack

			if(tempNode.isTarget()) {//2. check if the node is a target node
				solutionNode = tempNode;//2a. if yes appoint solution and return
				return solutionNode;
			}
			//2b. else expand and repeat
			newNodes = tempNode.expandNode(false);

			for(Node n : newNodes) {
				fringe.push(n);//add the new Nodes in the Stack
			}
		}
		return null;
	}
}

