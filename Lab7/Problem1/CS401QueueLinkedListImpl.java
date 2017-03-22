public class CS401QueueLinkedListImpl<E> implements CS401QueueInterface<E>  {
   private LinkEntry<E> head;
   private LinkEntry<E> tail;
   private int num_elements;

   public void add(E element) {
	  LinkEntry<E> ne = new LinkEntry<E>();
	  ne.element = element;
      if(head == null)
    	  head = tail = ne;
      else {
    	  tail.next = ne;
    	  tail = ne;
      }
      num_elements++;
   }

   public E remove()  {
	  if(is_empty())
		  throw new RuntimeException("Queue is empty");
      LinkEntry<E> tmp = head;
      head = head.next;
      num_elements--;
      return tmp.element;

   }
   public E peek()  {

      return head.element;

   }

   public boolean is_empty()  {

      return num_elements == 0;

   }

   public boolean is_full()  {

      throw new UnsupportedOperationException("Doesn't apply to linked list implementation");

   }

   /* ------------------------------------------------------------------- */
   /* Inner classes                                                      */
   protected class LinkEntry<E>
   {
      protected E element;
      protected LinkEntry<E> next;

      protected LinkEntry() { element = null; next = null; }
   }
}