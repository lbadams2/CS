/* 
 * Fixed front implementation of a Queue using arrays */

public class CS401QueueArrayFloatingImpl<E> implements CS401QueueInterface<E>  {
   private E[] data;
   private int front, back;
   private int capacity;
   private int num_elements;

   public CS401QueueArrayFloatingImpl(int num_elems)   {
      capacity = num_elems;
      data = (E[]) new Object[capacity];
      front = back = 0;    
   }

   public void add(E element) {
      if(is_full())
    	  throw new RuntimeException("Queue is full");
      data[back++] = element;
      back %= capacity;
      num_elements++;
   }

   public E remove()  {
      if(is_empty())
    	  throw new RuntimeException("Queue is empty");
      E element = data[front];
      front  = (front + 1) % capacity;
      num_elements--;
      return element;
   }

   public E peek()  {

      return data[front];
   }

   public boolean is_empty()  {
	  return num_elements == 0;
   }

   public boolean is_full()  {
	  return num_elements == capacity;
   }
}