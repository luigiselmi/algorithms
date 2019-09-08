package sorting;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;


public class IndexMaxPQ<Key extends Comparable<Key>> {
  private int N; // number of elements on PQ
  private int[] pq; // binary heap using 1-based indexing
  private int[] qp; // inverse: qp[pq[i]] = pq[qp[i]] = i
  private Key[] keys; // items with priorities
  
  public IndexMaxPQ(int maxN) {
    keys = (Key[]) new Comparable[maxN + 1];
    pq = new int[maxN + 1];
    qp = new int[maxN + 1];
    for (int i = 0; i <= maxN; i++) qp[i] = -1;
  }

  public boolean isEmpty() { return N == 0; }
  
  public int size() {return N;} // number of items in the priority queue
  
  public void change(int k, Key key) {} //change the item associated with k to item

  // returns true if k is an index on this priority queue
  public boolean contains(int k) { return qp[k] != -1; }

  // insert key and associate it with index k
  public void insert(int i, Key key) {
    if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
    N++;
    qp[i] = N;
    pq[N] = i;
    keys[i] = key;
    swim(N);
  }

  //remove the index and its associated key
  public void delete(int i) {
    if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
    int index = qp[i];
    exch(index, N--);
    swim(index);
    sink(index);
    keys[i] = null;
    qp[i] = -1;
  } 
  
  // Returns the maximum key.
  public Key max() { 
    if (N == 0) throw new NoSuchElementException("Priority queue underflow");
    return keys[pq[1]]; 
  }
  
  // Returns the index associated with a maximum key
  public int maxIndex() {
    if (N == 0) throw new NoSuchElementException("Priority queue underflow");
    return pq[1];
  }

  public int delMax() {
    int indexOfMax = pq[1];
    exch(1, N--);
    sink(1);
    keys[pq[N+1]] = null;
    qp[pq[N+1]] = -1;
    return indexOfMax;
  }
  
  /**
   * Restore the heap order when a child's key's node
   * is bigger that its parent's key. In a heap, the parent of the
   * node in position k is in position k/2.
   * @param k
   */
  private void swim(int k) {
    while (k > 1 && less(k/2, k)) {
      exch(k/2, k);
      k = k/2;
    }
  }
  /**
   * Restore heap order when a node's key
   * is smaller than the one's of its children.
   * The two children of the node in position
   * k are in positions 2k and 2k + 1.
   * @param k
   */
  private void sink(int k) {
    while (2*k <= N)  {
      int j = 2*k;
      if (j < N && less(j, j+1)) j++;
      if (!less(k, j)) break;
      exch(k, j);
      k = j;
    }
  }
   
  private boolean less(int i, int j) { 
    return keys[pq[i]].compareTo(keys[pq[j]]) < 0; 
  }
  
  private void exch(int i, int j) { 
    int swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }
  
  public static void main(String[] args) {
    // inserts a bunch of strings
    String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

    IndexMaxPQ<String> pq = new IndexMaxPQ<String>(strings.length);
    for (int i = 0; i < strings.length; i++) {
        pq.insert(i, strings[i]);
    }
    
   // deletes and prints each key in alphabetical order with its index
    while (!pq.isEmpty()) {
        String key = pq.max();
        int i = pq.delMax();
        StdOut.println(i + " " + key);
    }
  }
}