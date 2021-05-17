/**
 * Implements a FIFO collection.
 * The order is preserved. It is based on a linked list.
 */
package fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

	private Node<Item> first;
	private Node<Item> last;
	private int numberOfItems;
	
	// Linked list class
  private class Node<Item> {
    private Item item;
    private Node<Item> next;
  }
    
	public Queue() {
		first = null;
		last = null;
		numberOfItems = 0;
	}
	
	public boolean isEmpty() {
		return numberOfItems == 0 ? true : false;
	}
	
	public int size() {
		return numberOfItems;
	}
	
	// adds the item to the end of the linked list
	public void enqueue(Item item) {
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
	
	// removes an item from the beginning of the linked list.
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		numberOfItems--;
		if (isEmpty()) last = null;
		return item;
	}
	
	// the iterator supports loops on the linked list.
	@Override
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
	
}
