package assignments.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {
	
	private DoubleNode<Item> first;
	private DoubleNode<Item> last;
	private int numberOfItems;
	
	// Linked list class
    private class DoubleNode<Item> {
        private Item item;
        private DoubleNode<Item> next;
        private DoubleNode<Item> previous;
    }

    // construct an empty deque
    public Deque() {
    	first = null;
		last = null;
		numberOfItems = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return numberOfItems == 0 ? true : false;
    }

    // return the number of items on the deque
    public int size() {
    	return numberOfItems;
    }

    // add the item to the front (as in push() for stacks)
    public void addFirst(Item item) {
    	if (item == null) throw new IllegalArgumentException("item cannot be null.");
    	DoubleNode<Item> oldfirst = first;
        first = new DoubleNode<Item>();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty())
        	last = first;
        else
        	oldfirst.previous = first;
        numberOfItems++;
    }

    // add the item to the back (as in enqueue() for queues)
    public void addLast(Item item) {
    	if (item == null) throw new IllegalArgumentException("item cannot be null.");
    	DoubleNode<Item> oldlast = last;
		last = new DoubleNode<Item>();
		last.item = item;
		last.next = null;
		last.previous = oldlast;
		if (isEmpty()) 
			first = last;
        else 
        	oldlast.next = last;
		numberOfItems++;
    }

    // remove and return the item from the front (as in pop() for stacks)
    public Item removeFirst() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	Item item = first.item;
    	first = first.next;
    	numberOfItems--;
    	return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	Item item = last.item;
    	last = last.previous;
    	numberOfItems--;
    	return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    	return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

		private DoubleNode<Item> current;
		
		public ListIterator(DoubleNode<Item> first) {
			current = first;
		}
		
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
		}
		
		public void remove() { 
			throw new UnsupportedOperationException();  
		}
		
	}
    // unit testing (required)
    public static void main(String[] args) {
    	Deque<String> dq = new Deque<String>();
    	String s;
    	
    	// addFirst()
    	while (!(s = StdIn.readString()).equals("-")) {
            dq.addFirst(s);
        }
    	
    	// addLast()
    	while (!(s = StdIn.readString()).equals("-")) {
    		dq.addLast(s);
    	}
    	
    	// isEmpty()
    	StdOut.printf("The deque is empty: %s\n", dq.isEmpty());
    	
    	// iterator()
    	for(String word: dq)
    		StdOut.printf("%s\n", word);
    	
    	// removeFirst()
    	StdOut.printf("First item: %s\n", dq.removeFirst());
    	
    	// removeLast()
    	StdOut.printf("Last item: %s\n", dq.removeLast());
    	
    	// size()
    	StdOut.printf("Size: %s\n", dq.size());
    	
    }

}