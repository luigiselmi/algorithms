package assignments.puzzle;

import edu.princeton.cs.algs4.StdOut;

public class Board {

  private int [][] tiles;
  private int n; // dimension, e.g. n = 3 in a 3x3 matrix
  private int [][] goal = {{1,2,3},{4,5,6},{7,8,0}};
  
  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    this.tiles = tiles;
    this.n = tiles.length;
  }
                                         
  // string representation of this board
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(n + "\n");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++)
        str.append(tiles[i][j] + " ");
      str.append("\n");
    }
    return str.toString();
  }

  // board dimension n
  public int dimension() {
    return n;
  }

  // number of tiles out of place
  public int hamming() {
    int tilesOutOfPlace = 0;
    for (int i = 0; i < n*n - 1; i++) {
      int row = i / n ;
      int col = (n + i) % n ;
      int tileValue = tiles[row][col];
      if (tileValue != goal[row][col])
        tilesOutOfPlace++;
    }
    return tilesOutOfPlace;
  }

  // sum of Manhattan distances between tiles and goal
  //public int manhattan() {}

  // is this board the goal board?
  //public boolean isGoal() {}

  // does this board equal y?
  //public boolean equals(Object y) {}

  // all neighboring boards
  //public Iterable<Board> neighbors() {}

  // a board that is obtained by exchanging any pair of tiles
  //public Board twin() {}

  // unit testing (not graded)
  public static void main(String[] args) {
    int [][] tiles1 = {{8,1,3},{4,0,2},{7,6,5}};
    int [][] tiles2 = {{0,2,3},{1,5,6},{7,8,4}};
    Board b1 = new Board(tiles1);
    Board b2 = new Board(tiles2);
    // print board
    StdOut.println(b1.toString());
    // compute hamming distance
    StdOut.println(b1.hamming());
    StdOut.println(b2.hamming());
  }
}
