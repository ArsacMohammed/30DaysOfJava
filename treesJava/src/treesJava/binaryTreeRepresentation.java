package treesJava;

public class binaryTreeRepresentation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = new Node (1);
		root.left= new Node (2);
		root.right=new Node (3);
		root.left.left= new Node (4);
		root.left.right= new Node (5);
	    root.right.left= new Node (6);
	    root.right.right= new Node(7);

	}

}
