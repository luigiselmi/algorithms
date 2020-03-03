/**
 * This class creates a circular suffix array, a sorted array 
 * of the N circular suffixes of an input string of length N. 
 * The suffixes are sorted using the key-indexed counting method, 
 * a stable sorting method that ensures that the relative order 
 * of the suffixes is maintained. The N suffixes are lexically 
 * ordered N times using as key the characters at position dth
 * from right to left.
 * Execution:
 * $ java -cp "lib/algs4.jar;target/classes" assignments.burrows.CircularSuffixArray resources/assignments/burrows/abra.txt
 */
package assignments.burrows;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
  
  private int N; // number of characters in the input string, same as number of suffixes.
  private int [] index; // original indexes of the sorted suffixes.    
  private Alphabet alphabet  = Alphabet.EXTENDED_ASCII;
  private int R; // size of the extended ASCII code.
  private int [] charOccurrences; // indices computed from the number of occurrences of each character in the input string.
 
  // circular suffix array of the input string
  public CircularSuffixArray(String s) {
    if (s == null)
      throw new IllegalArgumentException("The suffix cannot be null.");
    for (int i = 0; i < s.length(); i++)
      if (!alphabet.contains(s.charAt(i)))
        throw new IllegalArgumentException("The input string contains non valid characters.");
    
    R = alphabet.radix();
    N = s.length();
    // computes the number of occurrences of character in the input string
    charOccurrences = countsToIndices(countCharOccurrences(s));
    // creates and initialize the index array
    index = new int [N];
    for (int i = 0; i < N; i++) 
      index[i] = i;
    sortSuffixes(s);
  }
  
  // length of the input string
  public int length() {
    return N;
  }
  
   // returns index of ith sorted suffix
  public int index(int i) {
    if (i < 0 || i > N)
      throw new IllegalArgumentException("The index i = " + i + " is outside the interval 0 <= i < " + N );
    return index[i];
  }

  /*
   *  Performs key-indexed counting of all the circular suffixes
   *  of the input string using as keys the characters in the dth 
   *  position in each suffix from d == 0 to d = N, length of input 
   *  string, so that the suffixes are sorted N times. The suffixes
   *  are computed dynamically from the input string and the index
   *  since the index represents the number of times the input string
   *  has been shifted one step to the left. 
   */
  private void sortSuffixes(String s) {
    for (int d = N - 1; d >= 0; d--) { 
      
      // counts and indices
      int [] counts = new int[charOccurrences.length];
      for (int i = 0; i < charOccurrences.length; i++)
        counts[i] = charOccurrences[i];
      
      // distribute
      int [] tempIndex = new int[N];
      for (int i = 0; i < N; i++) {
        String suffix = s.substring(index[i]) + s.substring(0, index[i]);
        int j = counts[alphabet.toIndex(suffix.charAt(d))]++;
        tempIndex[j] = index[i];
      }
      // copy the temporary index into the original one
      // to keep trace of the suffixes
      for (int k = 0; k < N; k++) 
        index[k] = tempIndex[k];
    
    }
  }
  
  /*
   *  Computes the number of occurrences of a character in the input string.
   *  The characters are among those available in the alphabet. The number of
   *  occurrences is used to build the index for the sorting of the suffixes.
   *  Since the suffixes are built from the input string, they contain the 
   *  same set of characters at any position and the frequency is always the same 
   *  so it needs to be computed only once from the input string. 
   */
  private int [] countCharOccurrences(String s) {
    int [] counts = new int[R + 1];
    for (int i = 0; i < s.length(); i++) { 
      int charIndex = alphabet.toIndex(s.charAt(i)); 
      counts[charIndex + 1]++;
    }
    return counts;
  }
  
  /*
   *  Transforms counts into indices, i.e. computes the starting value of the
   *  index for each character used as a key. 
   */
  private int [] countsToIndices(int [] counts) {
    for (int r = 0; r < counts.length - 1; r++)
      counts[r + 1] += counts[r];
    return counts;
  }

  // unit testing (required)
  public static void main(String[] args) {
    In in = new In(args[0]);
    String s = in.readString();
    CircularSuffixArray csa = new CircularSuffixArray(s);
    
    StdOut.printf("Length of the suffix array: %d\n", csa.length());
    
    for (int i = 0; i < s.length(); i++)
      StdOut.printf("index[%d] = %d\n", i, csa.index(i));
  }
}
