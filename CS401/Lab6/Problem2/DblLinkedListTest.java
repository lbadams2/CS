
public class DblLinkedListTest {
	
	public static void main(String[] args) {
		CS401DblLinkedListImpl<String> list = new CS401DblLinkedListImpl<String>();
		list.add("Bill");
		list.add("Rohan");
		list.add("James");
		list.add("Krishna");
		list.add("Javier");
		list.add("Lisa");
		System.out.println("---------Part a---------");
		list.print_from_beginning();
		System.out.println("\n---------Part b---------");
		list.print_from_end();
		list.remove(1);
		System.out.println("\n---------Part c---------");
		list.print_from_beginning();
		list.remove(5);
		System.out.println("\n---------Part d---------");
		list.print_from_end();
		list.remove(3);
		System.out.println("\n---------Part e---------");
		list.print_from_beginning();
	}
}
