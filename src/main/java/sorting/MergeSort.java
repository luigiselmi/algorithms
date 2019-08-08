package sorting;

import edu.princeton.cs.algs4.StdOut;

public class MergeSort {
  
  private static Comparable[] aux; // auxiliary array for merges
  
  //This class should not be instantiated.
  private MergeSort() { }
  
  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length]; // Allocate space just once.
    sort(a, 0, a.length - 1);
  }
  
  private static void sort(Comparable[] a, int lo, int hi) { // Sort a[lo..hi].
    if (hi <= lo) return;
    int mid = lo + (hi - lo)/2;
    sort(a, lo, mid); // Sort left half.
    sort(a, mid+1, hi); // Sort right half.
    merge(a, lo, mid, hi); // Merge results (code on page 271).
  }
  
  private static void merge(Comparable[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
      aux[k] = a[k];
    for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
      if (i > mid) a[k] = aux[j++];
      else if (j > hi ) a[k] = aux[i++];
      else if (less(aux[j], aux[i])) a[k] = aux[j++];
      else a[k] = aux[i++];
  }

  private static boolean less(Comparable v, Comparable w) { 
    return v.compareTo(w) < 0; // v < w
  }
  
  private static void exch(Comparable[] a, int i, int j) { 
    Comparable t = a[i]; 
    a[i] = a[j]; 
    a[j] = t; 
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
  
  public static void main(String[] args) { // Read strings from standard input, sort them, and print.
    //String[] a = StdIn.readAllStrings(); // doesn't work within the eclipse debugger
    String example = "M E R G E S O R T E X A M P L E";
    String [] a = example.split(" ");
    sort(a);
    assert isSorted(a);
    show(a);
  }
}
