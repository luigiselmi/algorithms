/**
 * This class provides two methods to find the
 * longest repeated substring. One method uses
 * the brute force approach that has a complexity
 * of (N^2)/2 where N is the length of the input 
 * string. The 2nd method uses the sorted array 
 * of the input string suffixes. The longest
 * repeated substring can be found by comparing 
 * the prefixes of two adjacent suffixes in the 
 * sorted array. 
 * 
 * $ java -cp "lib/algs4.jar;target/classes" strings.SuffixArray resources/searching/tinyTale.txt
 */
package strings;

import java.util.Arrays;
import edu.princeton.cs.algs4.In;

public class SuffixArray {
  
  private int N;
  private final String [] suffixes;

  public SuffixArray(String txt) {
    N = txt.length();
    suffixes = new String[N];
    for (int i = 0; i < N; i++)
      suffixes[i] = txt.substring(i);
    Arrays.sort(suffixes); // we can use other sorting algorithms
  }

  // returns the suffix in the sorted array 
  // at the specified position
  public String select(int i) {
    return suffixes[i];
  }
  
  // returns the index in the original text 
  // where the suffix at the given position 
  // (rank) i begins 
  public int index(int i) { 
    return N - suffixes[i].length(); 
  }
  
  // Returns the rank (position) of the key
  // in the sorted suffix array (binary search).
  // Any search for suffixes that begin with 
  // the key are found from here. 
  public int rank(String key) { 
    int lo = 0, hi = N - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = key.compareTo(suffixes[mid]);
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    return lo;
  }
  
  // finds the longest repeated substring in
  // the sorted suffixes array of the input string
  public String lrs() {
    String longest = "";
    for (int i = 0; i < N - 1; i++) {
      int lenght = lcp(suffixes[i], suffixes[i + 1]);
      if (lenght > longest.length())
        longest = suffixes[i].substring(0, lenght);
    }
    return longest;
  }
  
  // finds the longest repeated substring using 
  // a brute force approach
  public String bruteForceLRS(String txt) {
    int N = txt.length();
    String longest = "";
    for (int i = 0; i < N; i++) {
      String sub1 = txt.substring(i);
      for (int j = i + 1; j < N; j++) {
        String sub2 = txt.substring(j);
        int lenght = lcp(sub1, sub2);
        if (lenght > longest.length())
          longest = txt.substring(i, i + lenght);
      }
    }
    return longest;    
  }
  
  // finds the longest common prefix of two strings
  private static int lcp(String s, String t) {
    int N = Math.min(s.length(), t.length());
    for (int i = 0; i < N; i++)
      if (s.charAt(i) != t.charAt(i)) return i;
    return N;
  }
  
  public static void main(String[] args) {
    In is = new In(args[0]); // reads a text from input stream
    String txt = is.readAll().replaceAll("\\s+", " ");
    SuffixArray sa = new SuffixArray(txt);
    String bflrs = sa.bruteForceLRS(txt); // try the brute force approach for comparison
    System.out.println("Longest repeated string (brute force): " + bflrs);
    String lrs = sa.lrs();
    System.out.println("Longest repeated string (suffix array): " + lrs);
  }

}
