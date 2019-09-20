package assignments.puzzle;

import edu.princeton.cs.algs4.StdOut;

public class Board {

  protected int [][] tiles;
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

  /*
   *  Hamming distance: number of tiles out of place.
   *  Instead of using two nested loops here I use
   *  some modular arithmetics to compute row and column indexes
   *  It's not really needed but I was curious :)
   */
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

  /*
   * Two boards are equal if they have the same size 
   * and their corresponding tiles are in the same positions.
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object y) {
    if (this == y)
      return true;
    if (y == null || getClass() != y.getClass()) 
      return false;
    Board boardy = (Board)y;
    // check size
    if (boardy.dimension() != this.dimension())
        return false;
    // check tiles
    for (int i = 0; i < n*n - 1; i++) {
      int row = i / n ;
      int col = (n + i) % n ;
      int tileValue = tiles[row][col];
      if (tileValue != boardy.tiles[row][col])
        return false;
    }
    return true;
  }

  // all neighboring boards
  //public Iterable<Board> neighbors() {}

  // a board that is obtained by exchanging any pair of tiles
  //public Board twin() {}

  // unit testing (not graded)
  public static void main(String[] args) {
    int [][] tiles1 = {{8,1,3},{4,0,2},{7,6,5}};
    int [][] tiles2 = {{0,2,3},{1,5,6},{7,8,4}};
    int [][] tiles3 = {{0,2,3},{1,5,6},{7,8,4}}; // same as tiles2
    Board b1 = new Board(tiles1);
    Board b2 = new Board(tiles2);
    // print board
    StdOut.println(b1.toString());
    // compute hamming distance
    StdOut.println(b1.hamming());
    StdOut.println(b2.hamming());
    // test for equality
    StdOut.println(b1.equals(b2));
    Board b3 = new Board(tiles3); // same as b2
    StdOut.println(b2.equals(b3));
  }
}
