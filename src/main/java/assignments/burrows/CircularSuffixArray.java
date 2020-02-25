package assignments.burrows;

import edu.princeton.cs.algs4.In;

public class CircularSuffixArray {
  
  private int N; // number of character
  private int [] index; // original indexes of the sorted suffixes 
  private String [] sortedSuffixes; // suffix array
  private final int R = 256;   // size of the extended ASCII code
 
  /*
  private class CircularSuffix {
    private String suffix;
    private char firstChar;
    CircularSuffix(String s) {
      suffix = s;
      firstChar = s.charAt(0);
    }
  }
  */
 
  //circular suffix array of the input string
  public CircularSuffixArray(String s) {
    if (s == null)
      throw new IllegalArgumentException("The suffix cannot be null.");
    N = s.length();
    index = new int [N];
    sortedSuffixes = sortAll(createCircularSuffixes(s));
  }
  
  private String [] sortAll(String [] suffixes) {
    String [] temp = suffixes;
    for (int d = N - 1; d >= 0; d--) 
      temp = sortSuffixesByChar(temp, d);
    return temp;
  }
  
  private String [] sortSuffixesByChar(String [] suffixes, int d) {
    return distributeByChar(suffixes, index(countByChar(suffixes, d)), d);
  }
  
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

  // length of s
  public int length() {
    return N;
  }

  // returns index of ith sorted suffix
  public int index(int i) {
    if ( i < 0 || i > N - 1)
      throw new IllegalArgumentException("The index i must be within the interval 0 <= i < " + N );
    return 0;
  }

  // Compute frequency counts, i.e. number of suffixes 
  // starting with the same character
  private int [] countByChar(String [] suffixes, int d) {
    int [] counts = new int[R + 1];
    for (int i = 0; i < suffixes.length; i++)
      counts[suffixes[i].charAt(d) + 1]++;
    return counts;
  }
  
  // Transform counts to indices, i.e. computes the starting value of the 
  // index for each character used as a key.
  private int [] index(int [] counts) {
    for (int r = 0; r < counts.length - 1; r++)
      counts[r+1] += counts[r];
    return counts;
  }
   
  //Distribute the records
  private String [] distributeByChar(String [] suffixes, int [] counts, int d) {
    int N = suffixes.length;
    String [] sorted = new String[N]; 
    for (int i = 0; i < N; i++)
      sorted[counts[suffixes[i].charAt(d)]++] = suffixes[i];
    return sorted;
  }

  // unit testing (required)
  public static void main(String[] args) {
    In in = new In(args[0]);
    String s = in.readString();
    CircularSuffixArray suffix = new CircularSuffixArray(s);
  }
}
