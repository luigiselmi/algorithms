package sorting;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * A priority queue based on a heap-ordered array.
 * @author lselmi
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {
  
  private Key[] pq; // heap-ordered complete binary tree
  private int N = 0;
  
  public MaxPQ(int m) {
    pq = (Key[]) new Comparable[m + 1];
  }
  
  /**
   * Initializes an empty priority queue.
   */
  public MaxPQ() {
      this(1);
  }
  
  /**
   * We add the new key at the end of the array, increment the size of the heap,
   * and then swim up through the heap with that key to restore the heap condition
   */
  public void insert(Key v) {
    pq[++N] = v;
    swim(N);
  }
  
  /**
   * We take the largest key off the top, put the item from
   * the end of the heap at the top, decrement the size of the heap, and then sink down
   * through the heap with that key to restore the heap condition
   */
  public Key delMax() {
    Key max = pq[1]; // Retrieve max key from top.
    exch(1, N--); // Exchange with last item.
    pq[N+1] = null; // Avoid loitering.
    sink(1); // Restore heap property.
    return max;
  }
  
  public Key max() {
    return pq[1];
  }
  
  public boolean isEmpty() {
    return N == 0;
  }
  
  public int size() {
    return N;
  }
  
  /**
   * Restore the heap order when a child's key's node
   * is bigger that its parent's key.
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
   * is smaller than the one's of its children
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
    return pq[i].compareTo(pq[j]) < 0; 
  }
  
  private void exch(int i, int j) { 
    Key t = pq[i]; pq[i] = pq[j]; pq[j] = t; 
  }
  
  public static void main(String[] args) {
    int N = StdIn.readInt();
    int maxOperations = StdIn.readInt(); // insertion or deletion
    int numOperations = 0;
    MaxPQ<String> pq = new MaxPQ<String>(N);
    while (!StdIn.isEmpty() && numOperations < maxOperations) {
      numOperations++;
      String item = StdIn.readString();
      if (!item.equals("-")) pq.insert(item); // insert
      else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " "); // remove max
    }
    StdOut.println("(" + pq.size() + " left on pq)");
  }
}
