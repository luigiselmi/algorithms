package assignments.boggle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver {
  
  TST<Integer> symbolTable;
  
  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    symbolTable = new TST<Integer>();
    for (int i = 0; i < dictionary.length; i++)
      symbolTable.put(dictionary[i], dictionary[i].length());
  }  

  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    return symbolTable.keys();
  }

  // Returns the score of the given word if it is in the dictionary, zero otherwise.
  // (You can assume the word contains only the uppercase letters A through Z.)
  public int scoreOf(String word) {
    int length = word.length();
    int score = 0;
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
