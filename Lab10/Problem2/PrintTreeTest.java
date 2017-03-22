
public class PrintTreeTest {
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(55);
		tree.add(12);
		tree.add(30);
		tree.add(97);
		System.out.println(tree.toTreeString());
	}
}
