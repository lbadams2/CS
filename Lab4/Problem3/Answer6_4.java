import java.util.ArrayList;


public class Answer6_4 {

	// ClassCastException
	public static void main(String[] args){
		ArrayList<String> original = new ArrayList<String>();
		original.add("yes");
		ArrayList<Integer> copy = (ArrayList<Integer>)original.clone();
		
		System.out.println(copy.get(0));
	}
	// prints yes, a string, from a collection that should contain integers
}
