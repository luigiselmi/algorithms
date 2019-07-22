package chapter1;
/**
 * A simplified version of the Evaluate class from
 * Sedgewick book Algorithms, 4th ed. p.129
 *
 */
public class Evaluate {

	public static void main(String[] args) {
		
	Stack<Character> ops = new Stack<Character>();
	Stack<Integer> vals = new Stack<Integer>();
	
	char [] expression = "(1 + ((2 + 3) * (4 * 5)))".toCharArray(); //101
	
	for(int i = 0; i < expression.length; i++) {
		char s = expression[i];
		if (s == '(' || s == ' ') ;
		else if (s == '+') ops.push(s);
		else if (s == '-') ops.push(s);
		else if (s == '*') ops.push(s);
		else if (s == '/') ops.push(s);
		else if (s == ')') { // Pop, evaluate, and push result if token is ")".
			char op = ops.pop();
			int v = vals.pop();
			if (op == '+') v = vals.pop() + v;
			else if (op == '-') v = vals.pop() - v;
			else if (op == '*') v = vals.pop() * v;
			else if (op == '/') v = vals.pop() / v;
			vals.push(v);
		} // Token not operator or paren: push double value.
		else vals.push(s - '0');
		}
		System.out.println(vals.pop());
	}
}
