package fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class SimpleQueueClient {

	public static void main(String[] args) throws IOException {
		Queue<String> grades = new Queue<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String grade;
			while ( !(grade = reader.readLine()).equals("") ) {
				grades.enqueue(grade);
			}
		}
		finally {
			reader.close();
		}
		// loop supported by the queue iterator
		for (String grade: grades) {
			System.out.println(grade);
		}
		
		int N = grades.size();
		
		// Copy the data in the queue to an array of the same size
		String [] a = new String [N];
		
		for (int i = 0; i < N; i++) {
			a[i] = grades.dequeue();
		}
		
		// print the values in the array
		for (int j = 0; j < a.length; j++)
			System.out.println(a[j]);
	}

}
