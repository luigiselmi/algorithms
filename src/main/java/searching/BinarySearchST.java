/**
 * A symbol table in which the key are unique and kept in order. 
 * It is based on two parallel arrays, one for the keys and one 
 * for the values. The binary search algorithm is used to look 
 * for each input key that has to be compared with the keys 
 * already in the symbol table.  
 */
package searching;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import fundamentals.Bag;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

  private Key[] keys;
  private Value[] vals;
  private int N;
  
  public BinarySearchST(int capacity) { 
    keys = (Key[]) new Comparable[capacity];
    vals = (Value[]) new Object[capacity];
  }
  
  public int size() { 
    return N; 
  }
  
  public boolean isEmpty() {
    return N == 0;
  }
  
  public Value get(Key key) {
    if (isEmpty()) return null;
    int i = rank(key);
    if (i < N && keys[i].compareTo(key) == 0) return vals[i];
    else return null;
  }
  
  // Looks for the rank (i.e. order) of a key.
  public int rank(Key key) {
    int lo = 0, hi = N-1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = key.compareTo(keys[mid]); // cmp < 0 if key < keys[mid], cmp = 0 if key equal keys[mid] cmp > 0 otherwise.
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    return lo;
  }
  
  // Saves a key-value pair if the key is not already in the symbol table otherwise
  // updates it. The new key is put in the keys array following its natural order.
  public void put(Key key, Value val) { 
    int i = rank(key);
    if (i < N && keys[i].compareTo(key) == 0) { // update key's value
      vals[i] = val; return; 
    }
    for (int j = N; j > i; j--) { 
      keys[j] = keys[j-1]; vals[j] = vals[j-1]; // copies keys and values to the right
    }
    keys[i] = key; vals[i] = val; // saves the key and its associated value
    N++;
  }
  
  /*
   * returns a bag with all the unique keys
   * used in the symbol table.
   */
  public Iterable<Key> keys() {
    Bag<Key> stkeys = new Bag<Key>();
    int lo = 0, hi = N - 1;
    for (int i = lo; i < hi; i++)
      stkeys.add(keys[i]);;
    return stkeys;
  }
  
  public static void main(String[] args) {
    BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(30);
    for (int i = 0; !StdIn.isEmpty(); i++) {
        String key = StdIn.readString();
        st.put(key, i);
    }
    for (String s : st.keys())
        StdOut.println(s + " " + st.get(s));
	}

}
