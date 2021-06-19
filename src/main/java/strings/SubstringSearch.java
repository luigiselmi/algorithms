/**
 * This class provides two implementation of a substring
 * search using a brute-force approach.
 * 
 * $ java -cp "lib/algs4.jar;target/classes" strings.SubstringSearch ABR resources/strings/substring_search.txt
 */
package strings;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import fundamentals.Queue;

public class SubstringSearch {

  /*
   * Brute-force algorithm for substring search. Given
   * a text string of length N and a pattern string of length M
   * the algorithm looks for occurrences of the pattern in the 
   * text. It returns the position of all occurrences in the text.
   * The cost is proportional to NM.
   */
  public static Queue<Integer> search(String pat, String txt) {
    int M = pat.length();
    int N = txt.length();
    Queue<Integer> index = new Queue<Integer>();
    for (int i = 0; i <= N - M; i++) {
      for (int j = 0; j < M; j++) {
        if (txt.charAt(i+j) != pat.charAt(j))
          break;
        if (j == M - 1)  // pattern found
          index.enqueue(i);
      }
    }
    return index; // pattern not found
  }
  
  /*
   * Alternate implementation of the brute-force algorithm.
   * The cost is the same as the other algorithm: NM.
   * From Sedgewick's Book p.761
   */
  public static Queue<Integer> altsearch(String pat, String txt) {
    int j, M = pat.length();
    int i, N = txt.length();
    Queue<Integer> index = new Queue<Integer>();
    for (i = 0, j = 0; i < N; i++) {
      if (txt.charAt(i) == pat.charAt(j)) {
        j++;
        if (j == M) { // found
          index.enqueue(i - M + 1); 
          j = 0;
        }
      }
      else { i -= j; j = 0; }
    }
    return index;
  }
  
  public static void main(String[] args) {
      String pat = args[0];
      In is = new In(args[1]);
      String txt = is.readAll().replaceAll("\\s+", " ");
      Queue<Integer> index = SubstringSearch.search(pat, txt);
      if (! index.isEmpty()) {
        StdOut.printf("Pattern \"%s\" found %d times in \"%s\" by brute-force search.\n", pat, index.size(), args[1]);
        for (int i: index)
          StdOut.printf("Pattern \"%s\" found at position %d\n", pat, i);
      }
      else
        StdOut.printf("Pattern \"%s\" not found in \"%s\"", pat, args[1]);
    }

}
