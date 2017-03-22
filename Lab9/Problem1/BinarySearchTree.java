import java.util.*;

public class BinarySearchTree<E> extends AbstractSet<E> 
{
    protected Entry<E> root;

    protected int size;        
    
    /**
     *  Initializes this BinarySearchTree object to be empty, to contain 
     *  only elements of type E, to be ordered by the Comparable interface, 
     *  and to contain no duplicate elements.
     *
     */ 
    public BinarySearchTree() 
    {
        root = null;
        size = 0;  
    } // default constructor
	
	protected Entry<E> copy (Entry<? extends E> p, Entry<E> parent)
    {
        if (p != null)
        {
            Entry<E> q = new Entry<E> (p.element, parent);
            q.left = copy (p.left, q);
            q.right = copy (p.right, q);
            return q;
        } // if
        return null;
    } // method copy
	
	/**
     *  Returns the size of this BinarySearchTree object.
     *
     * @return the size of this BinarySearchTree object.
     *
     */
    public int size( )
    {
        return size;
    } // method size()
	
	/**
     *  Returns an iterator positioned at the smallest element in this 
     *  BinarySearchTree object.
     *
     *  @return an iterator positioned at the smallest element in this
     *                BinarySearchTree object.
     *
     */
    public Iterator<E> iterator()
    {
         return new TreeIterator();
    } // method iterator

    /**
     *  Ensures that this BinarySearchTree object contains a specified element.
     *  The worstTime(n) is O(n) and averageTime(n) is O(log n).
     *
     *  @param element - the element whose presence is ensured in this 
     *                 BinarySearchTree object.
     *
     *  @return true - if this BinarySearchTree object changed as a result of 
     *                 this method call (that is, if element was actually 
     *                 inserted); otherwise, return false.
     *
     *  @throws ClassCastException - if element cannot be compared to the 
     *                  elements already in this BinarySearchTree object.
     *  @throws NullPointerException - if element is null.
     *
     */    
    public boolean add(E element) {
    	return addRec(root, element);
    }
    
    public boolean addRec(Entry<E> p, E element) {
    	if(p == null) {
    		if(element == null)
    			throw new NullPointerException();
    		// p is copy of reference to root, p and root are each null on first call to add, assigning new entry to p doesn't affect root
    		root = new Entry<E> (element, null);
    		size++;
    		return true;
    	}
    	int comp = ((Comparable)element).compareTo(p.element);
    	
    	if(comp == 0)
    		return false;
    	else if(comp < 0)
    		if(p.left == null) { 
    			p.left = new Entry<E> (element, p);
    			size++;
    			return true;
    		}
    		else
    			return addRec(p.left, element);
    	else
    		if(p.right == null) { 
    			p.right = new Entry<E> (element, p);
    			size++;
    			return true;
    		}
    		else
    			return addRec(p.right, element);
    }
    
    /**
     * Calculates the height of this BinarySearchTree object.
     *
     * @return an int containing the height of this BinarySearchTree object.
     **/
    protected int height() {
    	if(root == null)
    		return -1;
    	BinarySearchTree<E> leftTree = new BinarySearchTree<E>();
    	leftTree.root = copy(root.left, null);
    	BinarySearchTree<E> rightTree = new BinarySearchTree<E>();
    	rightTree.root = copy(root.right, null);
    	return 1 + (leftTree.height() > rightTree.height() ? leftTree.height():rightTree.height());
    }
	
