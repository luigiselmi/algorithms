package assignments.boggle;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;
import fundamentals.ThreeSum;

public class BoggleSolver {
  
  private final TST<Integer> dictionary;
  private boolean[] marked; // visited board's cubes
  
  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the upper case letters A through Z.)
  public BoggleSolver(String[] dictionary) {
 
    this.dictionary = new TST<Integer>();
    for (int i = 0; i < dictionary.length; i++)
      this.dictionary.put(dictionary[i], dictionary[i].length());
    
  }  

  /**
   * Returns the set of all valid words in the given Boggle board, as an Iterable.
   * A candidate word is a valid word if it contains at least three adjacent letters 
   * and is in the dictionary. 
   * @param  a board (i.e. a matrix) of characters
   * @return all the valid words that can be built using the characters in the board.
   */
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    SET<String> matchedWords = new SET<String>();
    int rows = board.rows();
    int cols = board.cols();
    int cubes = rows * cols;
    marked = new boolean[cubes];
    Bag<Integer>[] graph = computeAdjacents(rows, cols);
    StringBuilder word = new StringBuilder();
    for (int s = 0; s < cubes; s++)
      dfs(board, graph, s, word, matchedWords);
    return matchedWords;
  }
  
  private void dfs(BoggleBoard board, Bag<Integer>[] graph, int v, StringBuilder word, SET<String> matchedWords) {
    marked[v] = true;
    int col = v % board.cols();
    int row = (v - col) / board.cols();
    char letter = board.getLetter(row, col);
    word.append(letter);
    
    boolean validString = dictionary.keysWithPrefix(word.toString()).iterator().hasNext();
    if (validString) {
      if (letter == 'Q')
        word.append('U');    
      
      if ( word.length() >= 3 ) {
        boolean matchString = dictionary.keysThatMatch(word.toString()).iterator().hasNext();
        if ( matchString )
          matchedWords.add(word.toString());
      }
      
      for (int w : graph[v]) 
        if (!marked[w]) 
          dfs(board, graph, w, word, matchedWords);  
    }
    
    marked[v] = false;
    if (word.length() >= 2 && word.charAt(word.length() - 2) == 'Q')
      word.deleteCharAt(word.length() - 1); // remove last 'U' after a 'Q'
    word.deleteCharAt(word.length() - 1);
     
  }
  
  /*
   * Computes the adjacent cubes of any cube in the board.
   * Each cube is represented as an integer from 0 to rows*columns
   * and all its adjacent cubes are added into its adjacency list.
   * For each cube there can be from 3 up to 8 adjacent cubes:
   * UL, UM, UR
   * ML, ML,
   * BL, BM, BR
   * Where B stands for bottom, U for up
   * L for left, R for right and M for middle
   */
  private Bag<Integer>[] computeAdjacents(int rows, int cols) {
    int cubes = rows * cols;
    Bag<Integer>[] adjs = new Bag[cubes];
    for (int v = 0; v < cubes; v++) {
      adjs[v] = new Bag<Integer>();
      int col = v % cols;
      int row = ( v - col ) / cols;
      // UL adjacent cube
      if ( row - 1 >= 0 && col - 1 >= 0 ) {
        int adjCube = cols * ( row - 1 ) + ( col - 1 );
        adjs[v].add(adjCube);
      }
      // UM adjacent cube
      if ( row - 1 >= 0 ) {
        int adjCube = cols * ( row - 1 ) + col;
        adjs[v].add(adjCube);
      }
      // UR adjacent cube
      if ( row - 1 >= 0 && col + 1 < cols ) {
        int adjCube = cols * ( row - 1 ) + ( col + 1 );
        adjs[v].add(adjCube);
      }
      // ML adjacent cube
      if ( col - 1 >= 0 ) {
        int adjCube = cols * row + ( col - 1 );
        adjs[v].add(adjCube);
      }
      // MR adjacent cube
      if ( col + 1 < cols ) {
        int adjCube = cols * row + ( col + 1 );
        adjs[v].add(adjCube);
      }
      // BL adjacent cube
      if ( row + 1 < rows && col - 1 >= 0 ) {
        int adjCube = cols * ( row + 1 ) + ( col - 1 );
        adjs[v].add(adjCube);
      }
      // BM adjacent cube
      if ( row + 1 < rows ) {
        int adjCube = cols * ( row + 1 ) + col;
        adjs[v].add(adjCube);
      }
      // BR adjacent cube
      if ( row + 1 < rows && col + 1 < cols ) {
        int adjCube = cols * ( row + 1 ) + ( col + 1 );
        adjs[v].add(adjCube);
      }
    }
    
   return adjs;
  }
  
  /**
   * Returns the score of the given word if it is in the dictionary, zero otherwise.
   * (You can assume the word contains only the upper case letters A through Z.)
   * @param word
   * @return
   */
  public int scoreOf(String word) {
    int length = word.length();
    int score = 0;
    if (dictionary.contains(word)) {
      if (length == 3 || length == 4)
        score = 1;
      else if (length == 5)
        score = 2;
      else if (length == 6)
        score = 3;
      else if (length == 7)
        score = 5;
      else if (length >= 8)
        score = 11;
    }
    return score;
  }
  
  public static void main(String[] args) {
    
    In in = new In(args[0]);
    String[] dictionary = in.readAllStrings();
    BoggleSolver solver = new BoggleSolver(dictionary);
    BoggleBoard board = new BoggleBoard(args[1]);
    int totalScore = 0;
    int counter = 0;
    long start = System.currentTimeMillis();
    for (String word : solver.getAllValidWords(board)) {
      counter++;
      int score = solver.scoreOf(word);
      totalScore += score;
      StdOut.printf("%s, score = %d\n", word, score);
    }
    long stop = System.currentTimeMillis();
    double timing = (stop - start) / 1000.0;
    StdOut.printf("Number of words found = %d, Score = %d, Timing = %f (seconds)",counter, totalScore, timing);
    
  }

}
