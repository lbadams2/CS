/* 
 * Fixed front implementation of a Queue using arrays */

public class CS401QueueArrayFloatingImpl<E> implements CS401QueueInterface<E>  {
   private E[] data;
   private int front, back, prevBack, prevFront;
   private int capacity;

   public CS401QueueArrayFloatingImpl(int num_elems)   {
      capacity = num_elems;
      data = (E[]) new Object[capacity];
      front = back = prevBack = 0;    
   }

   public void add(E element) {
      if(is_full())
    	  throw new RuntimeException("Queue is full");
      prevBack = back;
      data[back++] = element;
      back %= capacity;
   }

   public E remove()  {
      if(is_empty())
    	  throw new RuntimeException("Queue is empty");
      E element = data[front];
      prevFront = front;
      front  = (front + 1) % capacity;
      return element;
   }

   public E peek()  {

      return data[front];
   }

   public boolean is_empty()  {
	  if(front == 0 && back == 0 && prevBack == 0)
		  return true;
	  else if(back == front && front > prevBack)
		  return true;
	  else if(back == front && prevFront - back == capacity - 1)
		  return true;
	  else
		  return false;
   }

   public boolean is_full()  {
	  if(prevBack - front == capacity - 1 && prevFront != capacity - 1)
		  return true;
	  else if(prevBack == front - 1)
		  return true;
	  else
		  return false;
   }
}