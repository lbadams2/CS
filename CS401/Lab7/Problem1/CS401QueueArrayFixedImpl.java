/* 
 * Fixed front implementation of a Queue using arrays */

public class CS401QueueArrayFixedImpl<E> implements CS401QueueInterface<E>  {
   private E[] data;
   private final static int FRONT = 0;
   private int back;
   private int capacity;

   public CS401QueueArrayFixedImpl(int num_elems)   {
      capacity = num_elems;
      data = (E[]) new Object[capacity];
      back = 0;    
   }

   public void add(E element) {
      if(is_full())
    	  throw new RuntimeException("Queue is full");
      data[back++] = element;
   }

   public E remove()  {
	  if(is_empty())
		  throw new RuntimeException("Queue is empty");
      E element = data[FRONT];
      
      for(int i = 1; i < back; i++) 
    	  data[i-1] = data[i];
      back--;
      return element;
   }

   public E peek()  {

      return data[FRONT];
   }

   public boolean is_empty()  {

      return back == 0;
   }

   public boolean is_full()  {

      return back == capacity;

   }
}