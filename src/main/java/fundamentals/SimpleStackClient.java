package fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleStackClient {

	public static void main(String[] args) throws IOException {
		Stack<String> grades = new Stack<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String grade;
			while ( !(grade = reader.readLine()).equals("") ) {
				grades.push(grade);
			}
		}
		finally {
			reader.close();
		}
		
		int N = grades.size();
		
		// Copy the data in the stack to an array of the same size
		String [] a = new String [N];
				
		for (int i = 0; i < N; i++)
			a[i] = grades.pop();
		
		// print values with LIFO policy
		for (int j = 0; j < a.length; j++)
			System.out.println(a[j]);
	}

}
