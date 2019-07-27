package fundamentals;

public class SimpleStackClient {

	public static void main(String[] args) {
		Stack<Integer> grades = new Stack<Integer>();
		grades.push(5);
		grades.push(4);
		grades.push(7);
		grades.push(8);
		grades.push(6);
		
		int N = grades.size();
		
		// Copy the data in the stack to an array of the same size
		int [] a = new int [N];
				
		for (int i = 0; i < N; i++)
			a[i] = grades.pop();
		
		// print values with LIFO policy
		for (int j = 0; j < a.length; j++)
			System.out.println(a[j]);
	}

}
