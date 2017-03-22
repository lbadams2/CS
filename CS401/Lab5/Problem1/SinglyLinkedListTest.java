import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;


public class SinglyLinkedListTest {
	
	protected SinglyLinkedList<String> myList;
	
	@Before
	public void beforeEachTest() {
		myList = new SinglyLinkedList<String>();
		myList.addToFront("Flakes");
		myList.addToFront("Wax");
		myList.addToFront("Bulb");
		myList.addToFront("Yellow");
		myList.addToFront("Infest");
	}
	
	@Test
	public void removeTest() {
		String element = myList.remove(2);
		assertEquals("Bulb", element);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeTest2() {
		myList.remove(5);
	}
	
	@Test
	public void removeTest3() {
		String element = myList.remove(0);
		assertEquals("Infest", element);
	}
	
	@Test
	public void removeTest4() {
		myList.remove("Bulb");
		assertEquals(4, myList.size());
		assertEquals(myList.get(2), "Wax");
	}
	
	@Test
	public void removeTest5() {
		myList.remove("Infest");
		assertEquals(4, myList.size());
		assertEquals(myList.get(0), "Yellow");
	}
	
	@Test
	public void lastIndexOfTest() {
		myList.addToFront("Bulb");
		assertEquals(3, myList.lastIndexOf("Bulb"));
	}
	
	@Test 
	public void indexOfTest() {
		assertEquals(1, myList.indexOf("Yellow"));
	}
	
	@Test
	public void getTest() {
		assertEquals(myList.get(0), "Infest");
		assertEquals(myList.get(2), "Bulb");
		assertEquals(myList.get(3), "Wax");
	}
	
	@Test
	public void addIndexTest() {
		myList.add(1, "Boces");			
		assertEquals(6, myList.size());
		assertEquals(myList.get(1), "Boces");
		assertEquals(myList.get(2), "Yellow");
	}
	
	@Test
	public void removeAllTest() {
		Collection<String> c = new SinglyLinkedList<String>();
		c.add("Wax");
		c.add("Infest");
		myList.removeAll(c);
		assertEquals(myList.size(), 3);
		assertEquals("[Yellow, Bulb, Flakes]", myList.toString());
	}
	
	@Test
	public void addAllTest() {
		Collection<String> c = new SinglyLinkedList<String>();
		c.add("Gord");
		c.add("Queen");
		myList.addAll(3, c);
		assertEquals(myList.size(), 7);
		assertEquals("Gord", myList.get(3));
		assertEquals("Queen", myList.get(4));
	}
	
	@Test
	public void toArrayTest() {
		Object[] array = myList.toArray();
		for(int i = 0; i < myList.size(); i++)
			assertEquals(array[i], myList.get(i));
	}
	
	@Test
	public void equalsTest() {
		SinglyLinkedList<String> myList2 = new SinglyLinkedList<String>();
		myList2.addToFront("Flakes");
		myList2.addToFront("Wax");
		myList2.addToFront("Bulb");
		myList2.addToFront("Yellow");
		myList2.addToFront("Infest");
		
		assertEquals(true, myList.equals(myList2));
	}
	
	@Test
	public void equalsTest2() {
		SinglyLinkedList<String> myList2 = new SinglyLinkedList<String>();
		myList2.addToFront("Flakes");
		myList2.addToFront("Jowel");
		myList2.addToFront("Bulb");
		myList2.addToFront("Yellow");
		myList2.addToFront("Infest");
		
		assertEquals(false, myList.equals(myList2));
	}
	
	@Test
	public void addTest(){
		myList.add("Kerosene");
		assertEquals("Kerosene", myList.get(5));
	}
	
	@Test
	public void addTest2() {
		SinglyLinkedList<String> myList2 = new SinglyLinkedList<String>();
		myList2.add("Lice");
		myList2.add("Bubbles");
		assertEquals("Bubbles", myList2.get(1));
	}
}