	/**
      *  Deletes the element in a specified Entry object from this 
      *  BinarySearchTree.
      *  
      *  @param p - the Entry object whose element is to be deleted from this
      *                 BinarySearchTree object.
      *
      *  @return the Entry object that was actually deleted from this
      *                BinarySearchTree object. 
      *
      */
    protected Entry<E> deleteEntry (Entry<E> p) 
    {
        size--;

        // If p has two children, replace p's element with p's successor's
        // element, then make p reference that successor.
        if (p.left != null && p.right != null) 
        {
            Entry<E> s = successor (p);
            p.element = s.element;
            p = s;
        } // p had two children


        // At this point, p has either no children or one child.

        Entry<E> replacement;
         
        if (p.left != null)
            replacement = p.left;
        else
            replacement = p.right;

        // If p has at least one child, link replacement to p.parent.
        if (replacement != null) 
        {
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)
                p.parent.left  = replacement;
            else
                p.parent.right = replacement;
        } // p has at least one child  
        else if (p.parent == null)
            root = null;
        else 
        {
            if (p == p.parent.left)
                p.parent.left = null;
            else
                p.parent.right = null;        
        } // p has a parent but no children
        return p;
    } // method deleteEntry

	
	/**
     *  Finds the successor of a specified Entry object in this 
     *  BinarySearchTree. The worstTime(n) is O(n) and averageTime(n) is 
     *  constant.
     *
     *  @param e - the Entry object whose successor is to be found.
     *
     *  @return the successor of e, if e has a successor; otherwise, return null
     *
     */
    protected Entry<E> successor (Entry<E> e) 
    {
        if (e == null)
            return null;
        else if (e.right != null) 
        {
            // successor is leftmost Entry in right subtree of e
            Entry<E> p = e.right;
            while (p.left != null)
                p = p.left;
            return p;

        } // e has a right child
        else 
        {

            // go up the tree to the left as far as possible, then go up
            // to the right.
            Entry<E> p = e.parent;
            Entry<E> ch = e;
            while (p != null && ch == p.right) 
            {
                ch = p;
                p = p.parent;
            } // while
            return p;
        } // e has no right child
    } // method successor

	
	protected class TreeIterator implements Iterator<E>
    {

        protected Entry<E> lastReturned = null,
                           next;               

        /**
         *  Positions this TreeIterator to the smallest element, according to 
         *  the Comparable interface, in the BinarySearchTree object.
         *  The worstTime(n) is O(n) and averageTime(n) is O(log n).
         *
         */
        protected TreeIterator() 
        {             
            next = root;
            if (next != null)
                while (next.left != null)
                    next = next.left;
        } // default constructor


        /**
         *  Determines if there are still some elements, in the 
         *  BinarySearchTree object this TreeIterator object is iterating 
         * over, that have not been accessed by this TreeIterator object.
         *
         *  @return true - if there are still some elements that have not 
         *     been accessed by this TreeIterator object; otherwise, return 
         *     false.
         *
         */ 
        public boolean hasNext() 
        {
            return next != null;
        } // method hasNext


        /**
         *  Returns the element in the Entry this TreeIterator object was positioned at 
         *  before this call, and advances this TreeIterator object.
         *  The worstTime(n) is O(n) and averageTime(n) is constant.
         *
         *  @return the element this TreeIterator object was positioned at before this call.
         *
         *  @throws NoSuchElementException - if this TreeIterator object was not 
         *                 positioned at an Entry before this call.
         *
         */
        public E next() 
        {
            if (next == null)
                throw new NoSuchElementException();
            lastReturned = next;
            next = successor (next);             
            return lastReturned.element;
        } // method next

        /**
         *  Removes the element returned by the most recent call to this TreeIterator
         *  object's next() method.
         *  The worstTime(n) is O(n) and averageTime(n) is constant.
         *
         *  @throws IllegalStateException - if this TreeIterator's next() method was not
         *                called before this call, or if this TreeIterator's remove() method was
         *                called between the call to the next() method and this call.
         *
         */ 
        public void remove() 
        {
            if (lastReturned == null)
                throw new IllegalStateException();
 
            if (lastReturned.left != null && lastReturned.right != null)
                next = lastReturned; // the element you really want to delete has its value changed and its successor is 
            // deleted which is where next points, ensure next is pointing to null
            deleteEntry(lastReturned);
            lastReturned = null; // so consecutive calls to remove aren't allowed, a call to next must come between calls to remove
        } // method remove     

    } // class TreeIterator

    protected static class Entry<E> 
    {
        protected E element;

        protected Entry<E> left = null,
                           right = null,
                           parent;
 
        /**
         *  Initializes this Entry object.
         *
         *  This default constructor is defined for the sake of subclasses of
         *  the BinarySearchTree class. 
         */
        public Entry() { }


        /**
         *  Initializes this Entry object from element and parent.
         *
         */ 
         public Entry (E element, Entry<E> parent) 
         {
             this.element = element;
             this.parent = parent;
         } // constructor

    } // class Entry

} // class BinarySearchTree
