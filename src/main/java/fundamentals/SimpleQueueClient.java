package fundamentals;

import java.util.Iterator;

public class SimpleQueueClient {

	public static void main(String[] args) {
		Queue<Integer> grades = new Queue<Integer>();
		grades.enqueue(5);
		grades.enqueue(4);
		grades.enqueue(7);
		grades.enqueue(8);
		grades.enqueue(6);
		
		// loop supported by the queue iterator
		for (int grade: grades) {
			System.out.println(grade);
		}
		
		int N = grades.size();
		
		// Copy the data in the queue to an array of the same size
		int [] a = new int [N];
		
		for (int i = 0; i < N; i++) {
			a[i] = grades.dequeue();
		}
		
		// print the values in the array
		for (int j = 0; j < a.length; j++)
			System.out.println(a[j]);
	}

}
