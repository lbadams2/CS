import org.junit.Assert;
import org.junit.Test;


public class Lab21Test {

	@Test
	public void test1() {
		FairPQ<Student> pq = new FairPQ<Student>();
		Student s1 = new Student("Soumya", 3.4);
		Student s2 = new Student("Navdeep", 3.5);
		Student s3 = new Student("Viet", 3.5);
		pq.add(s1);
		pq.add(s2);
		pq.add(s3);
		Student[] result = new Student[3];
		
		System.out.println("Order of removal:");
		Student tmp;
		for(int i = 0; i < 3; i++) {
			tmp = pq.remove();
			System.out.println(tmp);
			result[i] = tmp;
		}
		
		Assert.assertEquals(result[0], s1);
		Assert.assertEquals(result[1], s2);
		Assert.assertEquals(result[2], s3);
	}
}
