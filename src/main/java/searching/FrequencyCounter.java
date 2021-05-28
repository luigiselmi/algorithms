/******************************************************************************
 *  Compilation:  javac FrequencyCounter.java
 *  Execution:    java FrequencyCounter L < input.txt
 *  Dependencies: SequentialSearchST.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/31elementary/tinyTale.txt
 *                https://algs4.cs.princeton.edu/31elementary/tale.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
 *
 *  Read in a list of words from standard input and print out
 *  the most frequently occurring word that has length greater than
 *  a given threshold.
 *
 *  $ java -cp "lib/algs4.jar;target/classes" searching.FrequencyCounter 1 < resources/searching/tinyTale.txt
 *  it 10
 *
 *  $ java -cp "lib/algs4.jar;target/classes" searching.FrequencyCounter 8 < resources/searching/tale.txt
 *  business 122
 *
 *
 ******************************************************************************/

package searching;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code FrequencyCounter} class provides a client for 
 *  reading in a sequence of words and printing a word (exceeding
 *  a given length) that occurs most frequently. It is useful as
 *  a test client for various symbol table implementations.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class FrequencyCounter {

  // Do not instantiate.
  private FrequencyCounter() { }
  
  /**
   * Reads in a command-line integer and sequence of words from
   * standard input and prints out a word (whose length exceeds
   * the threshold) that occurs most frequently to standard output.
   * It also prints out the number of words whose length exceeds
   * the threshold and the number of distinct such words.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    
    int minWordLen = Integer.parseInt(args[0]);
    testBST(minWordLen);
    //testRedBlackBST(minWordLen);
  }
  private static void testBST(int minWordLen) {
    int distinct = 0, words = 0;
    BinarySearchTree<String, Integer> st = new BinarySearchTree<String, Integer>();
    // compute frequency counts
    System.out.println("Test Binary Search Tree");
    long start = System.currentTimeMillis();
    while (!StdIn.isEmpty()) {
      String key = StdIn.readString();
      if (key.length() < minWordLen) continue;
      words++;
      if (st.contains(key)) {
        st.put(key, st.get(key) + 1);
      }
      else {
        st.put(key, 1);
        distinct++;
      }
    }
    long stop = System.currentTimeMillis();
    double runningTime = (stop - start)/1000.0;
    StdOut.println("Total number of words = " + words);
    StdOut.println("Distinct words = " + distinct);
    System.out.println("Running time for insertion in symbol table (sec.): " + runningTime);
    
    // find a key with the highest frequency count
    String max = "";
    st.put(max, 0);
    start = System.currentTimeMillis();
    for (String word : st.keys()) {
      if (st.get(word) > st.get(max))
        max = word;
    }
    stop = System.currentTimeMillis();
    runningTime = (stop - start)/1000.0;
    StdOut.println("Most frequent word of length " + minWordLen + ": \"" + max + "\", found " + st.get(max) + " times.");
    System.out.println("Running time for most frequent word search (sec.): " + runningTime);
    
    
  }
  private static void testRedBlackBST(int minWordLen) {
    int distinct = 0, words = 0;
    BinarySearchTree<String, Integer> st = new BinarySearchTree<String, Integer>();
    System.out.println("Test Red-Black BST");
    // compute frequency counts
    long start = System.currentTimeMillis();
    while (!StdIn.isEmpty()) {
      String key = StdIn.readString();
      if (key.length() < minWordLen) continue;
      words++;
      if (st.contains(key)) {
        st.put(key, st.get(key) + 1);
      }
      else {
        st.put(key, 1);
        distinct++;
      }
    }
    long stop = System.currentTimeMillis();
    double runningTime = (stop - start)/1000.0;
    StdOut.println("Total number of words = " + words);
    StdOut.println("Distinct words = " + distinct);
    System.out.println("Running time for insertion in symbol table (sec.): " + runningTime);
    
    // find a key with the highest frequency count
    String max = "";
    st.put(max, 0);
    start = System.currentTimeMillis();
    for (String word : st.keys()) {
      if (st.get(word) > st.get(max))
        max = word;
    }
    stop = System.currentTimeMillis();
    runningTime = (stop - start)/1000.0;
    StdOut.println("Most frequent word of length " + minWordLen + ": \"" + max + "\", found " + st.get(max) + " times.");
    System.out.println("Running time for most frequent word search (sec.): " + runningTime);
    
  }
}

/******************************************************************************
 *  Copyright 2002-2019, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
