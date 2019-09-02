package sorting;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
/**
 * Sorts an array using the quicksort algorithm.
 * @author lselmi
 *
 */
public class Quick {

  public static void sort(Comparable[] a) {
    StdRandom.shuffle(a); // Eliminate dependence on input.
    sort(a, 0, a.length - 1);
  }
  
  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int j = partition(a, lo, hi); // Partition (see page 291).
    sort(a, lo, j-1); // Sort left part a[lo .. j-1].
    sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
  }
  
  private static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
    int i = lo, j = hi+1; // left and right scan indices
    Comparable v = a[lo]; // partitioning item
    while (true) { // Scan right, scan left, check for scan complete, and exchange.
      while (less(a[++i], v)) if (i == hi) break;
      while (less(v, a[--j])) if (j == lo) break;
      if (i >= j) break;
      exch(a, i, j);
    }
    exch(a, lo, j); // Put v = a[j] into position
    return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
  }
  
  //is v < w ?
  private static boolean less(Comparable v, Comparable w) {
      if (v == w) return false;   // optimization when reference equals
      return v.compareTo(w) < 0;
  }
  
  //exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
      Object swap = a[i];
      a[i] = a[j];
      a[j] = swap;
  }
  
  private static void show(Comparable[] a) { // Print the array, on a single line.
    for (int i = 0; i < a.length; i++)
      StdOut.print(a[i] + " ");
      StdOut.println();
    }
  
  public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i-1])) return false;
    return true;
  }
  
  public static void main(String[] args) {
    String example = "Q U I C K S O R T E X A M P L E";
    String [] a = example.split(" ");
    sort(a);
    assert isSorted(a);
    show(a);

  }

}
