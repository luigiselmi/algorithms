/**
 * This class implements the heapsort algorithm to order a collection
 * of comparables (i.e. numbers, chars, strings or other items that 
 * implement a Comparable interface. It uses the same approach as a 
 * priority queue. The algorithm is divided in two phases. In the first 
 * phase it orders the input array as a heap, in the second phase it 
 * sorts it down by moving the maximum to the last position repeatedly.
 * 
 * $ java -cp "target/classes" sorting.Heap SORTEXAMPLE
 * 
 */
package sorting;

public class Heap {
  
  //This class should not be instantiated.
  private Heap() { }
  
  public static void sort(Comparable[] a) {
    int n = a.length;
    // heap orders the input array
    for (int k = n/2; k >= 1; k--)
      sink(a, k, n);
    
    // sorts the input array by moving the maximum
    // from position 1
    int k = n;
    while (k > 1) {
      exch(a, 1, k--);
      sink(a, 1, k);
    }
  }
  
  /*
   * Used to navigate top-down the binary tree
   * to keep it heap-ordered. If the index of a node is k
   * the indexes of its children nodes are 2k and 2k + 1.
   * If the node's key is less than any of its two children
   * the key must be exchanged with the larger one.
   */
  private static void sink(Comparable[] a, int k, int n) {
    while (2*k <= n) {
      int j = 2*k; // left child node index
      if (j < n && less(a, j, j+1)) j++; // if left child smaller than use the right child
      if (!less(a, k, j)) break; // exit if the parent is not smaller than the child
      exch(a, k, j); // exchange the keys between parent and larger child
      k = j;
    }
  }

  /* Used to check if a parent key is less than
  * its child key. In that case it must be exchanged
  * to keep the binary tree heap-ordered. It is also used to
  * check whether the left child is smaller than the right child.
  */
  private static boolean less(Comparable[] a, int i, int j) { 
    boolean comparison = a[i - 1].compareTo(a[j - 1]) < 0; 
    return comparison;
  }
  
  /* Used to exchange a node's key with its parent's
  * key when it's larger, to keep the binary tree heap-ordered.
  */
  private static void exch(Comparable[] a, int i, int j) { 
    Comparable t = a[i - 1]; // parent's key
    a[i -1] = a[j - 1]; 
    a[j - 1] = t; 
  }
  public static void main(String[] args) {
    Comparable [] unsorted = new Comparable[args[0].length()];
    for (int i = 0; i < unsorted.length; i++)
      unsorted[i] = args[0].charAt(i);
    
    Heap.sort(unsorted);
    
    for (int i = 0; i < unsorted.length; i++) 
      System.out.print(unsorted[i]);

  }

}
