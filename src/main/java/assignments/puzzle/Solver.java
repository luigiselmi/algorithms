package assignments.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
  private Board puzzle = null;
  private boolean isSolvable = false;
  private int movesToGoal = 0;
  private Queue<Board> solution = null;
  
  //find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null)
      throw new IllegalArgumentException("The initial board cannot be null.");
    puzzle = initial;
    solution = new Queue<Board>();
    solvePuzzle(puzzle);
  }

  // is the initial board solvable? 
  public boolean isSolvable() {
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
  
  private class SearchNode implements Comparable<SearchNode> {
    private Board board;
    private int moves;
    private SearchNode previous;
    private int distance;
    public SearchNode(int distance) {
      this.distance = distance;
    }
    @Override
    public int compareTo(SearchNode object) {
      int objectPriority = object.distance + object.moves;
      int priority = distance + moves;
      if (priority == objectPriority) return 0;
      else if (priority < objectPriority) return -1;
      else return 1;
    }
  }
  /*
   * The algorithm creates two priority queues. One for the puzzle
   * given by the user, and one for its twin. The 2nd queue allows us 
   * to check whether the puzzle is solvable or not. 
   */
  private void solvePuzzle(Board puzzle) {
    MinPQ<SearchNode> pq = new MinPQ<SearchNode>(); //priority queue for the puzzle
    MinPQ<SearchNode> tpq = new MinPQ<SearchNode>(); //priority queue for the twin puzzle
    // creates the initial search node for the puzzle
    SearchNode initN = new SearchNode(puzzle.manhattan());
    initN.board = puzzle;
    initN.moves = 0;
    initN.previous = null;
    // creates the initial search node for the twin puzzle
    Board twin = puzzle.twin();
    SearchNode initTN = new SearchNode(twin.manhattan());
    initTN.board = twin;
    initTN.moves = 0;
    initTN.previous = null;
    
    boolean isPuzzleGoal = false;
    boolean isTwinGoal = false;
    // adds the init search node to the priority queue
    pq.insert(initN);
    // adds the init search node to the twin priority queue
    tpq.insert(initTN);
    
    // solve the puzzle starting from the init board
    Board previousB = null;
    Board previousTB = null;
    while (!(isPuzzleGoal || isTwinGoal)) {
      // search the puzzle priority queue
      SearchNode minN = pq.delMin();
      Board minB = minN.board;
      solution.enqueue(minB);
      SearchNode previousN = minN.previous;
      if (previousN != null )
        previousB = previousN.board;
      if (minB.isGoal()) {
        movesToGoal = minN.moves;
        isSolvable = true;
        isPuzzleGoal = true;
        pq = null;
      }
      else {
        for(Board b: minB.neighbors()) {
          if(! b.equals(previousB)) {// critical optimization
            SearchNode neighbor = new SearchNode(b.manhattan());
            neighbor.moves = minN.moves + 1 ;
            neighbor.previous = minN;
            neighbor.board = b;
            pq.insert(neighbor);
          }
        }
      }
      // search the twin priority queue
      if (! isPuzzleGoal ) {
        SearchNode minTN = tpq.delMin();
        Board minTB = minTN.board;
        SearchNode previousTN = minTN.previous;
        if (previousTN != null )
          previousTB = previousTN.board;
        if (minTB.isGoal()) {
          movesToGoal = -1;
          isTwinGoal = true;
          tpq = null;
        }
        else {
          for(Board b: minTB.neighbors()) {
            if(! b.equals(previousTB)) {// critical optimization
              SearchNode neighbor = new SearchNode(b.manhattan());
              neighbor.moves = minTN.moves + 1 ;
              neighbor.previous = minTN;
              neighbor.board = b;
              tpq.insert(neighbor);
            }
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
