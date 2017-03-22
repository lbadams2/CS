import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SortTiming {
	
	private static Student[] students;
	final static double NANO_FACTOR = 1000000000.0;
	
	public static void main(String[] args) {
		fillArray();
		bubbleSort();
		
		fillArray();
		insertionSort();
		
		fillArray();
		selectionSort();
	}
	
	private static void fillArray() {
		Scanner sc = null;
		students = new Student[3380];
		try {
			sc = new Scanner(new File("students.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0;
		String[] tokens;
		while(sc.hasNext()) {
			tokens = sc.nextLine().split("[,\\s]+");
			students[i++] = new Student(tokens[0], tokens[1], tokens[2], Long.parseLong(tokens[3]));
		}
	}
	
	public static void bubbleSort() {
		long begin = System.nanoTime();
		int finalSwapPos = students.length - 1,
				swapPos;
		while(finalSwapPos > 0) {
			swapPos = 0;
			for(int i = 0; i < finalSwapPos; i++) 
				if(students[i].compareTo(students[i+1]) > 0) {
					swap(i, i+1);
					swapPos = i;
				}
			finalSwapPos = swapPos;
		}
		long end = System.nanoTime();
		long duration = end - begin;
		System.out.println("Bubble sort of data file takes " + duration/NANO_FACTOR + " seconds");
	}
	
	public static void insertionSort() {
		long begin = System.nanoTime();
		for(int i = 1; i < students.length; i++)
			for(int k = i; k > 0 && (students[k-1].compareTo(students[k]) > 0); k--)
				swap(k, k - 1);
		long end = System.nanoTime();
		long duration = end - begin;
		System.out.println("Insertion sort of data file takes " + duration/NANO_FACTOR + " seconds");
	}
	
	public static void selectionSort() {
		long begin = System.nanoTime();
		for(int i = 0; i < students.length - 1; i++) {
			int pos = i;
			for(int k = i + 1; k < students.length; k++)
				if(students[k].compareTo(students[pos]) < 0)
					pos = k;
			swap(i, pos);
		}
		long end = System.nanoTime();
		long duration = end - begin;
		System.out.println("Selection sort of data file takes " + duration/NANO_FACTOR + " seconds");
	}
	
	public static void swap(int x, int y) {
		Student tmp = students[y];
		students[y] = students[x];
		students[x] = tmp;
	}
}