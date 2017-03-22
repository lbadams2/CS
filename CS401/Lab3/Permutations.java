
public class Permutations {
	
	private static String result = "";
	public static void main(String[] args) {
		permute("ABCD");
		System.out.println(result);
	}
	
	public static void permute (String s)
	  { 
	     recPermute (s.toCharArray(), 0);
	  } 
	
	/*
	 * first invocation of recPermute will change the value of the first character, 2nd will change value of second character.. k stays constant in 
	 * each instance of method, position of characters are restored before incrementing i to perform another swap with the kth character
	 * the number of swaps each instance of method will perform decreases each level of nesting, until no swaps are performed for k=3, the 
	 * position of the last character, and the array will be printed
	*/
	protected static void recPermute(char[] s, int k) {
		int length = s.length;
		if(k == length-1){ // maximum index is reached
			for(int i = 0; i<length; i++)
				result += s[i];
			result += '\n';
		}
		// iterate over substring beginning at index provided by k
		for(int i =k; i<length; i++){
			swap(s, i, k); 
			recPermute(s, k+1);
			swap(s, i, k);
		}
	}
	
	private static void swap(char[] s, int i, int j) {
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}
}
