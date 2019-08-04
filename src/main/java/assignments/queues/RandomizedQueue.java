package assignments.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot
    
	// construct an empty randomized queue
    @SuppressWarnings("unchecked")
	public RandomizedQueue() {
    	q = (Item[]) new Object[2];
    	first = 0;
		last = 0;
		n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return n;
    }
    
    // resize the underlying array
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= n;
		Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = n;
    }

    // add the item
    public void enqueue(Item item) {
    	// double size of array when it's full and recopy to beginning of array
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    	Item item = q[first];
        q[first] = null;                            // to avoid loitering
        n--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // halve size of the array when it's one-quarter full
        if (n > 0 && n == q.length/4) resize(q.length/2); 
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	Item randomItem = q[StdRandom.uniform(n)];
    	return randomItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
    	return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        
        public ArrayIterator() {
        	StdRandom.shuffle(q, 0, n);
        }
        
        public boolean hasNext()  { return i < n; }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }

 	// unit testing (required)
    public static void main(String[] args) {
    	RandomizedQueue<String> rq = new RandomizedQueue<String>();
    	String s;
    	// enqueue()
    	while (!(s = StdIn.readString()).equals("-")) {
            rq.enqueue(s);
        }
    	
    	// isEmpty()
    	StdOut.printf("Queue is empty: %s\n", rq.isEmpty());
    	
    	// size()
    	StdOut.printf("Queue size: %s\n", rq.size());
    	
    	// iterator()
    	for (String word: rq)
    		StdOut.printf("%s\n", word);
    	
    	// sample()
    	StdOut.printf("Sample random item: %s\n", rq.sample());
    	
    	// dequeue()
    	StdOut.printf("Dequeue random item: %s\n", rq.dequeue());
    }

}