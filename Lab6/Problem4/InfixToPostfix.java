import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class InfixToPostfix {
	
	private static Map<Character, Precedence> opPrec;
	
	public static void main(String[] args) {
		Scanner sc = null;
		fillPrecedenceMap();
		try {
			sc = new Scanner(new File("infix.txt"));
			while(sc.hasNext())
				convertToPostfix(sc.nextLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}
	
	private static void convertToPostfix(String line) {
		CS401StackLinkedListImpl<Character> opStack = new CS401StackLinkedListImpl<Character>();
		Set<Character> operators = opPrec.keySet();
		Character c, tmp;
		String postfix = "";
		System.out.println("Infix: " + line);
		Scanner lineScanner = new Scanner(line);
		
		while(lineScanner.hasNext()) {
			c = lineScanner.findInLine("[^\\s]").charAt(0);
			if(operators.contains(c)) {
				if(c == ')') {
					c = opStack.pop();
					while(c != '(') {
						postfix += c;
						c = opStack.pop();
					}
				}
				else if(opStack.size() == 0 || opPrec.get(c).ordinal() > opPrec.get(opStack.top()).ordinal() || c == '(')
					opStack.push(c);
				else {
					tmp = c;
					c = opStack.pop();
					postfix += c;
					
					c = opStack.pop();
					while(c != null) {
						postfix += c;
						c = opStack.pop();
					}
					
					opStack.push(tmp);
				}
			}
			else
				postfix += c;
		}
		
		c = opStack.pop();
		while(c != null) {
			postfix += c;
			c = opStack.pop();
		}
		
		lineScanner.close();
		System.out.println("Postfix: " + postfix);
		System.out.println("Answer: " + evaluatePostfix(postfix) + "\n");
	}
	
	private static float evaluatePostfix(String expression) {
		CS401StackLinkedListImpl<Float> resultStack = new CS401StackLinkedListImpl<Float>();
		Scanner lineScanner = new Scanner(expression);
		float op2;
		
		while(lineScanner.hasNext()) {
			char c = lineScanner.findInLine("[^\\s]").charAt(0);
			
			if(Character.isDigit(c))
				resultStack.push((float)(c - '0'));
			
			switch(c) {
			case '+':
				resultStack.push(resultStack.pop() + resultStack.pop());
				break;
			case '-':
				op2 = resultStack.pop();
				resultStack.push(resultStack.pop() - op2);
				break;
			case '*':
				resultStack.push(resultStack.pop() * resultStack.pop());
				break;
			case '/':
				op2 = resultStack.pop();
				resultStack.push(resultStack.pop() / op2);
				break;
			default:
				break;
			}
		}
		
		lineScanner.close();
		return resultStack.pop();
	}
	
	private static void fillPrecedenceMap() {
		if(opPrec == null)
			opPrec = new HashMap<Character, Precedence>();
		
		opPrec.put('+', Precedence.ADD);
		opPrec.put('-', Precedence.ADD);
		opPrec.put('*', Precedence.MULT);
		opPrec.put('/', Precedence.MULT);
		opPrec.put('(', Precedence.PAREN);
		opPrec.put(')', Precedence.PAREN);
	}
	
	private static enum Precedence{
		PAREN,
		ADD,
		MULT;
	}
}