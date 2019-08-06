package sorting;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class InsertionSort {
  
  public static void sort(Comparable[] a) { // Sort a[] into increasing order.
    int N = a.length;
    for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
      for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
        exch(a, j, j-1);
    }
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
    String[] a = StdIn.readAllStrings();
    sort(a);
    assert isSorted(a);
    show(a);
  }
	
}
