import java.util.LinkedList;
import java.util.ListIterator;


public class Answer7_6 {
	
	public static void main(String[] args){
		
		try {
			a();
		}
		catch(Exception e) {
			System.out.println("Exception in a() " + e );
		}
		
		try {
			b();
		}
		catch(Exception e) {
			System.out.println("Exception in b() " + e );
		}
		
		try {
			c();
		}
		catch(Exception e) {
			System.out.println("Exception in c() " + e );
		}
		
		try {
			d();
		}
		catch(Exception e) {
			System.out.println("Exception in d() " + e );
		}
		
		try {
			e();
		}
		catch(Exception e) {
			System.out.println("Exception in e() " + e );
		}
		
		try {
			f();
		}
		catch(Exception e) {
			System.out.println("Exception in f() " + e );
		}
		
	}
	
	/*
	 * LinkedList.add(E element) adds to the back of the list
	 * adds 8.8 as head, increments nextIndex to 1.
	 * call to next() returns 5.3, increments nextIndex to 2
	 * removes element returned by most recent call to next() which is 5.3
	 */
	static void a() {
		LinkedList<Double> weights = new LinkedList<Double>();
		ListIterator<Double> itr;
		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		itr.add(8.8);
		itr.next();
		itr.remove();
	}
	
	/*
	 * An IllegalStateException will be thrown by itr.remove() because next() or previous() has not been called, also add()
	 * cannot immediately precede a call to remove()
	 */
	static void b() {
		LinkedList<Double> weights = new LinkedList<Double>();
		ListIterator<Double> itr;
		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		itr.add(8.8);
		itr.remove();
		itr.next();
	}
	
	/*
	 * next() returns 5.3, increments nextIndex to 1
	 * adds 8.8 before nextIndex, so before 2.8, after 5.3, increments nextIndex to 2
	 * call to remove() throws IllegalStateException, add() cannot be called right before remove()
	 */
	static void c() {
		LinkedList<Double> weights = new LinkedList<Double>();
		ListIterator<Double> itr;
		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		itr.next();
		itr.add(8.8);
		itr.remove();
	}
	
	/*
	 * next() returns 5.3, increments nextIndex to 1
	 * removes 5.3, decrements nextIndex to 0
	 * adds 8.8 before element at 0th index, 8.8 is head, 2.8 is tail
	 */
	static void d() {
		LinkedList<Double> weights = new LinkedList<Double>();
		ListIterator<Double> itr;
		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		itr.next();
		itr.remove();
		itr.add(8.8);
	}
	
	/*
	 * remove() throws IllegalStateException because no call to next() or previous() has been made
	 */
	static void e() {
		LinkedList<Double> weights = new LinkedList<Double>();
		ListIterator<Double> itr;
		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		itr.remove();
		itr.add(8.8);
		itr.next();
	}
	
	/*
	 * remove() throws IllegalStateException because no call to next() or previous() has been made
	 */
	static void f() {
		LinkedList<Double> weights = new LinkedList<Double>();
		ListIterator<Double> itr;
		weights.add(5.3);
		weights.add(2.8);
		itr = weights.listIterator();
		itr.remove();
		itr.next();
		itr.add(8.8);
	}
}
