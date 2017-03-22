import java.util.NoSuchElementException;

public class FairPQ<E> {
	
	private transient Object[] queue;
	private int size = 0;
	private transient int modCount = 0;
	private final int DEFAULT_INITIAL_CAPACITY = 11;
	
	public FairPQ() {
		queue = new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public boolean add(E e) {
		if(e == null)
			throw new NullPointerException();
		modCount++;
		int i = size;
		size = i+1;
		if(i == 0)
			queue[0] = e;
		else
			siftUp(i, e);
		return true;
	}
	
	public E remove() {
		if(size == 0)
			throw new NoSuchElementException();
		int s = --size;
		modCount++;
		E result = (E)queue[0];
		E x = (E) queue[s]; // get rightmost leaf at bottom of tree
		queue[s] = null;
		if(s != 0)
			siftDown(0, x);
		return result;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void heapSort(Object[] a) {
		queue = a;
		int length = queue.length;
		size = length;
		
		for(int i = (size >> 1) - 1; i >= 0; i--)
			siftDownUnFair(i, (E)queue[i]);
		
		E x;
		for(int i = 0; i < length; i++) {
			x = (E)queue[--size];
			queue[size] = queue[0];
			siftDownUnFair(0, x);
		}
		
		for(int i = 0; i < length / 2; i++) {
			x = (E)queue[i];
			queue[i] = queue[length - i - 1];
			queue[length - i - 1] = x;
		}
	}
	
	private void siftUp(int k, E x) {
        Comparable key = (Comparable) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
	
	private void siftDown(int k, E x) {
        Comparable key = (Comparable)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) { // at most half of the elements can be leaves
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child]; // c and child represent the node and index of least child
            int right = child + 1;
            if (right < size &&
                ((Comparable) c).compareTo((E)queue[right]) > 0)
                c = queue[child = right]; // if right child is less than left child
            if (key.compareTo((E) c) < 0) // if element is less than child, child has already been copied to parent on previous iteration 
                break;
            else if(key.compareTo((E) c) == 0) { 
            	queue[child] = key;
            	key = (Comparable)c;
            	break;
            }
            queue[k] = c; // make parent equal to least child
            k = child; // repeat process down one level
        }
        queue[k] = key; // 
    }
	
	private void siftDownUnFair(int k, E x) {
		Comparable key = (Comparable)x;
		int half = size >>> 1;
        while(k < half) {
        	int child = (k << 1) + 1;
        	Object c = queue[child];
        	int right = child + 1;
        	if(right < size && ((Comparable)c).compareTo((E)queue[right]) > 0)
        		c = queue[child = right];
        	if(key.compareTo((E)c) <= 0)
        		break;
        	queue[k] = c;
        	k = child;
        }
        queue[k] = key;
	}
}
