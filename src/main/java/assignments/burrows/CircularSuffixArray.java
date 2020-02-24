package assignments.burrows;

import edu.princeton.cs.algs4.In;

public class CircularSuffixArray {
  
  private int N; // number of character
  private int [] index; // original indexes of the sorted suffixes 
  private CircularSuffix [] sortedSuffixes; // suffix array

 
  private class CircularSuffix {
    private String suffix;
    private char firstChar;
    CircularSuffix(String s) {
      suffix = s;
      firstChar = s.charAt(0);
    }
  }
 
  
  //circular suffix array of s
  public CircularSuffixArray(String s) {
    if (s == null)
      throw new IllegalArgumentException("The suffix cannot be null.");
    N = s.length();
    index = new int [N];
    sortedSuffixes = sortSuffixes(s);
  }
  
  //Use MSD 
  private CircularSuffix [] sortSuffixes(String s) {
    return createSuffixes(s);
  }
  
  private CircularSuffix[] createSuffixes(String s) {
    CircularSuffix [] suffixes = new CircularSuffix[N];
    String suffix = s;
    for (int i = 0; i < N; i++) {
      suffixes[i] = new CircularSuffix(suffix);
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

  // unit testing (required)
  public static void main(String[] args) {
    In in = new In(args[0]);
    String s = in.readString();
    CircularSuffixArray suffix = new CircularSuffixArray(s);
  }
}
