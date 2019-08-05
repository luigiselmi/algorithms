package assignments.queues;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

  public static void main(String[] args) {
	RandomizedQueue<String> rq = new RandomizedQueue<String>();
	int k = Integer.parseInt(args[0]);
	while (! StdIn.isEmpty() ) {
        rq.enqueue(StdIn.readString());
    }
	
	int counter = 0;
	Iterator<String> i = rq.iterator();
	while(i.hasNext() && counter < k) {
		StdOut.printf("%s\n", i.next());
		counter++;
	}
}

}
