import java.util.ArrayList;
import java.util.Arrays;


public class Answer6_13 {

	public static void main(String[] args) {
		ArrayList<Integer> myList = new ArrayList<Integer>(Arrays.asList(new Integer[] {3,8,6,4,8,7,8,9,4}));
		System.out.println(uniquefy(myList));
	}
	
	public static <T> ArrayList<T> uniquefy (ArrayList<T> list) {
		ArrayList<T> uniqueList = new ArrayList<T>();
		if(list == null)
				throw new NullPointerException("List passed to uniquefy is null");
		
		for(T elem : list) 
			if(!uniqueList.contains(elem))
				uniqueList.add(elem);
		
		return uniqueList;
	}
}
