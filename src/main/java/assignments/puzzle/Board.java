package assignments.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {

  private int [][] tiles;
  private int n;
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
  //public int hamming() {}

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
    int [][] tiles = {{8,6,7},{2,5,4},{3,0,1}};
    Board b = new Board(tiles);
    StdOut.print(b.toString());
  }
}
