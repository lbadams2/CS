import java.util.Iterator;

public class LinkedListTest {

	public static void main(String[] args) {
	   Chores a = new Chores("Make Bed", 10);
	   Chores b = new Chores("Do Laundry", 5);
	   Chores c = new Chores("Take out garbage", 20);
	   
	   //Test 1
	   System.out.println("----------Test 1----------");
	   CS401LinkedListImpl<Chores> test1 = new CS401LinkedListImpl<Chores>();
	   test1.add(a);
	   Iterator<Chores> iter = test1.iterator();
	   System.out.println("Constructed linked list with " + test1.size() + " object:\n " + getElemsString(iter) + iter.next());
	   	
	   iter = test1.iterator();
	   removeElem(iter, a);
       System.out.println("\nTraversing linked list:\n There are " + test1.size() + " elements on the linked list.");
       
       //Test 2
       System.out.println("\n----------Test 2----------");
       CS401LinkedListImpl<Chores> test2 = new CS401LinkedListImpl<Chores>();
	   test2.add(a);
	   test2.add(b);
	   iter = test2.iterator();
	   System.out.println("Constructed linked list with " + test2.size() + " objects:\n " + getElemsString(iter) + iter.next());
	   	
	   iter = test2.iterator();
	   removeElem(iter, a);
       System.out.println("\nTraversing linked list:\n There is " + test2.size() + " element on the linked list.\n  "
    		   	+ getElemsString(test2.iterator()) + iter.next());
       
       //Test 3
       System.out.println("\n----------Test 3----------");
       CS401LinkedListImpl<Chores> test3 = new CS401LinkedListImpl<Chores>();
	   test3.add(a);
	   test3.add(b);
	   iter = test3.iterator();
	   System.out.println("Constructed linked list with " + test3.size() + " objects:\n " + getElemsString(iter) + iter.next());
	   	
	   iter = test3.iterator();
	   removeElem(iter, b);
       System.out.println("\nTraversing linked list:\n There is " + test3.size() + " element on the linked list.\n  "
    		   	+ getElemsString(test3.iterator()) + iter.next());
       
     //Test 4
       System.out.println("\n----------Test 4----------");
       CS401LinkedListImpl<Chores> test4 = new CS401LinkedListImpl<Chores>();
	   test4.add(a);
	   test4.add(b);
	   test4.add(c);
	   iter = test4.iterator();
	   System.out.println("Constructed linked list with " + test4.size() + " objects:\n " + getElemsString(iter) + iter.next());
	   	
	   iter = test4.iterator();
	   removeElem(iter, b);
       System.out.println("\nTraversing linked list:\n There are " + test4.size() + " elements on the linked list.\n  "
    		   	+ getElemsString(test4.iterator()) + iter.next());
	}
	
	private static <E> String getElemsString(Iterator<E> iter){
	   String s = "";
	   while(iter.hasNext())
		   s += "[" + iter.next().toString() + "] -> ";
	   return s;
	}
	
	private static <E> void removeElem(Iterator<E> iter, E elemToRemove){
		while (iter.hasNext())  {
	          E elem = iter.next();
	          if (elem.equals(elemToRemove))  {
	             iter.remove();
	             System.out.println("\nDeleted object [" + elem +"].");
	          }
	    }
	}
}