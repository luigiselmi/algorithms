package assignments.queues;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		int k = StdIn.readInt(); // n-by-n grid
		String s;
    	while (!(s = StdIn.readString()).equals("-")) {
            rq.enqueue(s);
        }
    	
    	for (int i = 0; i < k; i++)
    		System.out.println("Random string " + i + ": " + rq.sample());
	}

}
