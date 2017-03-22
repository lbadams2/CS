
public class BSTArray_Driver {

	public static void main(String[] args)
	{
		BinarySearchTree<Integer> nodeTree = new BinarySearchTree<Integer>();
		nodeTree.add(14);
		nodeTree.add(8);
		nodeTree.add(12);
		nodeTree.add(10);
		nodeTree.add(31);
		nodeTree.add(40);
		nodeTree.add(18);
		nodeTree.add(30);
		nodeTree.add(2);
		nodeTree.add(20);
		System.out.println("Iterative InOrder Traversal");
		nodeTree.inOrderIter();
		System.out.println("\nRecursive InOrder Traversal");
		nodeTree.inorder();
	}
}
