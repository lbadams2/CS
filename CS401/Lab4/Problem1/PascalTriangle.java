import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PascalTriangle {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter r for recursive version or i for iterative version: ");
		char in = sc.nextLine().charAt(0);
		if(in == 'i'){
			System.out.println("Enter number of rows for triangle: ");
			int n = 0;
			try {
				n = sc.nextInt();
			}
			catch(Exception e){
				e.getStackTrace();
			}
			finally {
				sc.close();
			} 
			List<String> result = getTriangle(n);
			for(String s : result)
				System.out.println(s);
		}
		else if(in == 'r') {
			System.out.println("Enter row, element: ");
			String line = sc.nextLine();
			String[] elements = line.split(",");
			int result = getTriangle(Integer.parseInt(elements[0].trim()), Integer.parseInt(elements[1].trim()));
			System.out.println("pascal(" + elements[0] + "," + elements[1] + "): " + result );
			sc.close();
		}
		else {
			sc.close();
			throw new IllegalArgumentException("Invalid Input");
		}
	}
	
	private static List<String> getTriangle(int n) {
		if(n < 1)
			throw new IllegalArgumentException();
		else
			return pascIter(n);
	}
	
	private static int getTriangle(int row, int element) {
		if(element > row || row < 1 || element < 1)
			throw new IllegalArgumentException();
		else 
			return pasc(row, element);
	}
	
	private static List<String> pascIter(int n){
		List<String> triangle = new ArrayList<String>();
		triangle.add("1");
		String currentLine = null;
		int sum = 0, currentNumber = 0, prev = 0;
		for(int i=0; i<n-1; i++){
			StringBuilder nextEntry = new StringBuilder();
			currentLine = triangle.get(i);
			prev = 0;
			for(int j=0; j<triangle.get(i).length(); j+=2){
				String currentNumberString = "";
				while(Character.isDigit(currentLine.charAt(j))) {
					currentNumberString += currentLine.charAt(j);
					if(++j >= currentLine.length())
						break;
				}
				j-=1; // ungetch
				currentNumber = Integer.parseInt(currentNumberString);
				sum = prev + currentNumber;
				nextEntry.append(sum);
				nextEntry.append(" ");
				prev = currentNumber;
			}
			nextEntry.append("1");
			triangle.add(nextEntry.toString());
		}
		return triangle;
	}
	
	private static int pasc(int row, int element) {
		if(row == element)
			return 1;
		else if(element == 1)
			return 1;
		else
			return pasc(row -1, element) + pasc(row-1, element-1);
	}
}