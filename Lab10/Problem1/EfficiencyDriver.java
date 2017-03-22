import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class EfficiencyDriver {
	
	static CS401DblLinkedListImpl<Student> sortedList = new CS401DblLinkedListImpl<Student>();
	static CS401DblLinkedListImpl<Student> unsortedList = new CS401DblLinkedListImpl<Student>();
	static BinarySearchTree<Student> tree = new BinarySearchTree<Student>();
	final static double NANO_FACTOR = 1000000000.0;
	
	public static void main(String[] args) {
		Scanner sc = null;
		long startTime, endTime, duration;
		
		// Add to unsorted list
		try {
			sc = new Scanner(new File("students.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		startTime = System.nanoTime();
		while(sc.hasNext())
			addToUnsortedList(sc.nextLine());
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("Time taken to load the records in a random linked list: " + duration/NANO_FACTOR + " seconds");
		
		// Add to sorted list
		try {
			sc = new Scanner(new File("students.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		startTime = System.nanoTime();
		while(sc.hasNext())
			addToSortedList(sc.nextLine());
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("Time taken to load the records in a sorted linked list: " + duration/NANO_FACTOR + " seconds");
		
		// Add to tree
		try {
			sc = new Scanner(new File("students.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		startTime = System.nanoTime();
		while(sc.hasNext())
			addToTree(sc.nextLine());
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("Time taken to load the records in a binary search tree: " + duration/NANO_FACTOR + " seconds");
		
		searchStructuresForStudent(new Student(null, null, null, 483293267L));
		searchStructuresForStudent(new Student(null, null, null, 1902997270L));
		searchStructuresForStudent(new Student(null, null, null, 8564086840L));
		searchStructuresForStudent(new Student(null, null, null, 143507366L));
		searchStructuresForStudent(new Student(null, null, null, 307954472L));
		searchStructuresForStudent(new Student(null, null, null, 876561221L));
	}
	
	private static void addToSortedList(String line) {
		String[] tokens = line.split("[,\\s]+");
		Student s = new Student(tokens[0], tokens[1], tokens[2], Long.parseLong(tokens[3]));
		sortedList.addSort(s);
	}
	
	private static void addToUnsortedList(String line) {
		String[] tokens = line.split("[,\\s]+");
		Student s = new Student(tokens[0], tokens[1], tokens[2], Long.parseLong(tokens[3]));
		unsortedList.add(s);	
	}
	
	private static void addToTree(String line) {
		String[] tokens = line.split("[,\\s]+");
		Student s = new Student(tokens[0], tokens[1], tokens[2], Long.parseLong(tokens[3]));
		tree.add(s);
	}
	
	private static void searchStructuresForStudent(Student s) {
		long startTime, endTime; 
		double durationUnsorted, durationSorted, durationTree;
		Student unSorted, sorted, treeGet;
		
		// search unsorted list
		startTime = System.nanoTime();
		unSorted = unsortedList.get(s);
		endTime = System.nanoTime();
		durationUnsorted = (endTime - startTime)/NANO_FACTOR;
		
		// search sorted list
		startTime = System.nanoTime();
		sorted= sortedList.get(s);
		endTime = System.nanoTime();
		durationSorted = (endTime - startTime)/NANO_FACTOR;
		
		// search tree
		startTime = System.nanoTime();
		if(tree.getEntry(s) != null)
			treeGet = tree.getEntry(s).element;
		else
			treeGet = null;
		endTime = System.nanoTime();
		durationTree = (endTime - startTime)/NANO_FACTOR;
		
		System.out.println("\nSearching for student ID " + s.id);
		if(unSorted != null && sorted != null && treeGet != null)
			System.out.println("Success (record found): " + unSorted);
		else
			System.out.println("No record with this key found");
		System.out.println("Search time in random linked list: " + durationUnsorted + " seconds");
		System.out.println("Search time in sorted linked list: " + durationSorted + " seconds");
		System.out.println("Search time in binary search tree: " + durationTree + " seconds");
	}
}