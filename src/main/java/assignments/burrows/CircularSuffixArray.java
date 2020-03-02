/**
 * This class creates a circular suffix array, a sorted array 
 * of the n circular suffixes of the input string.
 * Execution:
 * $ java -cp "lib/algs4.jar;target/classes" assignments.burrows.CircularSuffixArray resources/strings/abra.txt
 */
package assignments.burrows;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
  
  private int N; // number of character
  private int [] index; // original indexes of the sorted suffixes 
  private String [] sortedSuffixes; // suffix array
  private final int R = 256;   // size of the extended ASCII code
 
  // circular suffix array of the input string
  public CircularSuffixArray(String s) {
    if (s == null)
      throw new IllegalArgumentException("The suffix cannot be null.");
    N = s.length();
    // creates and initialize the index array
    index = new int [N];
    for (int i = 0; i < N; i++) 
      index[i] = i;
    sortAll(createCircularSuffixes(s));
  }
  
  //length of the input string
  public int length() {
    return N;
  }
  
   // returns index of ith sorted suffix
  public int index(int i) {
    if ( i < 0 || i > N )
      throw new IllegalArgumentException("The index i = " + i + " is outside the interval 0 <= i < " + N );
    return index[i];
  }

  /*
   *  Performs key-indexed counting of all the circular suffixes
   *  of the input string using as keys the characters in the dth 
   *  position in each suffix from d == 0 to d = N, length of input 
   *  string, so that the suffixes are sorted N times.
   */
  private void sortAll(String [] suffixes) {
    String [] temp = suffixes;
    for (int d = N - 1; d >= 0; d--) 
      temp = sortSuffixesByChar(temp, d);
  }
  
  /*
   * Sorts the array of all the circular suffixes of the input string 
   * by key-indexed counting, using as keys the characters at position dth 
   * of each suffix. The procedure consists of three steps:
   * 1) count the occurrences of each character at position dth of each suffix
   * 2) compute the index from the occurrences in order to sort the suffixes
   * 3) sort the suffixes
   */ 
  private String [] sortSuffixesByChar(String [] suffixes, int d) {
    return distributeByChar(suffixes, index(countByChar(suffixes, d)), d);
  }
  
  /*
   * Creates an array of N circular suffixes from the input string 
   * by shifting the characters 1 position to the left N times,
   * where N is the length of the input string.
   */
  private String[] createCircularSuffixes(String s) {
    String [] suffixes = new String[N];
    String suffix = s;
    for (int i = 0; i < N; i++) {
      suffixes[i] = suffix;
      suffix = shiftToLeft(suffix);
    }
    return suffixes;
  }
  
  private String shiftToLeft(String s) {
    char first = s.charAt(0);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N - 1; i++)
      sb.append(s.charAt(i + 1));
    sb.append(first);
    return sb.toString();
  }
  
  // Compute frequency counts, i.e. number of suffixes 
  // starting with the same character
  private int [] countByChar(String [] suffixes, int d) {
    int [] counts = new int[R + 1];
    for (int i = 0; i < suffixes.length; i++)
      counts[suffixes[i].charAt(d) + 1]++;
    return counts;
  }
  
  // Transform counts into indices, i.e. computes the starting value of the 
  // index for each character used as a key.
  private int [] index(int [] counts) {
    for (int r = 0; r < counts.length - 1; r++)
      counts[r + 1] += counts[r];
    return counts;
  }
   
  /*
   * Distribute the suffixes.
   */
  private String [] distributeByChar(String [] suffixes, int [] counts, int d) {
    String [] sorted = new String[N]; 
    int [] tempIndex = new int[N];
    for (int i = 0; i < N; i++) {
      int j = counts[suffixes[i].charAt(d)];
      tempIndex[j] = index[i];
      sorted[counts[suffixes[i].charAt(d)]++] = suffixes[i];
    }
    // copy the temporary index into the original one
    // to keep trace of the suffixes
    for (int k = 0; k < N; k++)
      index[k] = tempIndex[k];
    
    return sorted;
  }

  // unit testing (required)
  public static void main(String[] args) {
    In in = new In(args[0]);
    String s = in.readString();
    CircularSuffixArray csa = new CircularSuffixArray(s);
    
    StdOut.printf("Length of the suffix array: %d", csa.length());
    
    for (int i = 0; i < s.length(); i++)
      StdOut.printf("index[%d] = %d\n", i, csa.index(i));
  }
}
