import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StackTest {

	private CS401StackInterface<String> stack;
	
	@Before
	public void beforeEachTest() {
		stack = new CS401StackLinkedListImpl<String>();
		stack.push("Termites");
		stack.push("Honey");
		stack.push("Seeds");
	}
	
	@Test
	public void test1() {
		Assert.assertEquals("Seeds", stack.pop());
	}
	
	@Test
	public void test2() {
		Assert.assertEquals(3, stack.size());
	}
	
	@Test 
	public void test3() {
		stack.pop();
		stack.push("Eggs");
		Assert.assertEquals(3, stack.size());
		Assert.assertEquals("Eggs", stack.pop());
		Assert.assertEquals(2, stack.size());
	}
	
	@Test
	public void test4() {
		stack.push("Larvae");
		stack.push("Nails");
		Assert.assertEquals(5, stack.size());
		Assert.assertEquals("Nails", stack.pop());
		Assert.assertEquals(4, stack.size());
		stack.push("Glare");
		Assert.assertEquals(5, stack.size());
		Assert.assertEquals("Glare", stack.pop());
		Assert.assertEquals("Larvae", stack.top());
		Assert.assertEquals(4, stack.size());
		Assert.assertEquals("Larvae", stack.top());
		stack.push("Juice");
		Assert.assertEquals("Juice", stack.top());
		Assert.assertEquals(5, stack.size());
		Assert.assertEquals("Juice", stack.pop());
		Assert.assertEquals(4, stack.size());
	}
	
	@Test
	public void test5() {
		Assert.assertEquals("Seeds", stack.top());
		Assert.assertEquals(3, stack.size());
	}
	
	@Test
	public void test6() {
		int i = 4;
		while(i > 0) {
			stack.pop();
			i--;
		}
	}
}
