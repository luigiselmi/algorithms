package assignments.boggle;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver {
  
  private TST<Integer> symbolTable;
  private SET<String> matchedWords;
  boolean[] marked; // visited board's cubes
  
  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the upper case letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    symbolTable = new TST<Integer>();
    for (int i = 0; i < dictionary.length; i++)
      symbolTable.put(dictionary[i], dictionary[i].length());
    matchedWords = new SET<String>();
  }  

  /**
   * Returns the set of all valid words in the given Boggle board, as an Iterable.
   * Each letter on the board is a vertex. An edge is added from a vertex to all its
   * adjacent vertices but its parent vertex. The process starts from a source vertex on the 
   * board and adds edges from all the other vertices. At any step there are Then it is started again from
   * another vertices so that a word can start with any of the letter in the board avoiding
   * not adjacent letters, non sequential paths, and die used more than once.
   * Any path that starts with a letter in the board is a candidate word.
   * A candidate word is a valid word if it contains at least three letters and is in the dictionary. 
   * @param  a board (i.e. a matrix) of characters
   * @return all the valid words that can be built using the characters in the board.
   */
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    int rows = board.rows();
    int cols = board.cols();
    marked = new boolean[rows * cols];
    Bag<Integer>[] graph = computeAdjacents(rows, cols);
    StringBuilder word = new StringBuilder();
    for (int s = 0; s < rows*cols; s++)
      dfs(board, graph, s, word);
    return matchedWords;
  }
  
  private void dfs(BoggleBoard board, Bag<Integer>[] graph, int v, StringBuilder word) {
    marked[v] = true;
    int col = v % board.cols();
    int row = (v - col) / board.cols();
    word.append(board.getLetter(row, col));
    boolean validString = symbolTable.keysWithPrefix(word.toString()).iterator().hasNext();
    boolean matchString = symbolTable.keysThatMatch(word.toString()).iterator().hasNext();
    
    if (word.length() >= 3 && matchString) 
      matchedWords.add(word.toString());
    
    if (validString)
      for (int w : graph[v]) 
        if (!marked[w]) 
          dfs(board, graph, w, word);
    
    marked[v] = false;
    word.deleteCharAt(word.length() - 1);
  }
  
  /*
   * Computes the adjacent cubes of any cube in the board.
   * For each cube there can be from 3 up to 8 adjacent cubes:
   * UL, UM, UR
   * ML, ML,
   * BL, BM, BR
   * Where B stands for bottom, U for up
   * L for left, R for right and M for middle
   */
  private Bag<Integer>[] computeAdjacents(int rows, int cols) {
    int vertices = rows * cols;
    Bag<Integer>[] adjs = (Bag<Integer>[]) new Bag[vertices];
    for (int v = 0; v < vertices; v++) {
      adjs[v] = new Bag<Integer>();
      int col = v % cols;
      int row = (v - col)/cols;
      // UL adjacent vertex
      if ( row - 1 >= 0 && col - 1 >= 0 ) {
        int adjVertex = cols * (row - 1) + (col - 1);
        adjs[v].add(adjVertex);
      }
      // UM adjacent vertex
      if ( row - 1 >= 0 ) {
        int adjVertex = cols * (row - 1) + col;
        adjs[v].add(adjVertex);
      }
      // UR adjacent vertex
      if ( row - 1 >= 0 && col + 1 < cols ) {
        int adjVertex = cols * (row - 1) + (col + 1);
        adjs[v].add(adjVertex);
      }
      // ML adjacent vertex
      if ( col - 1 >= 0 ) {
        int adjVertex = cols * row + (col - 1);
        adjs[v].add(adjVertex);
      }
      // MR adjacent vertex
      if ( col + 1 < cols ) {
        int adjVertex = cols * row + (col + 1);
        adjs[v].add(adjVertex);
      }
      // BL adjacent vertex
      if ( row + 1 < rows && col - 1 >= 0 ) {
        int adjVertex = cols * (row + 1) + (col - 1);
        adjs[v].add(adjVertex);
      }
      // BM adjacent vertex
      if ( row + 1 < rows ) {
        int adjVertex = cols * (row + 1) + col;
        adjs[v].add(adjVertex);
      }
      // BR adjacent vertex
      if ( row + 1 < rows && col + 1 < cols ) {
        int adjVertex = cols * (row + 1) + (col + 1);
        adjs[v].add(adjVertex);
      }
    }
    
   return adjs;
  }
  
  //index mapping from 2D array to 1D array, e.g. [a][b] -> [c]
  private int i1d(int cols, int row, int col) {
    return cols * row + col;
  }
  //index mapping from 1D array to 2D array, row, e.g. [c] -> [a][]
  private int i2dRow(int cols, int i1, int col) {
    return (i1 - col)/cols;
  }
  //index mapping from 1D array to 2D array, column, e.g. [c] -> [][b]
  private int i2dCol(int cols, int i1) {
    return i1%cols;
  }

  /**
   * Returns the score of the given word if it is in the dictionary, zero otherwise.
   * (You can assume the word contains only the uppercase letters A through Z.)
   * @param word
   * @return
   */
  public int scoreOf(String word) {
    int length = word.length();
    int score = 0;
    if (symbolTable.contains(word)) {
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
    int score = 0;
    for (String word : solver.getAllValidWords(board)) {
        StdOut.println(word);
        score += solver.scoreOf(word);
    }
    StdOut.println("Score = " + score);
    
  }

}
