import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTreeArray<E> implements Iterable<E>{

	Entry[] tree;
	
	int root,
		size;
	
	@SuppressWarnings("unchecked")
	public BinarySearchTreeArray(int capacity) 
    {
		tree = (Entry[]) Array.newInstance(Entry.class, capacity);
//		Object[] o = new Object[capacity];
//		tree = (Entry[])o;
        root = 0;
        size = 0;  
    }
	
    public boolean add(E element) {
    	return addRec(tree[root], element);
    }
    
    public boolean addRec(Entry p, E element) {
    	if(p == null) {
    		if(element == null)
    			throw new NullPointerException();
    		// p is copy of reference to root, p and root are each null on first call to add, assigning new entry to p doesn't affect root
    		tree[root] = new Entry (element, -1);
    		size++;
    		return true;
    	}
    	int comp = ((Comparable)element).compareTo(p.element);
    	Entry ne;
    	if(comp == 0)
    		return false;
    	else if(comp < 0)
    		if(p.left == -1) {
    			if(p.parent == -1) 
        			ne = new Entry (element, root);
    			else {
	    			if(tree[p.parent].left != -1 && tree[tree[p.parent].left].element == p.element)  // get index of p from p's parent
	    				ne = new Entry (element, tree[p.parent].left);
	    			else 
	    				ne = new Entry (element, tree[p.parent].right);
    			}
    			p.left = size;
    			tree[size] = ne;
    			size++;
    			return true;
    		}
    		else
    			return addRec(tree[p.left], element);
    	else
    		if(p.right == -1) { 
    			if(p.parent == -1) 
        			ne = new Entry (element, root);
    			else {
	    			if(tree[p.parent].left != -1 && tree[tree[p.parent].left].element == p.element)  // get index of p from p's parent
	    				ne = new Entry (element, tree[p.parent].left);
	    			else 
	    				ne = new Entry (element, tree[p.parent].right);
    			}
    			p.right = size;
    			tree[size] = ne;
    			size++;
    			return true;
    		}
    		else
    			return addRec(tree[p.right], element);
    }
    
    public boolean remove (Object obj)
    {
        Entry e = getEntry (obj);
        if (e == null)
            return false;
        deleteEntry (e);       
        return true;
    } // method remove
    
    public Iterator<E> iterator()
    {
         return new TreeIterator();
    } // method iterator
    
    protected Entry getEntry (Object o) 
    {
    	int temp = root,
    		comp;
    	
    	while(temp != -1) {
    		comp = ((Comparable)o).compareTo(tree[temp].element);
    		if(comp == 0)
    			return tree[temp];
    		else if(comp < 0)
    			temp = tree[temp].left;
    		else
    			temp = tree[temp].right;
    	}
    	return null;
    }
    
    protected Entry deleteEntry (Entry p) 
    {
        size--;
        int deletedOrder = p.order;
        // If p has two children, replace p's element with p's successor's
        // element, then make p reference that successor.
        if (p.left != -1 && p.right != -1) 
        {
            Entry s = successor (p);
            p.element = s.element;
            p.order = s.order;
            p = s; // left child of successor s must be null by method of finding successor
        } // p had two children


        // At this point, p has either no children or one child.

        Entry replacement; // replacement will be child of element to be deleted
        int replacementIndex = 0;
         
        if (p.left != -1) {
            replacement = tree[p.left];
            replacementIndex = p.left;
        }
        else { // always comes here if p had two children
            replacement = tree[p.right];
            replacementIndex = p.right;
        }

        // If p has at least one child, link replacement to p.parent.
        if (replacement != null) 
        {
            replacement.parent = p.parent;
            if (p.parent == -1)
                tree[root] = replacement;
            else if (p == tree[tree[p.parent].left]) { //left child
                tree[tree[p.parent].left]  = replacement;
                if(tree[replacementIndex].left != -1) 
                	tree[tree[replacementIndex].left].parent = tree[p.parent].left; // makes child's parent equal to index of deleted element
                if(tree[replacementIndex].right != -1)
                	tree[tree[replacementIndex].right].parent = tree[p.parent].left;
                adjustArray(replacementIndex, deletedOrder);
            }
            else {
                tree[tree[p.parent].right] = replacement;
                if(tree[replacementIndex].left != -1) 
                	tree[tree[replacementIndex].left].parent = tree[p.parent].right; // makes child's parent equal to index of deleted element
                if(tree[replacementIndex].right != -1)
                	tree[tree[replacementIndex].right].parent = tree[p.parent].right;
                adjustArray(replacementIndex, deletedOrder);
            }
        } // p has at least one child  
        else if (p.parent == -1) // deleted only element of tree
            root = -1;
        else // deleting leaf
        {
            if (p == tree[tree[p.parent].left])
                tree[p.parent].left = -1;
            else
                tree[p.parent].right = -1;        
        } // p has a parent but no children
        return p;
    } // method deleteEntry
    
    protected Entry successor (Entry e) 
    {
        if (e == null)
            return null;
        else if (e.right != -1) 
        {
            // successor is leftmost Entry in right subtree of e
            Entry p = tree[e.right];
            while (p.left != -1)
                p = tree[p.left];
            return p;

        } // e has a right child
        else 
        {

            // go up the tree to the left as far as possible, then go up
            // to the right.
            Entry p = tree[e.parent];
            Entry ch = e;
            while (p != null && p.right!= -1 && ch == tree[p.right]) 
            {
                ch = p;
                if(p.parent == -1)
                	return null;
                p = tree[p.parent];
            } // while
            return p;
        } // e has no right child
    } // method successor
    
    private void adjustArray(int index, int deletedOrder) 
    {
    	// size has already been decremented
    	// the element at index has been moved to another index
    	for(int i = index+1; i < size+1; i++ )
    		tree[i-1] = tree[i];
    	tree[size] = null;
    	Entry e;
    	for(int i = 0; i < size; i++) {
    		e = tree[i];
    		if(e.left > index)
    			e.left-=1;
    		if(e.right > index)
    			e.right-=1;
    		if(e.parent > index)
    			e.parent-=1;
    		if(e.order > deletedOrder)
    			e.order -= 1;
    	}
    }
    
    protected class TreeIterator implements Iterator<E>
    {
        protected int next;

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
            return next < size;
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
        	for(int i = 0; i < size; i++)
        		if(tree[i].order == next) {
        			next++;
        			tree[i].position = i;
        			return tree[i].element;
        		}
        	return null;
        } // method next    

    } // class TreeIterator


	
	
    protected class Entry 
    {
        protected E element;

        protected int left,
                      right,
                      parent,
                      order,
                      position;
 
        /**
         *  Initializes this Entry object.
         *
         *  This default constructor is defined for the sake of subclasses of
         *  the BinarySearchTree class. 
         */
        public Entry() 
        { 
        	left = right = parent = -1;
        }


        /**
         *  Initializes this Entry object from element and parent.
         *
         */ 
         public Entry (E element, int parent) 
         {
             this.element = element;
             this.parent = parent;
             left = -1;
             right = -1;
             order = size;
         } // constructor

    } // class Entry
}
