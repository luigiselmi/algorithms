package assignments.boggle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver {
  
  private TST<Integer> symbolTable;
  
  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    symbolTable = new TST<Integer>();
    for (int i = 0; i < dictionary.length; i++)
      symbolTable.put(dictionary[i], dictionary[i].length());
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
    //char[][] boardData = board.getLetter(i, j)
    return symbolTable.keys();
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
    /*
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
    */
    // index mapping from 2D array to 1D array, e.g. [a][b] -> [c]
    int cols = 10;
    int rows = 10;
    for (int r = 0; r < rows; r++){
       for (int c = 0; c < cols; c++)
         StdOut.printf("%d ", cols * r + c);
      StdOut.println();
    }
    // index mapping from 1D array to 2D array
    int v = 38;
    int col = v%cols;
    int row = (v - col)/cols;
    StdOut.printf("col = %d, row = %d", col, row);
  }

}
