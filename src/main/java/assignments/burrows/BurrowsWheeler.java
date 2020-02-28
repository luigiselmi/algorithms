package assignments.burrows;

import java.util.Arrays;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

  //Do not instantiate.
  private BurrowsWheeler() {}

  // apply Burrows-Wheeler transform,
  // reading from standard input and writing to standard output 
  public static void transform() {
    Alphabet EX_ASCII = Alphabet.EXTENDED_ASCII;
    String s = BinaryStdIn.readString(); // plain text
    int n = s.length();
    CircularSuffixArray csa = new CircularSuffixArray(s);
    // use the index to get the last char of all
    // the suffixes
    String [] suffixes = createCircularSuffixes(s);
    int first = findFirst(csa, n);
    BinaryStdOut.write(first);
    for (int i = 0; i < n; i++) {
      int index = csa.index(i);
      char c = suffixes[index].charAt(n-1);
      BinaryStdOut.write(EX_ASCII.toIndex(c), 8);
    }
    BinaryStdOut.close();
  }
  
  // apply Burrows-Wheeler inverse transform,
  // reading from standard input and writing to standard output
  public static void inverseTransform() {
    Alphabet EX_ASCII = Alphabet.EXTENDED_ASCII;
    int first = BinaryStdIn.readInt(); // index of the original input string in the array of sorted suffixes
    String s = BinaryStdIn.readString(); // transformed string
    int N = s.length();
    char [] t = s.toCharArray();
    // the first column can be easily inferred from the input string
    char [] firstColumn = new char[N];
    for (int i = 0; i < N; i++)
      firstColumn[i] = t[i];
    Arrays.sort(firstColumn);
    
      
  }
  
  // finds the index of circular suffix that is equal 
  // to the input string
  private static int findFirst(CircularSuffixArray csa, int N) {
    for (int i = 0; i < N; i++)
      if ((csa.index(i)) == 0)
        return i;
    return -1;
  }
  // Returns the last character of all the ordered suffixes
  private static char [] getLastColumn(CircularSuffixArray csa, String [] suffixes) {
    int N = suffixes.length;
    char t[] = new char[N]; 
    for (int i = 0; i < N; i++) {
      int index = csa.index(i);
      char c = suffixes[index].charAt(N - 1);
      t[i] = c;
    }
    return t;
  }
  
  /*
   * Creates an array of N circular suffixes from the input string 
   * by shifting the characters 1 position to the left N times,
   * where N is the length of the input string.
   */
  private static String[] createCircularSuffixes(String s) {
    int N = s.length();
    String [] suffixes = new String[N];
    String suffix = s;
    for (int i = 0; i < N; i++) {
      suffixes[i] = suffix;
      suffix = shiftToLeft(suffix);
    }
    return suffixes;
  }
  
  private static String shiftToLeft(String s) {
    char first = s.charAt(0);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length() - 1; i++)
      sb.append(s.charAt(i + 1));
    sb.append(first);
    return sb.toString();
  }

  // if args[0] is "-", apply Burrows-Wheeler transform
  // if args[0] is "+", apply Burrows-Wheeler inverse transform
  public static void main(String[] args) {
    if      (args[0].equals("-")) transform();
    else if (args[0].equals("+")) inverseTransform();
    else throw new IllegalArgumentException("Illegal command line argument");
  }
}
