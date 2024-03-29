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


    /**
     * Initializes this BinarySearchTree object to contain a shallow copy of
     * a specified BinarySearchTree object.
     * The worstTime(n) is O(n), where n is the number of elements in the
     * specified BinarySearchTree object.
     *
     * @param otherTree - the specified BinarySearchTree object that this
     *            BinarySearchTree object will be assigned a shallow copy of.
     *
     */
    public BinarySearchTree (BinarySearchTree<? extends E> otherTree)
    {
         root = copy (otherTree.root, null);
         size = otherTree.size;  
    } // copy constructor


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
        
    public boolean equals (Object obj)
    {
        if (!(obj instanceof BinarySearchTree))
            return false;
        return equals (root, ((BinarySearchTree<? extends E>)obj).root);
    } // method 1-parameter equals
    
    public boolean equals (Entry<E> p, Entry<? extends E> q)
    {
       if (p == null || q == null)
           return p == q;      
       if (!p.element.equals (q.element))
           return false;
       if (equals (p.left, q.left) && equals (p.right, q.right) )
           return true;            
       return false;     
    } // method 2-parameter equals
    
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
     *  Determines if there is at least one element in this BinarySearchTree 
     *  object that equals a specified element.
     *  The worstTime(n) is O(n) and averageTime(n) is O(log n).  
     *
     *  @param obj - the element sought in this BinarySearchTree object.
     *
     *  @return true - if there is an element in this BinarySearchTree object
     *                the equals obj; otherwise, return false.
     *
     *  @throws ClassCastException - if obj cannot be compared to the 
     *           elements in this BinarySearchTree object. 
     *  @throws NullPointerException - if obj is null.
     *  
     */ 
    public boolean contains (Object obj) 
    {
        return getEntry (obj) != null;
    } // method contains

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
     *  Ensures that this BinarySearchTree object does not contain a specified 
     *  element.
     *  The worstTime(n) is O(n) and averageTime(n) is O(log n).
     *
     *  @param obj - the object whose absence is ensured in this 
     *                 BinarySearchTree object.
     *
     *  @return true - if this BinarySearchTree object changed as a result of
     *                this method call (that is, if obj was actually removed); 
     *                otherwise, return false.
     *
     *  @throws ClassCastException - if obj cannot be compared to the 
     *                  elements already in this BinarySearchTree object. 
     *  @throws NullPointerException - if obj is null.
     *
     */
    public boolean remove (Object obj)
    {
        Entry<E> e = getEntry (obj);
        if (e == null)
            return false;
        deleteEntry (e);       
        return true;
    } // method remove


    /**
     *  Finds the Entry object that houses a specified element, if there is 
     *  such an Entry.
     *  The worstTime(n) is O(n), and averageTime(n) is O(log n).
     *
     *  @param obj - the element whose Entry is sought.
     *
     *  @return the Entry object that houses obj - if there is such an Entry;
     *                otherwise, return null.  
     *
     *  @throws ClassCastException - if obj is not comparable to the elements
     *                  already in this BinarySearchTree object.
     *  @throws NullPointerException - if obj is null.
     *
     */
    public Entry<E> getEntry (Object obj) 
    {
        int comp;

        if (obj == null)
           throw new NullPointerException();
        Entry<E> e = root;
        while (e != null) 
        {
            comp = ((Comparable)obj).compareTo (e.element);
            if (comp == 0)
                return e;
            else if (comp < 0)
                e = e.left;
            else
                e = e.right;
        } // while
        return null;
    } // method getEntry
    
  

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
     * Performs a left rotation in this BinarySearchTree object around a specified
     * Entry object.
     * 
     * @param p - the Entry object around which the left rotation is performed
     * 
     * @throws NullPointerException - if p is null or p.right is null
     */
    protected void rotateLeft(Entry<E> p)
    {
    	Entry<E> r = p.right;
    	p.right = r.left;
    	if(r.left != null)
    		r.left.parent = p; // make left child of the right child of element of rotation a child of element of rotation
    	r.parent = p.parent; // make right child take place of element of rotation
    	if(p.parent == null)
    		root = r;
    	else if(p.parent.left == p) // p is left child
    		p.parent.left = r; // make right child take place of element of rotation visible to parent
    	else // p is right child
    		p.parent.right = r; // make right child take place of element of rotation visible to parent
    	r.left = p; // make element of rotation the left child of its former right child, visible to r
    	p.parent = r; // same as above this makes the change apparent to p
    }
    
    /**
     * Returns a String representation of this BinarySearchTree object.
     * The worstTime(n) is linear in n.
     * 
     * @return a String representation - that incorporates the structure - of this
     * 			BinarySearchTree object.
     */
    public String toTreeString() 
    {	
    	final int tabSpaces = 8;
    	double level = 0;
    	StringBuilder sb = new StringBuilder();
    	double height = new Double(height());
    	int maxLeaves = new Double(Math.pow(2.0, height)).intValue();
    	int tabs = maxLeaves/2 - 1;
    	Queue<Entry<E>> queue = new LinkedList<Entry<E>>();
    	for(int i = 0; i < tabs; i++)
    		sb.append("\t");
    	queue.add(root);
    	int i = 0;
    	Entry<E> entry;
    	double result = 0;
    	int entryInLevel = 0;
    	while(!queue.isEmpty()) {
    		result = Math.pow(2.0, level);
    		if(i!= 2 && i >= result) {
    			level++;
    			sb.append("\n");
    			int intLevel = new Double(level).intValue();
    			tabs = maxLeaves/2 - 1 - intLevel;
    			for(int k = 0; k < tabs; k++)
    	    		sb.append("\t");
    			entryInLevel = 0;
    		}
    		entry = queue.remove();
    		if(entry.parent != null) 
	    		if(entry.parent.left != null && entry.parent.left.element.equals(entry.element)) {
	    			if(entryInLevel == 0)
	    				sb.append("\t" + entry.element);
	    			else {
	    				for(int j = 0; j < (tabSpaces - level); j++)
	    					sb.append(" ");
	    				sb.append(entry.element);
	    			}
	    		}
	    		else if(level == 1)
	    			sb.append("\t\t" + entry.element);
	    		else {
	    			if(entryInLevel == 0)
	    				sb.append("\t");
	    			for(int j = 0; j < (tabSpaces - 1 - level); j++)
    					sb.append(" ");
	    			sb.append(entry.element);
	    		}
    		else // root element
    			sb.append("\t" + entry.element);
    		entryInLevel++;
    		if(entry.left != null)
    			queue.add(entry.left);
    		if(entry.right != null)
    			queue.add(entry.right);
    		i++;
    	}
    	return sb.toString();
    }
    
    public void inOrderIter() {
    	Stack<Entry<E>> stack = new Stack<Entry<E>>();
    	Entry<E> current = root;
    	boolean done = false;
    	
    	while(!done) {
    		if(current!=null) {
    			while(current != null) {
    				stack.push(current);
    				current = current.left;
    			}
    		}
    		else {
    			if(stack.isEmpty()) 
    				done = true;
    			else {
    				current = stack.pop();
    				System.out.println(current.element);
    				current = current.right;
    			}
    		}
    	}
    }
    
    /* -------------------------------------------------------------------
     * Recursively does an inorder traversal of the tree
     */
    public void inorder() { inorder_p(root); }

    /*
     * Complete the following method to do an inorder traversal. */
    private void inorder_p(Entry<E> t)  {
       /*
        * Note: t.get_left() gives you the entire left subtree and
        *       t.get_right() gives you the right tree.
        */
 	   if(t != null) {
 	    	inorder_p(t.left);
 	    	System.out.println(t.element);
 	    	inorder_p(t.right);
 	      }
       return;
    }
    
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
