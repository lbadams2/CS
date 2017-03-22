import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SortTiming {
	
	private static Student[] students;
	final static double NANO_FACTOR = 1000000000.0;
	final static int INSERTIONSORT_THRESHOLD = 7;
	
	public static void main(String[] args) {
		
		fillArray();
		Student aux[] = students.clone();
		long begin = System.nanoTime();
		mergeSort(aux, 0, students.length);
		long end = System.nanoTime();
		long duration = end - begin;
		System.out.println("Merge sort of data file takes " + duration/NANO_FACTOR + " seconds");
		
		fillArray();
		begin = System.nanoTime();
		quickSort(0, students.length);
		end = System.nanoTime();
		duration = end - begin;
		System.out.println("Quick sort of data file takes " + duration/NANO_FACTOR + " seconds");
		
		FairPQ<Student> pq = new FairPQ<Student>();
		fillArray();
		begin = System.nanoTime();
		pq.heapSort(students);
		end = System.nanoTime();
		duration = end - begin;
		System.out.println("Heap sort of data file takes " + duration/NANO_FACTOR + " seconds");
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
	
	public static void quickSort(int off, int len) {
		int m = off + (len >> 1),
				l = off,
				n = off + len - 1;
		
		m = med3(l, m, n);
		Student v = students[m];
		
		int b = off,
				c = off + len - 1;
		while(true) {
			while(b <= c && (students[b].compareTo(v) < 0))
				b++;
			while(c >= b && (students[c].compareTo(v) > 0)) 
				c--;
			if(b > c)
				break;
			swap(b++, c--);
		}
		
		if(c + 1 - off > 1)
			quickSort(off, c + 1 - off);
		if(off + len - b > 1)
			quickSort(b, off + len - b);
	}
	
	public static void mergeSort(Student[] src, int low, int high) {
		int length = high - low;
		
		if(length < INSERTIONSORT_THRESHOLD) {
			for(int i = low; i < high; i++)
				for(int j = i; j > low && ((Comparable)students[j-1]).compareTo(students[j]) > 0; j--)
					swap(students, j, j-1);
			return;
		}
		
		int mid = (low + high) >> 1;
		mergeSort(students, low, mid);
		mergeSort(students, mid, high);
		
		if(((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
			System.arraycopy(src, low, students, low, length);
			return;
		}
		
		for(int i = low, p = low, q = mid; i < high; i++)
			if(q>=high || (p<mid && ((Comparable)src[p]).compareTo(src[q])<=0))
				students[i] = src[p++];
			else
				students[i] = src[q++];
	}
	
	public static void swap(int x, int y) {
		Student tmp = students[y];
		students[y] = students[x];
		students[x] = tmp;
	}
	
	public static void swap(Student[] s, int x, int y) {
		Student tmp = s[y];
		s[y] = s[x];
		s[x] = tmp;
	}
	
	private static int med3(int a, int b, int c) {
		return(students[a].compareTo(students[b]) < 0 ?
				(students[b].compareTo(students[c]) < 0 ? b : students[a].compareTo(students[c]) < 0 ? c : a) :
					(students[b].compareTo(students[c]) > 0 ? b : students[a].compareTo(students[c]) > 0 ? c : a));
	}
}