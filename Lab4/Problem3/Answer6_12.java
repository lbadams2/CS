import java.util.ArrayList;
import java.util.Iterator;


public class Answer6_12 {
	
	private ArrayList<String> words;
	
	public static void main(String[] args) {
		Answer6_12 ans = new Answer6_12();
		System.out.println("All words in list: " + ans.getWords());
		System.out.println("\nIndex version:");
		ans.index();
		System.out.println("\nIterator version:");
		ans.iterator();
		System.out.println("\nEnhanced for version:");
		ans.enhancedFor();
	}
	
	public ArrayList<String> getWords() {
		return words;
	}
	
	public Answer6_12(){
		words = new ArrayList<String>();
		words.add("green");
		words.add("vase");
		words.add("doll");
		words.add("crust");
		words.add("hair");
	}
	
	private void index() {
		String word;
		for(int i = 0; i<words.size(); i++) {
			word = words.get(i);
			if(word.length() == 4)
				System.out.println(word);
		}
	}
	
	private void iterator() {
		Iterator<String> itr = words.iterator();
		String word;
		while(itr.hasNext()) {
			word = itr.next();
			if(word.length() == 4)
				System.out.println(word);
		}
	}

	private void enhancedFor() {
		for(String s : words) 
			if(s.length() == 4)
				System.out.println(s);
	}
}
