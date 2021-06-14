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
    Arrays.sort(suffixes);
  }
  
  public String select(int i) {
    return suffixes[i];
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
  // the brute force approach
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
    System.out.println(bflrs);
    String lrs = sa.lrs();
    System.out.println(lrs);
  }

}
