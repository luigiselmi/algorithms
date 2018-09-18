package chapter1.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implements a LIFO collection.
 * It is based on a linked list.
 */
public class Stack<Item> implements Iterable<Item> {

	private Node<Item> first;     // top of stack
	private int numberOfItems;
	
	public Stack() {
		first = null;
		numberOfItems = 0;
	}
	
	// Linked list class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    
    public void push(Item item) {
    	Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        numberOfItems++;
    }
    
    public Item pop() {
    	if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    	Item item = first.item;
    	first = first.next;
    	numberOfItems--;
    	return item;
    }
    
    public boolean isEmpty() {
		return numberOfItems == 0 ? true : false;
	}
    
    public int size() {
		return numberOfItems;
	}
    
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
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
