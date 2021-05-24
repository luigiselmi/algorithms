package fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an unordered collection. Objects cannot be removed.
 * it is based on a linked list.
 */
public class Bag<Item> implements Iterable<Item> {
	
	private Node<Item> first;
	private int numberOfItems;
	
	public Bag() {
		first = null;
		numberOfItems = 0;
	}
	
	// Linked list class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
	
    // Adds the item at the beginning of the linked list
	public void add(Item item ) {
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldFirst;
		numberOfItems++;
	}
	
	public boolean isEmpty() {
		return numberOfItems == 0 ? true : false;
	}
	
	public int size() {
		return numberOfItems;
	}
	
	// the iterator supports loops on the linked list.
	@Override
	public Iterator<Item> iterator() {
		
		return new ListIterator(first);
	}
	
	private class ListIterator implements Iterator<Item> {

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
	
	public static void main(String[] args) {
	  Bag<String> mybag = new Bag<String>();
	  mybag.add("Rome");
	  mybag.add("Paris");
	  mybag.add("New York");
	  System.out.println(mybag.isEmpty());
	  for (String city: mybag)
	    System.out.println(city);
	}

}
