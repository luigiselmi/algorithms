package chapter1.stack;

public class Reverse {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		
		stack.push("S");
		stack.push("i");
		stack.push("r");
		stack.push("i");
		stack.push("s");
		
		while( ! stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
