
public class BSTArray_Driver {

	public static void main(String[] args)
	{
		BinarySearchTreeArray<String> bst = new BinarySearchTreeArray<String>(7);
		bst.add("dog");
		bst.add("turtle");
		bst.add("cat");
		bst.add("ferret");
		bst.add("shark");
		bst.add("whale");
		bst.add("porpoise");
		bst.remove("ferret");
		printTree(bst);
		
		BinarySearchTreeArray<Integer> bstInt = new BinarySearchTreeArray<Integer>(10);
		bstInt.add(3);
		bstInt.add(18);
		bstInt.add(4);
		bstInt.add(99);
		bstInt.add(50);
		bstInt.add(23);
		bstInt.add(5);
		bstInt.add(101);
		bstInt.add(77);
		bstInt.add(87);
		bstInt.remove(50);
		printTree(bstInt);
	}
	
	private static <E> void printTree(BinarySearchTreeArray<E> bst) {
		BinarySearchTreeArray<E>.Entry entry;
		System.out.println("Elements sorted in the order in which they were entered");
		System.out.println("Index\tElement\t\tparent\tleft\tright");
		for(E s : bst) {
			entry = bst.getEntry(s);
			if(s.equals("porpoise"))
				System.out.println(entry.position + "\t" + s + "\t" + entry.parent + "\t" + entry.left + "\t" + entry.right);
			else
				System.out.println(entry.position + "\t" + s + "\t\t" + entry.parent + "\t" + entry.left + "\t" + entry.right);
		}
		System.out.print("\n\n");
	}
}
