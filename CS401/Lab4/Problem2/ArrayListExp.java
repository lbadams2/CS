import java.util.ArrayList;
import java.util.List;


public class ArrayListExp {
	
	private List<String> myList;
	
	public static void main(String[] args) {
		new ArrayListExp().quiz();
	}
	
	protected String exp1() {
		myList = new ArrayList<String>();
		myList.add ("yes");
		myList.add ("no");
		myList.add ("maybe");
		return myList.toString();
	}
	
	protected String exp2() {
		if(myList != null) {
			myList.add ("blue");
			myList.add ("red");
			myList.remove ("no");
			return myList.toString();
		}
		else
			throw new RuntimeException("List not initialized");
	}
	
	protected void quiz(){
		exp1();
		exp2();
		myList.add (3, "hello");
		System.out.println(myList);
	}
}
