package assignments.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;


public class Deque<Item> implements Iterable<Item> {
	
	private Node<Item> first;
	private Node<Item> last;
	private int numberOfItems;
	
	// Linked list class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
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
    	Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        numberOfItems++;
    }

    // add the item to the back (as in enqueue() for queues)
    public void addLast(Item item) {
    	if (item == null) throw new IllegalArgumentException("item cannot be null.");
    	Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
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

    // remove and return the item from the back (as in dequeue() for queues)
    public Item removeLast() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    	return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
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
    	while (!(s = StdIn.readString()).equals("-")) {
            dq.addFirst(s);
        }
    	
    	while ( !dq.isEmpty() ) {
    		System.out.println(dq.removeFirst());
    	}
    }

}