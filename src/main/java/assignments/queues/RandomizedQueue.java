package assignments.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private Node<Item> first;
	private Node<Item> last;
	private int numberOfItems;
	
	// Linked list class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    
	// construct an empty randomized queue
    public RandomizedQueue() {
    	first = null;
		last = null;
		numberOfItems = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return numberOfItems == 0 ? true : false;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return numberOfItems;
    }

    // add the item
    public void enqueue(Item item) {
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

    // remove and return a random item
    public Item dequeue() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	Item item = first.item;
		first = first.next;
		numberOfItems--;
		if (isEmpty()) last = null;
		return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	Item randomItem = null;
    	int randomIndex = StdRandom.uniform(1, numberOfItems + 1);
    	Iterator<Item> i = this.iterator();
    	int counter = 1;
    	while (i.hasNext()) {
    		Item item = i.next();
    		if (counter == randomIndex) {
    			randomItem = item;
    			break;
    		}
    		counter++;
    	}
    	return randomItem;
    }

    // return an independent iterator over items in random order
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
    	RandomizedQueue<String> rq = new RandomizedQueue<String>();
    	String s;
    	while (!(s = StdIn.readString()).equals("-")) {
            rq.enqueue(s);
        }
    	
    	String randomItem1 = rq.sample();
    	System.out.println("Random item 1: " + randomItem1);
    	String randomItem2 = rq.sample();
    	System.out.println("Random item 2: " + randomItem2);
    	
    	while ( !rq.isEmpty() ) {
    		System.out.println(rq.dequeue());
    	}
    }

}