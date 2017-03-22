import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;


public class ArrayListTest {
	
	private ArrayListExp testedClass;
	
	@Before
	public void init() {
		testedClass = new ArrayListExp();
	}
	
	@Test
	public void exp1Test() {
		String result = testedClass.exp1();
		Assert.assertEquals("[yes, no, maybe]", result);
	}
	
	@Test
	public void exp2Test(){
		testedClass.exp1();
		String result = testedClass.exp2();
		Assert.assertEquals("[yes, maybe, blue, red]", result);
	}
}
