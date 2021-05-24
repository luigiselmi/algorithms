/**
 * A simple (unordered) symbol table implemented on
 * top of a linked list. 
 */
package searching;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import fundamentals.Bag;

public class SequentialSearchST<Key, Value> {

  private Node first;
  private int numItems;
 
  private class Node {
    private Key key;
    private Value val;
    private Node next;
    public Node(Key key, Value val, Node next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }
  // returns the value associated to the input key
  public Value get(Key key) { // Search for key, return associated value.
    for (Node x = first; x != null; x = x.next)
      if (key.equals(x.key))
        return x.val; // search hit
    return null; // search miss
  }
  // Adds a key-value pair. Keys must be unique. 
  public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
    for (Node x = first; x != null; x = x.next)
      if (key.equals(x.key))
        { x.val = val; return; } // Search hit: update val.
    first = new Node(key, val, first); // Search miss: add new node
    numItems++;  
  }
  
  public boolean contains(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to contains() is null");
    return get(key) != null;
  }
  /*
   * returns a bag with all the unique keys
   * used in the symbol table.
   */
  public Iterable<Key> keys() {
    Bag<Key> keys = new Bag<Key>();
    for (Node x = first; x != null; x = x.next)
      keys.add(x.key);
    return keys;
  }
  
  public int size() {
    return numItems;
  }
  
  public static void main(String[] args) {
    SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
   
    for (int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      st.put(key, i);
    }
    
    StdOut.println("Number of items: " + st.size());
    
    for (String s : st.keys())
      StdOut.println(s + " " + st.get(s));

  }
  
}
