import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class QueueArrayTest {

	private CS401QueueInterface<String> queue;
	
	@Before
	public void beforeEachTest() {
		queue = new CS401QueueArrayFloatingImpl<String>(5);
		queue.add("Yeast");
		queue.add("Fizz");
		queue.add("Chin");
		queue.add("Pulp");
		queue.add("Vile");
	}
	
	@Test
	public void test1() {
		Assert.assertEquals(false, queue.is_empty());
		Assert.assertEquals(true, queue.is_full());
		Assert.assertEquals("Yeast", queue.peek());
	}
	
	@Test
	public void test2() {
		for(int i = 0; i < 5; i++)
			queue.remove();
		Assert.assertEquals(true, queue.is_empty());
		Assert.assertEquals(false, queue.is_full());
	}
	
	@Test
	public void test3() {
		Assert.assertEquals("Yeast", queue.remove());
		Assert.assertEquals("Fizz", queue.remove());
		Assert.assertEquals(false, queue.is_empty());
		Assert.assertEquals(false, queue.is_full());
		queue.add("Leather");
		queue.add("Sew");
		Assert.assertEquals(false, queue.is_empty());
		Assert.assertEquals(true, queue.is_full());
	}
	
	@Test (expected = RuntimeException.class)
	public void test4() {
		queue.add("Feel");
	}
	
	@Test (expected = RuntimeException.class)
	public void test5() {
		for(int i = 0; i < 6; i++)
			queue.remove();
	}
	
	@Test
	public void test6() {
		for(int i = 0; i < 4; i++)
			queue.remove();
		Assert.assertEquals("Vile", queue.remove());
		queue.add("Wood");
		queue.add("Furnace");
		queue.add("Scratch");
		queue.add("Pillow");
		queue.add("Paint");
		Assert.assertEquals(false, queue.is_empty());
		Assert.assertEquals(true, queue.is_full());
		Assert.assertEquals("Wood", queue.peek());
	}
}
