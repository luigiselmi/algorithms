package assignments.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
  private Board puzzle = null;
  private boolean isSolvable = false;
  private int movesToGoal = 0; 
  MinPQ<Node> pq; //priority queue
  Queue<Board> solution = null;
  
  //find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null)
      throw new IllegalArgumentException("The initial board cannot be null.");
    puzzle = initial;
    pq = new MinPQ<Node>();
    solution = new Queue<Board>();
    solvePuzzle(puzzle);
  }

  // is the initial board solvable? 
  public boolean isSolvable() {
    Board swapPuzzle = puzzle.twin();
    return isSolvable;
  }

  // min number of moves to solve initial board
  public int moves() {
    return movesToGoal;
  }

  // sequence of boards in a shortest solution
  public Iterable<Board> solution() {
    return solution;
  }
  
  private class Node implements Comparable<Node> {
    private Board board;
    private int moves;
    private Node previous;
    @Override
    public int compareTo(Node object) {
      int objectDistance = object.board.manhattan();
      int objectPriority = objectDistance + object.moves;
      int priority = board.manhattan() + moves;
      if (priority == objectPriority) return 0;
      else if (priority < objectPriority) return -1;
      else return 1;
    }
  }
  
  private void solvePuzzle(Board initB) {
    // creates initial search node
    Node initN = new Node();
    initN.board = initB;
    initN.moves = 0;
    initN.previous = null;
    boolean isGoal = false;
    // adds the init search node to the priority queue
    pq.insert(initN);
    // solve the puzzle starting from the init board
    Board previousB = null;
    while (!isGoal) {
      Node minN = pq.delMin();
      Board minB = minN.board;
      solution.enqueue(minB);
      Node previousN = minN.previous;
      if (previousN != null )
        previousB = previousN.board;
      if (minB.isGoal()) {
        movesToGoal = minN.moves;
        isSolvable = true;
        isGoal = true;
      }
      else {
        for(Board b: minB.neighbors()) {
          if(! b.equals(previousB)) {// critical optimization
            Node neighbor = new Node();
            neighbor.moves = minN.moves + 1 ;
            neighbor.previous = minN;
            neighbor.board = b;
            pq.insert(neighbor);
          }
        }
      }
    }
    
  }

  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);
    // solve the puzzle
    Solver solver = new Solver(initial);
    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
    
  }
}
