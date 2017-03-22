import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class InfixToPostfix {
	
	private static Map<String, Precedence> opPrec;
	private static List<String> operands = new ArrayList<String>();
	
	public static void main(String[] args) {
		Scanner sc = null, lineScanner = null;
		List<String> variables = new ArrayList<String>();
		fillPrecedenceMap();
		while(true) {
			try {
				System.out.print("Please enter a condition, or $ to quit: ");
				sc = new Scanner(System.in);
				String condition = sc.nextLine();
				if(condition.charAt(0) == '$')
					break;
				lineScanner = new Scanner(condition).useDelimiter("[^a-zA-Z]");
				String var = "";
				while(lineScanner.hasNext()) {
					var = lineScanner.next();
					if(!variables.contains(var) && !var.equals(""))
						variables.add(var);
				}
				String value;
				operands.clear();
				for(String s : variables) {
					System.out.print("\n\nPlease enter a value: ");
					value = sc.nextLine().trim();
					operands.add(value);
					condition = condition.replace(s, value);
				}
				
				convertToPostfix(condition);
				variables.clear();
			} catch (Exception e) {
				e.printStackTrace();
				lineScanner.close();
				sc.close();
			}
		}
		sc.close();
		lineScanner.close();
	}
	
	public static void convertToPostfix(String line) {
		CS401StackLinkedListImpl<String> opStack = new CS401StackLinkedListImpl<String>();
		Set<String> operators = opPrec.keySet();
		Character c, c2 = null;
		String tmp;
		boolean tooFar = false;
		String postfix = "";
		System.out.println("Infix: " + line);
		Scanner lineScanner = new Scanner(line);
		String op = "";
		
		while(lineScanner.hasNext()) {
			c = lineScanner.findInLine("[^\\s]").charAt(0);
			if(c == '!' || c == '=' || c == '&' || c == '|') {
				c2 = lineScanner.findInLine("[^\\s]").charAt(0);
				op = new StringBuilder().append(c).append(c2).toString();
			}
			else if(c == '<' || c == '>') {
				c2 = lineScanner.findInLine("[^\\s]").charAt(0);
				if(c2 == '=')
					op = new StringBuilder().append(c).append(c2).toString();
				else {
					tooFar = true; // got an extra character
					op = c.toString();
				}
			}
			else // one character operator or digit
				op = c.toString();
			if(operators.contains(op)) {
				if(op.equals(")")) {
					op = opStack.pop();
					while(!"(".equals(op)) {
						postfix += op;
						op = opStack.pop(); // when it equals (, discard it
					}
				}
				else if(opStack.size() == 0 || opPrec.get(op).ordinal() > opPrec.get(opStack.top()).ordinal() || op.equals("("))
					opStack.push(op);
				else { // operator has lower precedence than top of the stack
					tmp = op; // save operator with lower precedence
					op = opStack.pop(); // pop operator with higher precedence
					postfix += op; // append higher precedence operator to the string
					
					while(opStack.top() != null && opPrec.get(tmp).ordinal() < opPrec.get(opStack.top()).ordinal()) {
						op = opStack.pop();
						postfix += op;
					}
					opStack.push(tmp); // push the lower precedence operator on the stack, only element on the stack
				}
				if(tooFar) {
					postfix += c2; // after pushing the operator > or <, append the character too far to the string
					tooFar = false;
				}
			}
			else
				postfix += c; // character is a digit
		}
		
		op = opStack.pop(); // pop remaining elements off the stack
		while(op != null) {
			postfix += op;
			op = opStack.pop();
		}
		
		lineScanner.close();
		System.out.println("Postfix: " + postfix);
		System.out.println("Answer: " + evaluatePostfix(postfix) + "\n");
	}
	
	public static String evaluatePostfix(String expression) {
		CS401StackLinkedListImpl<String> resultStack = new CS401StackLinkedListImpl<String>();
		Scanner lineScanner = new Scanner(expression);
		float op2;
		boolean halfOp = false;
		char c2 = '0';

		while(lineScanner.hasNext()) {
			String op = "";
			boolean tooFar = false;
			char c = lineScanner.findInLine("[^\\s]").charAt(0);
			if(c == '!' || c == '=' || c == '&' || c == '|') {
				if(halfOp) { // not possible for c to be ! here
					op = new StringBuilder().append(c2).append(c).toString();
				}
				else {
					if(lineScanner.hasNext()) {
						c2 = lineScanner.findInLine("[^\\s]").charAt(0);
						op = new StringBuilder().append(c).append(c2).toString();
					}
				}
			}
			
			else if(c == '<' || c == '>') {
				if(lineScanner.hasNext()) {
					c2 = lineScanner.findInLine("[^\\s]").charAt(0);
					if(c2 == '=')
						op = new StringBuilder().append(c).append(c2).toString();
					else if(c2 == '!' || c2 == '=' || c2 == '&' || c2 == '|'){ // ||, etc., following < or >
						halfOp = true;
						op+=c;
					}
					else { // operator is < or >
						tooFar = true;
						op+=c;
					}
				}
				else
					op+=c;
			}
			
			else 
				if(Character.isDigit(c)) {
					Character box = (Character)c;
					String operand = box.toString();
					if(operands.contains(operand)) // can break if 1x and 1 or 2x and 2, etc are each inputs
						resultStack.push(Integer.toString(c - '0'));
					else { // two digit number
						operand = "";
						c2 = lineScanner.findInLine("[^\\s]").charAt(0); // advance to ones digit of two digit number
						operand += c;
						operand += c2;
						resultStack.push(operand);
					}
				}
				else
					op += c; // c is one character operator
			
			switch(op) {
			case "+":
				resultStack.push(Float.toString(Float.parseFloat(resultStack.pop()) + Float.parseFloat(resultStack.pop())));
				break;
			case "-":
				op2 = Float.parseFloat(resultStack.pop());
				resultStack.push(Float.toString(Float.parseFloat(resultStack.pop()) - op2));
				break;
			case "*":
				resultStack.push(Float.toString(Float.parseFloat(resultStack.pop()) * Float.parseFloat(resultStack.pop())));
				break;
			case "/":
				op2 = Float.parseFloat(resultStack.pop());
				resultStack.push(Float.toString(Float.parseFloat(resultStack.pop()) / op2));
				break;
			case "%":
				op2 = Float.parseFloat(resultStack.pop());
				resultStack.push(Float.toString(Float.parseFloat(resultStack.pop()) % op2));
				break;
			case ">":
				op2 = Float.parseFloat(resultStack.pop());
				resultStack.push(Boolean.toString(Float.parseFloat(resultStack.pop()) > op2));
				break;
			case "<":
				op2 = Float.parseFloat(resultStack.pop());
				resultStack.push(Boolean.toString(Float.parseFloat(resultStack.pop()) < op2));
				break;
			case ">=":
				op2 = Float.parseFloat(resultStack.pop());
				resultStack.push(Boolean.toString(Float.parseFloat(resultStack.pop()) >= op2));
				break;
			case "<=":
				op2 = Float.parseFloat(resultStack.pop());
				resultStack.push(Boolean.toString(Float.parseFloat(resultStack.pop()) <= op2));
				break;
			case "==":
				resultStack.push(Boolean.toString(Boolean.parseBoolean(resultStack.pop()) == Boolean.parseBoolean(resultStack.pop())));
				break;
			case "!=":
				resultStack.push(Boolean.toString(Boolean.parseBoolean(resultStack.pop()) != Boolean.parseBoolean(resultStack.pop())));
				break;
			case "&&":
				resultStack.push(Boolean.toString(Boolean.parseBoolean(resultStack.pop()) && Boolean.parseBoolean(resultStack.pop())));
				break;
			case "||":
				resultStack.push(Boolean.toString(Boolean.parseBoolean(resultStack.pop()) || Boolean.parseBoolean(resultStack.pop())));
				break;
			default:
				break;
			}
			if(tooFar) {
				Character box = (Character)c2;
				String operand = box.toString();
				if(operands.contains(operand)) // can break if 1x and 1 or 2x and 2, etc are each inputs
					resultStack.push(Integer.toString(c2 - '0'));
				else { // two digit number
					operand = "";
					c = lineScanner.findInLine("[^\\s]").charAt(0); // advance to ones digit of two digit number
					operand += c2;
					operand += c;
					resultStack.push(operand);
				}
			}
		}
		
		lineScanner.close();
		return resultStack.pop();
	}
	
	private static void fillPrecedenceMap() {
		if(opPrec == null)
			opPrec = new HashMap<String, Precedence>();
		
		opPrec.put("+", Precedence.ADD);
		opPrec.put("-", Precedence.ADD);
		opPrec.put("*", Precedence.MULT);
		opPrec.put("/", Precedence.MULT);
		opPrec.put("%", Precedence.MULT);
		opPrec.put("(", Precedence.PAREN);
		opPrec.put(")", Precedence.PAREN);
		opPrec.put("||", Precedence.OR);
		opPrec.put("&&", Precedence.AND);
		opPrec.put("==", Precedence.EQUALITY);
		opPrec.put("!=", Precedence.EQUALITY);
		opPrec.put(">=", Precedence.GREATLESS);
		opPrec.put(">", Precedence.GREATLESS);
		opPrec.put("<=", Precedence.GREATLESS);
		opPrec.put("<", Precedence.GREATLESS);
	}
	
	private static enum Precedence{
		PAREN,
		OR,
		AND,
		EQUALITY,
		GREATLESS,
		ADD,
		MULT;
	}
}