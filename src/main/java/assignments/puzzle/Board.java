package assignments.puzzle;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {

  protected int [][] tiles; // n x n matrix
  private int n; // dimension
  private int [][] goal; // goal board
  
  /*
   * creates a node (board) from an n-by-n array of tiles,
   */
  public Board(int[][] tiles) {
    this.n = tiles.length;
    if (n < 2 || n > 128)
      throw new IllegalArgumentException("The dimension n of the puzzle must be between 2 and 128.");
    this.tiles = tiles;
    goal = createGoal(n);
  }
  
  /* 
   * initializes the goal board
   * e.g. for n = 3 -> {{1,2,3},{4,5,6},{7,8,0}}
   */
  protected int [][] createGoal(int n) {
    int [][] goal = new int [n][n];
    for (int i = 0; i < n ; i++)
      for (int j = 0; j < n ; j++)
        goal[i][j] = n * i + j + 1 ;
    goal[n-1][n-1] = 0;
    return goal;
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
      int tile = tiles[row][col];
      if (tile != goal[row][col])
        tilesOutOfPlace++;
    }
    return tilesOutOfPlace;
  }

  /*
   * The Manhattan distance of a board from the goal is 
   * defined as the sum of the Manhattan distances of 
   * each tile from the corresponding tile in the goal
   */
  public int manhattan() {
    int manhattanDistance = 0;
    for (int i = 1; i < n*n + 1 ; i++) {
      // compute row and column of each tile just to avoid a nested loop
      int row = (i - 1) / n ; // division module n
      int col = (n + i - 1) % n ; // remainder
      int tile = tiles[row][col];
      if (tile == 0) continue;
      // compute row and column of the tile in the goal board
      int grow = (tile - 1) / n ;
      int gcol = (tile - 1) % n ;
      int rowDist = Math.abs(grow - row);
      int colDist = Math.abs(gcol - col);
      int tileDist = rowDist + colDist;
      manhattanDistance += tileDist;
    }
    return manhattanDistance;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return this.equals(new Board(goal));
  }

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
      int tile = tiles[row][col];
      if (tile != boardy.tiles[row][col])
        return false;
    }
    return true;
  }

  /*
   *  All neighboring boards.
   *  The algorithm looks for row and column of the blank square and then
   *  swap it with the tiles up, down, left and right.
   */
  public Iterable<Board> neighbors() {
    Stack<Board> neighbors = new Stack<Board>(); // we'll put all the neighbors in a Stack
    int row0 = 0; // row of the blank square
    int col0 = 0; // column of the blank square
    
    // looks for the blank square
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) 
        if (tiles[i][j] == 0) {
          row0 = i;
          col0 = j;
          break;
        }
    
    // swap the blank square and its left neighbor
    if (col0 > 0) {
      // row and col of left tile
      int rowL = row0 ;
      int colL = col0 - 1 ;
      int [][] tilesL = copyTiles();
      int temp = tilesL[row0][col0];
      tilesL[row0][col0] = tilesL[rowL][colL];
      tilesL[rowL][colL] = temp;
      Board boardL = new Board(tilesL);
      neighbors.push(boardL);
    }
    
    // swap the blank square and its right neighbor    
    if (col0 < n - 1) {
      // row and col of right tile
      int rowR = row0 ;
      int colR = col0 + 1 ;
      int [][] tilesR = copyTiles();
      int temp = tilesR[row0][col0];
      tilesR[row0][col0] = tilesR[rowR][colR];
      tilesR[rowR][colR] = temp;
      Board boardR = new Board(tilesR);
      neighbors.push(boardR);
    }
    
    // swap the blank square and its upper neighbor
    if (row0 > 0) {
      // row and col of upper tile
      int rowU = row0 - 1 ;
      int colU = col0 ;
      int [][] tilesU = copyTiles();
      int temp = tilesU[row0][col0];
      tilesU[row0][col0] = tilesU[rowU][colU];
      tilesU[rowU][colU] = temp;
      Board boardU = new Board(tilesU);
      neighbors.push(boardU);
    }
    
    // swap the blank square and its lower neighbor
    if (row0 < n - 1) {
      // row and col of lower (down) tile
      int rowD = row0 + 1 ;
      int colD = col0 ;
      int [][] tilesD = copyTiles();
      int temp = tilesD[row0][col0];
      tilesD[row0][col0] = tilesD[rowD][colD];
      tilesD[rowD][colD] = temp;
      Board boardD = new Board(tilesD);
      neighbors.push(boardD);
    }
    
    return neighbors;
  }
  
  private int [][] copyTiles() {
    int [][] init = new int [n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        init[i][j] = tiles[i][j];
    return init;
  }

  /*
   *  A board obtained by exchanging any (one) pair of tiles. The 
   *  algorithm looks for row and column of the blank square, compute
   *  its one dimensional index, and then compute row and column of the 
   *  left and right tile and finally swap the two tiles. 
   */
  public Board twin() {
    int [][] twinTiles = new int [n][n];
    int row0 = 0; // row of the blank square
    int col0 = 0; // column of the blank square
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        int tile = tiles[i][j];
        if (tile == 0) {
          row0 = i;
          col0 = j;
        }
        twinTiles[i][j] = tile;
      }
    // one dimensional index of the blank square 
    int index0 = n * row0 + col0;
    int indexL; // index of the tile on the left of the blank square
    int indexR; // index of the tile on the right of the blank square
    if (index0 == 0) {
      indexL = index0 + 1;
      indexR = indexL + 1;
    }
    else if (index0 == n*n - 1) {
      indexR = index0 - 1;
      indexL = indexR - 1;
    }
    else {
      indexL = index0 - 1;
      indexR = index0 + 1;
    }
    // row and col tile left
    int rowL = indexL / n ;
    int colL = (n + indexL) % n ;
    // row and col tile right
    int rowR = indexR / n ;
    int colR = (n + indexR) % n ;
    // swap left and right tiles
    int temp = twinTiles[rowL][colL];
    twinTiles[rowL][colL] = twinTiles[rowR][colR];
    twinTiles[rowR][colR] = temp;
    return new Board(twinTiles);
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    int [][] tiles1 = {{8,1,3},{4,0,2},{7,6,5}};
    int [][] tiles2 = {{0,2,3},{1,5,6},{7,8,4}};
    int [][] tiles3 = {{0,2,3},{1,5,6},{7,8,4}}; // same as tiles2
    // test validity of input data
    //Board b0 = new Board(new int [129][129]);
    Board b1 = new Board(tiles1);
    Board b2 = new Board(tiles2);
    // print board
    StdOut.println(b1.toString());
    // compute Hamming distance
    assert b1.hamming() == 5 : "error in the hamming() method";
    assert b2.hamming() == 2 : "error in the hamming() method";
    StdOut.printf("Hamming distance of board " + b1.toString() + "from goal: %d\n", b1.hamming()); // 5
    StdOut.printf("Hamming distance of board " + b2.toString() + "from goal: %d\n", b2.hamming()); // 2
    // test for equality
    StdOut.printf("Board b1 is equal to b2: %s\n", b1.equals(b2)); // false
    Board b3 = new Board(tiles3); // same as b2
    assert b2.equals(b3) == true : "error in the equals() method";
    StdOut.printf("Board b2 is equal to b3: %s\n", b2.equals(b3)); // true
    int [][] tiles4 = {{0,2},{1,5}};
    Board b4 = new Board(tiles4);
    StdOut.printf("The board b2 is equal to b4: %s\n", b2.equals(b4)); // false
    // test if goal
    StdOut.printf("The board " + b1.toString() + "is a goal: %s\n", b1.isGoal()); // false
    Board goal = new Board(b1.createGoal(3));
    StdOut.printf("The board " + goal.toString() + "is a goal: %s\n", goal.isGoal()); // true
    // test Manhattan distance
    StdOut.printf("The Manhattan distance of board " + b1.toString() + "is %d\n", b1.manhattan());
    // test twin board
    Board twin = b1.twin();
    StdOut.printf("The twin board of " + b1.toString() + "is %s\n", twin.toString());
    // test neighbors
    int [][] tiles5 = {{1,0,3},{4,2,5},{7,8,6}};
    Board b5 = new Board(tiles5);
    for(Board b: b5.neighbors()) {
      StdOut.printf("Neighbor %s\n", b.toString());
    }
    // test beginning of puzzle
    int [][] tiles6 = {{0,1,3},{4,2,5},{7,8,6}};
    Board initNode = new Board(tiles6);
    StdOut.printf("The Manhattan distance of initial search node: " + initNode.toString() + "is %d\n", initNode.manhattan());
    for(Board b: initNode.neighbors()) {
      StdOut.printf("Neighbor of initial search node %s\n", b.toString());
      StdOut.printf("The Manhattan distance of neighbor " + b.toString() + "is %d\n", b.manhattan());
    }
  }
}
