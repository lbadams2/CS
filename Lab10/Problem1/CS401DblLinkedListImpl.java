import java.util.AbstractCollection;
import java.util.Iterator;

public class CS401DblLinkedListImpl<E extends Comparable> extends AbstractCollection<E> 
implements CS401CollectionInterface<E> 
{
   private LinkEntry<E> head;
   private LinkEntry<E> tail;
   private int s;

   public CS401DblLinkedListImpl()
   {
      head = tail = null;
   }

   public boolean is_empty()
   {
      if (head == null) 
          return true;

      return false;
   }

   public boolean is_full() { return false; }

   public int size()
   {
	  s = 1;
      return size_r(head) - 1;
      /*
       * Note that an iterative solution that traverses the list from
       * the front would be as follows:
       * 
       * LinkEntry<E> temp;
       * int i = 0;
       *
       * for (temp = head; temp != null; temp = temp.next)
       *      i++;
       * return i;
       */
   }

   public boolean add(int index, E e)
   {
      throw new UnsupportedOperationException();
   }

   /*
    * Add e to the end of the doubly linked list.
    * Returns true - if e was successfully added, false otherwise.
    */
   public boolean add(E e)
   {
	  try {
		  LinkEntry<E> ne = new LinkEntry<E>();
		  ne.element = e;
		  if(head == null) {
			  head = ne;
			  tail = ne;
			  return true;
		  }
		  ne.previous = tail;
		  ne.next = null;
		  tail.next = ne;
		  tail = ne;
	  } catch(Exception ex) {
		  ex.printStackTrace();
		  return false;
	  }
      return true;
   }
   
   public boolean addSort(E e) 
   {
	   LinkEntry<E> ne = new LinkEntry<E>();
	   ne.element = e;
	   if(head == null) {
			  head = ne;
			  tail = ne;
			  return true;
	   }
	   LinkEntry<E> current = head;
	   for (; current != null; current = current.next) {
		   if(e.compareTo(current.element) <= 0) {
			   if(current == head) {
				   head = ne;
				   ne.next = current;
				   current.previous = ne;
				   return true;
			   }
			   if(e.compareTo(current.previous.element) >= 0) {
				   current.previous.next = ne;
				   ne.previous = current.previous;
				   ne.next = current;
				   current.previous = ne;
				   return true;
			   }
		   }			   
	   }
	   tail.next = ne;
	   ne.previous = tail;
	   tail = ne;
	   return true;
   }

   /*
    * Remove the nth element in the list.  The first element is element 1.
    * Return the removed element to the caller.
    */
   public E remove(int n)
   {
	  LinkEntry<E> temp;
      if(n > size() || n < 1)
    	  throw new IndexOutOfBoundsException();
      else if(n == 1) {
    	  temp = head;
    	  head = head.next;
    	  head.previous = null;
      }		
      else if(n == size()){
    	  temp = tail;
    	  tail = tail.previous;
    	  tail.next = null;
      }
      else {
    	  int i = 1;
    	  for(temp = head; temp != null; temp = temp.next, i++) 
    		  if(i == n) {
    			  temp.previous.next = temp.next;
    			  temp.next.previous = temp.previous;
    			  break;
    		  }
      }
      return temp.element;
   }

   /*
    * Print the doubly linked list starting at the beginning.
    */
   public void print_from_beginning()
   {
      LinkEntry<E> temp;
      for(temp = head; temp != null; temp = temp.next)
    	  System.out.println(temp.element);
   }

   /*
    * Print the doubly linked list starting the end.
    */
   public void print_from_end()
   {
	   LinkEntry<E> temp;
	   for(temp = tail; temp != null; temp = temp.previous)
	      System.out.println(temp.element);
   }

   public E remove()
   {
      throw new UnsupportedOperationException();
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

   public boolean contains(E e)
   {
      for(E current : this) 
    	  if(current.compareTo(e) == 0)
    		  return true;
      return false;
   }
   
   public E get(E e)
   {
      for(E current : this) 
    	  if(current.compareTo(e) == 0)
    		  return current;
      return null;
   }

   private int size_r(LinkEntry<E> head)  /* Think about this recursive call! */
   {
      if (head != null)
         s = s + size_r(head.next);
      return s;
   }
   

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}
	
   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<E>
   {
      protected E element;
      protected LinkEntry<E> next;
      protected LinkEntry<E> previous;

      protected LinkEntry() { element = null; next = previous = null; }
   }
   
   protected class DoublyLinkedListIterator implements Iterator<E> 
   {
     protected LinkEntry<E> next;
     
     /**
      *  Initializes this SinglyLinkedListIterator object
      */
     DoublyLinkedListIterator() 
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

} /* CS401LinkedListImpl<E> */