package assignments.percolation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int blockedSites;
	private final WeightedQuickUnionUF uf;
	private final int n;
	private boolean[][] openSites;


  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0)
      throw new IllegalArgumentException("Grid dimensions " + n + " must be a positive number.");
    this.n = n;
    blockedSites = n*n;
    openSites = new boolean [n][n];
    uf = new WeightedQuickUnionUF(n*n);
  }

  /** Opens the site (row, col) if it is not open already.
   * open() method should do three things: 
   * 1) it should validate the indices of the site that it receives 
   * 2) it should somehow mark the site as open
   * 3) it should perform some sequence of WeightedQuickUnionUF operations 
   *    that links the site in question to its open neighbors
   * @param row
   * @param col
   */
  
  public void open(int row, int col) {
    validate(row, col);
    if (!isOpen(row, col)) {
      openSites[row - 1][col - 1] = true; // set the site to open
    	blockedSites--;
    	// connect the site to all open neighbors (left, right, up, down)
    	int p = xyTo1D(row, col);
    	if (row - 1 > 0 && isOpen(row - 1, col)) { 
    		int q = xyTo1D(row - 1, col); // up neighbor
    		uf.union(p, q);
    }
    if (row + 1 <= n && isOpen(row + 1, col)) {
      int q = xyTo1D(row + 1, col); // down neighbor
    	uf.union(p, q);
    }
    if (col - 1 > 0 && isOpen(row, col - 1)) {
      int q = xyTo1D(row, col - 1); // left neighbor
    	uf.union(p, q);
    }
    if (col + 1 <= n && isOpen(row, col + 1)) {
      int q = xyTo1D(row, col + 1); // right neighbor
    	uf.union(p, q);
    }
   }
  }

  // Is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validate(row, col);
    return openSites[row - 1][col - 1];
  }

  /**
   * A full site is an open site that can be connected 
   * to an open site in the top row via a chain of  
   * neighboring (left, right, up, down) open sites.
   */
  public boolean isFull(int row, int col) {
    validate(row, col);
    boolean isFull = false;
    if (isOpen(row, col)) {
      int p = xyTo1D(row, col);
      int rootP = uf.find(p);
      for (int topCol = 1; topCol <= n; topCol++) {
        if (isOpen(1, topCol)) {
          int q = xyTo1D(1, topCol);
          int rootQ = uf.find(q);
          if (rootP ==  rootQ)
            return true;
        }
      }
    }
  	return isFull;
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
  	return n*n - blockedSites;
  }

  /* 
   * Does the system percolate ?
   * The system percolates if there is a full site 
   * in the bottom row.
   */
  public boolean percolates() {
    boolean percolates = false;
    for (int i = 0; i < n; i++) {
      if (isFull(n,i + 1))
        return true;
    }
    return percolates;
  }
  
  // map a matrix element (row, col) to a 1D array index, e.g. (1.1) -> 0
  private int xyTo1D(int row, int col) {
    return n * (row - 1) + col - 1;
  }

  // validate row and column indexes
  private void validate(int row, int col) {
    if (row < 1 || row > n)
      throw new IllegalArgumentException("row index " + row + " is not between 1 and " + n);

    if (col < 1 || col > n)
      throw new IllegalArgumentException("col index " + col + " is not between 1 and " + n);
  }
  
  public static void main(String[] args) {
  	In in = new In(args[0]);
  	int n = in.readInt();
  	Percolation s = new Percolation(n);
  	s.open(1, 11);
  	boolean test = s.isOpen(1, 11) == s.isFull(1, 11);
  	StdOut.printf("%d, %s\n", 11, test);
  }
}