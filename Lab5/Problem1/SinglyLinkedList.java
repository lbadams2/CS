import java.util.*;

public class SinglyLinkedList<E> extends AbstractCollection<E> 
  implements List<E>
{
  protected Entry<E> head;
  
  
  /**
   * Initializes this SinglyLinkedList object to be empty, with elements to be of 
   *  type E.
   */
  public SinglyLinkedList()
  {
    head = null;
  } // constructor
  
  
  /**
   *  Determines if this SinglyLinkedList object has no elements.
   *
   *  @return true - if this SinglyLinkedList object has no elements; otherwise,
   *                          false.  
   */
  public boolean isEmpty ()  
  {
    return head == null;
  } // method isEmpty
  
  
  /**
   *  Adds a specified element to the front of this SinglyLinkedList object.
   *
   *  @param e - the element to be prepended.
   *
   */
  public void addToFront (E element) 
  {
    Entry<E> temp = new Entry<E>();
    temp.element = element;
    temp.next = head;
    head = temp;
  } // method addToFront
  
  
  /**
   *  Returns a SinglyLinkedListIterator object to iterate over this
   *  SinglyLinkedList object.
   *
   */  
  public Iterator<E> iterator()
  {
    return new SinglyLinkedListIterator();
  } // method iterator
  
  
  /**  
   *  Determines the number of elements in this SinglyLinkedList object.
   *  The worstTime(n) is O(n).
   *
   *  @return the number of elements.
   */
  public int size() 
  {
    int count = 0;
    
    for (Entry<E> current = head; current != null; current = current.next)
      count++;
    return count;
  } // method size
  
  
  /** 
   *  Determines if this SinglyLinkedList object contains a specified element.
   *  The worstTime(n) is O(n).
   *
   *  @param obj - the specified element being sought.
   *
   *  @return true - if this SinglyLinkedList object contains element; otherwise,
   *                false. 
   *
   */
  public boolean contains (Object obj) 
  {
    if (obj == null)
    {
      for (Entry<E> current = head; current != null; current = current.next)
        if (current.element == null)
          return true;
    } // if obj == null
    else   
      for (Entry<E> current = head; current != null; current = current.next)
        if (obj.equals (current.element))
          return true;
    return false;
  } // method contains
  

  public ListIterator<E> listIterator(int index)
  {
    throw new UnsupportedOperationException( );
  }  
 
  public ListIterator<E> listIterator()
  {
    throw new UnsupportedOperationException( );
  }

  public int lastIndexOf(Object obj)
  {
	  int currentIndex = 0, lastIndex = -1;
	  for (Entry<E> current = head; current != null; current = current.next) {
	        if (obj.equals (current.element))
	          lastIndex = currentIndex;
	        currentIndex++;
	  }
	  return lastIndex;
  }

  public int indexOf(Object obj)
  {
	  int currentIndex = 0;
	  for (Entry<E> current = head; current != null; current = current.next) {
	        if (obj.equals (current.element))
	          return currentIndex;
	        currentIndex++;
	  }
	  return -1;
  }

  public E remove(int index)
  {
	if(index > size() - 1)
		throw new IndexOutOfBoundsException();
	
	int currentIndex = 0;
	Entry<E> prev = head;
	Entry<E> temp = null;
	if(index == 0) {
		temp = head;
		head = head.next;
		return temp.element;
	}
	for (Entry<E> current = head; current != null; current = current.next) {
	     if (index == currentIndex) {
	         prev.next = current.next;
	         return current.element;
	     }
	     currentIndex++;
	     prev = current;
	}
	return null;
  }

  
  public boolean add(E element)
  {
	Entry<E> newEntry = new Entry<E>();
	newEntry.element = element;
	
	if(head == null) {
		head = newEntry;
		return true;
	}
	
	Entry<E> tail = head;
	for (Entry<E> current = head; current != null; current = current.next)
		tail = current;
	
	tail.next = newEntry;
    return true;
  } 

  public void add(int index, E element)
  {
	Entry<E> prev = head;
	Entry<E> newEntry = new Entry<E>();
	newEntry.element = element;
	int currentIndex = 0;
	if(index == 0)
		addToFront(element);
	for (Entry<E> current = head; current != null; current = current.next){
		if(index == currentIndex){
			newEntry.next = prev.next; // prev.next is current
			prev.next = newEntry;
		}
		currentIndex++;
		prev = current;
	}
  } 

  public E set(int index, E element)
  {
    throw new UnsupportedOperationException( );
  }

  public E get(int index)
  {
	if(index > size() - 1)
		throw new IndexOutOfBoundsException();
	
	int currentIndex = 0;
	for(E current : this) {
		if(index == currentIndex)
			return current;
		currentIndex++;
	}
    return null;
  }

  public boolean addAll(Collection<? extends E> c)
  {
    throw new UnsupportedOperationException( );
  }

  public boolean addAll(int index, Collection<? extends E> c)
  {
	for(E e: c)
		add(index++, e);
    return true;
  }

  public Object[] toArray() 
  { 
	Object[] array = new Object[size()];
	int i = 0;
	for(E e: this) {
		array[i] = e;
		i++;
	}
    return array; 
  }
  
  public <E>E[] toArray(E[] a)
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public boolean remove(Object obj) 
  { 
	E objToRemove = (E)obj;
	Entry<E> prev = head;
	if(objToRemove == head.element) {
		head = head.next;
		return true;
	}
	for (Entry<E> current = head; current != null; current = current.next) {
		if(objToRemove.equals(current.element)) {
			prev.next = current.next;
			return true;
		}
		prev = current;
	} 
	return false;
  }
  
  public boolean containsAll(Collection<?> c) 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  
  public boolean removeAll(Collection<?> c) 
  { 
	SinglyLinkedList<E> list = (SinglyLinkedList<E>)c;
	boolean status = true;
	for(E e: list)
		if(!remove(e))
			status = false;
	return status;
  }
  
  public boolean retainAll(Collection<?> c) 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  public List<E> subList(int fromIndex, int toIndex)
  { 
    throw new UnsupportedOperationException( ); 
  }
  public void clear() 
  {
    head = null; 
  }
  
  public boolean equals(Object o) 
  {   
	  if(!(o instanceof SinglyLinkedList<?>))
		  return false;
	  
	  SinglyLinkedList<E> linkedList = (SinglyLinkedList<E>)o;
	  
	  if(linkedList.size() != size())
		  return false;
	  
	  Entry<E> current;
	  Entry<E> current2;
	  for (current = head, current2 = linkedList.head; current != null; current = current.next, current2 = current2.next)
	        if (!(current.element.equals(current2.element)))
	          return false;
	  
	  return true; 
  }
  
  public int hashCode() 
  { 
    throw new UnsupportedOperationException( ); 
  }
  
  
  protected class SinglyLinkedListIterator implements Iterator<E> 
  {
    protected Entry<E> next;
    
    /**
     *  Initializes this SinglyLinkedListIterator object
     */
    SinglyLinkedListIterator() 
    {
      next = head;
    } // constructor
    
    
    /** 
     *  Returns the element this SinglyLinkedListIterator object was 
     *  (before this call) positioned at, and advances this 
     *  SinglyLinkedListIterator object.
     *                    
     *  @return - the element this SinglyLinkedListIterator object was 
     *            positioned at.
     *
     *  @throws NullPointerException – if this Iterator object was
     *                 not postioned at an element before this call.
     */                            
    public E next() 
    {
      E theElement = next.element;
      next = next.next;
      return theElement;
    } // method next
    
    
    /**
     *  Determines if this SinglyLinkedListIterator object is positioned 
     * at an element in this SinglyLinkedList object.
     *
     *  @return true - if this SinglyLinkedListIterator object is 
     *                 positioned at an element; otherwise, false.
     */                   
    public boolean hasNext() 
    {       
      return next != null;
    } // method hasNext
    
    /**
     *  Removes the element returned by the most recent call to next().
     *  The behavior of this Iterator object is unspecified if the underlying 
     *  collection is modified (while this iteration is in progress) other than 
     *   by calling this remove() method.
     *
     *  @throws IllegalStateException - if next() had not been called before
     *                 this call to remove(), or if there had been an intervening call 
     *                 to remove() between the most recent call to next() and this 
     *                 call.
     */
    public void remove() 
    { 
      throw new UnsupportedOperationException( ); 
    }
    
  } // class SinglyLinkedListIterator
  
  
  protected static class Entry<E> 
  {
    E element;
    Entry<E> next;
    
  } // class Entry
  
} // class SinglyLinkedList