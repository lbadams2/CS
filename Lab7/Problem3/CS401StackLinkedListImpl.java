
public class CS401StackLinkedListImpl<E> implements CS401StackInterface<E> 
{
   private LinkEntry<E> head;
   private int num_elements;
   private LinkEntry<E> tail;

   public CS401StackLinkedListImpl()
   {
      head = null;
      num_elements = 0;
   }

   public void push(E e)
   {
	  LinkEntry<E> ne = new LinkEntry<E>();
	  ne.element = e;
      if(num_elements == 0)
    	  head = tail = ne;
      else {
    	  tail.next = ne;
    	  ne.prev = tail;
    	  tail = ne;
      }
      num_elements++;  
   }

   public E pop()
   {
	   if(num_elements <= 0)
		   return null;
	   LinkEntry<E> temp = tail;
	   tail.next = null;
	   tail = tail.prev;
	   num_elements--;
	   return temp.element;
   }
   
   public E top() 
   {
	   if(tail == null)
		   return null;
	   return tail.element;
   }
   
   public int size()
   {
      return num_elements;
   }

   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<T>
   {
      protected T element;
      protected LinkEntry<T> next;
      protected LinkEntry<T> prev;

      protected LinkEntry() { element = null; next = null; prev = null;}
   }
} /* CS401StackLinkedListImpl<E> */