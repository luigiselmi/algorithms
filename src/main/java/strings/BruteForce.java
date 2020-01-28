package strings;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BruteForce {

  /*
   * From Sedgewick's Book p.760
   */
  public static int search(String pat, String txt) {
    int M = pat.length();
    int N = txt.length();
    for (int i = 0; i <= N - M; i++) {
      int j;
      for (j = 0; j < M; j++)
        if (txt.charAt(i+j) != pat.charAt(j))
          break;
      if (j == M) return i; // found
    }
    return N; // not found
  }
  
  /*
   * From Sedgewick's Book p.761
   */
  public static int search2(String pat, String txt) {
    int j, M = pat.length();
    int i, N = txt.length();
    for (i = 0, j = 0; i < N && j < M; i++) {
      if (txt.charAt(i) == pat.charAt(j)) j++;
      else { i -= j; j = 0; }
    }
    if (j == M) return i - M; // found
    else return N; // not found
  }
  
  /* Replaces string subout with string subin in txt.
   * One issue of this function is that it cannot find the 
   * pattern when it is repeated without any letter in the 
   * middle, for example in the string "toratoratora" it cannot find 
   * and replace the last occurrence of the pattern "tora". (to be fixed)
   */
  public static String replace(String txt, 
                                 String subout,
                                 String subin,
                                 int start) {
    String rightstring = txt;
    int indexofapice = start;
    int lastindexapice = rightstring.lastIndexOf(subout);
    String strLeft;
    String strRight;
    indexofapice = rightstring.indexOf(subout,start);
    if(indexofapice != -1) {
      strLeft = rightstring.substring(0,indexofapice);
      strRight = rightstring.substring(indexofapice + subout.length(),rightstring.length());
      rightstring = strLeft + subin + strRight;
      if(indexofapice + subin.length() < lastindexapice) 
        rightstring = replace(rightstring,subout,subin,indexofapice + subin.length());
    }
    return rightstring;
  }
  
  public static void main(String[] args) {
    for (int i = 0; !StdIn.isEmpty(); i++) {
      String pat = StdIn.readString();
      String txt = StdIn.readString();
      StringBuffer sb = new StringBuffer(); // replacement for the pattern
      for (int k = 0; k < pat.length(); k++)
        sb.append("-");
      int index = BruteForce.search2(pat, txt);
      boolean patternFound = index < txt.length(); // pattern found in text at least once
      if (patternFound) {
        StdOut.printf("Pattern \"%s\" found in \"%s\" at %d \n", pat, txt, index);
        String modifiedStr = BruteForce.replace(txt, pat, sb.toString(), 0);
        StdOut.printf("Removed pattern \"%s\" from text \"%s\": \"%s\" \n", pat, txt, modifiedStr);
      }
      else
        StdOut.printf("Pattern \"%s\" not found in \"%s\"", pat, txt);
    }
  }

}
