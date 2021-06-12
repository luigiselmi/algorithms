/**
 * Least-significant-digit-first algorithm. It sorts stably 
 * fixed-length strings. The algorithm is based on key-indexed 
 * counting.
 * 
 * $ java -cp "lib/algs4.jar;target/classes" strings.LSD resources/strings/lsd_example.txt
 */
package strings;

import edu.princeton.cs.algs4.In;

public class LSD {
  
  private static final int R = 256; // size of the characters set (extended 8 bits ASCII)
  
  private LSD() { } //do not instantiate
  
  //sort strings by key-indexed counting
  public static String [] sort(String [] s) {
    int N = s.length; // number of strings
    int W = s[0].length(); // fixed-length strings
    String [] kisort = new String[N];
    
    // Sort by key-indexed counting on dth char.
    for (int d = W-1; d >= 0; d--) { 
      int[] count = new int[R+1]; 
      for (int i = 0; i < N; i++)
        count[s[i].charAt(d) + 1]++; // computes frequency counts.
      for (int r = 0; r < R; r++) 
        count[r+1] += count[r]; // transforms counts to indices.
      for (int i = 0; i < N; i++) // Distribute.
        kisort[count[s[i].charAt(d)]++] = s[i];
      for (int i = 0; i < N; i++) // Copy back.
        s[i] = kisort[i];
    }
    
    return s;
  }
  
  public static void main(String[] args) {
    In is = new In(args[0]);
    String [] s = is.readAllLines();
    
    String [] sorted = LSD.sort(s);
    
    for (int i = 0; i < sorted.length; i++)
      System.out.println(sorted[i]);

  }

}
