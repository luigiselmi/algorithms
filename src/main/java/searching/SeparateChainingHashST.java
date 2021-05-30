/**
 * Hash table implemented using an (unordered) symbol table 
 * based on a linked list. An hash function is used to compute
 * an integer value to be used as an index in an array of
 * lists of key-value pairs, one list for each index. This 
 * solution solves the problem of the collisions. When the 
 * same hash value is returned from two or more different 
 * keys they are added to the same list. If N is the number 
 * of keys and M is the size of the array, the number of 
 * compares for insert and search is N/M.  
 */
package searching;

public class SeparateChainingHashST<Key, Value> {
  
  private int N; // number of key-value pairs
  private int M; // size of the index array
  private SequentialSearchST<Key, Value>[] st;
  
  public SeparateChainingHashST() { 
    this(997); 
  }
  public SeparateChainingHashST(int M) { // Create M linked lists.
    this.M = M;
    st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
    for (int i = 0; i < M; i++)
      st[i] = new SequentialSearchST();
  }
  
  private int hash(Key key) { 
    return (key.hashCode() & 0x7fffffff) % M; 
  }
  
  public Value get(Key key) { 
    return (Value) st[hash(key)].get(key); 
  }
  
  public void put(Key key, Value val) { 
    st[hash(key)].put(key, val); 
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String [] keys = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
    SeparateChainingHashST<String, Integer> ht = new SeparateChainingHashST<String, Integer>();
    
    for (int i = 0; i < keys.length; i++) 
      ht.put(keys[i], i);
    
    System.out.println(ht.get("A"));
    
  }

}
