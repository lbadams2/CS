import org.junit.Assert;
import org.junit.Test;


public class FullTimeEmployeeTest {
	
	@Test
	public void reflexivityTest() {
		FullTimeEmployee fte  = new FullTimeEmployee();
		Assert.assertTrue(fte.equals(fte));
	}
	
	@Test
	public void symmetryTest() {
		FullTimeEmployee fte1 = new FullTimeEmployee("fte1", 23.0);
		FullTimeEmployee fte2 = new FullTimeEmployee("fte2", 24.0);
		
		Assert.assertEquals(fte1.equals(fte2), fte2.equals(fte1));
	}
	
	@Test
	public void transitivityTest() {
		FullTimeEmployee x = new FullTimeEmployee("fte", 23.0);
		FullTimeEmployee y = new FullTimeEmployee("fte", 23.0);
		FullTimeEmployee z = new FullTimeEmployee("fte", 23.0);
		
		Assert.assertTrue(x.equals(y));
		Assert.assertTrue(y.equals(z));
		Assert.assertTrue(x.equals(z));
		
	}
	
	@Test
	public void consistencyTest() {
		FullTimeEmployee x = new FullTimeEmployee("fte", 23.0);
		FullTimeEmployee y = new FullTimeEmployee("fte", 24.0);
		for(int i = 0; i<10; i++)
			if(!x.equals(y))
				System.out.println("x does not equal y, as expected - iteration " + i);
			else
				Assert.fail();
	}
	
	@Test
	public void actualityTest() {
		FullTimeEmployee x = new FullTimeEmployee("fte", 23.0);
		Assert.assertFalse(x.equals(null));
	}
}
