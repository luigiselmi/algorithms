package assignments.percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int closedSites;
	private WeightedQuickUnionUF uf;
	private int n; 
	private int [][] id;
	

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if (n <= 0)
            throw new IllegalArgumentException("Grid dimensions " + n + " must be a positive number.");
    	this.n = n;
    	closedSites = n;
    	id = new int [n][n];
    	uf = new WeightedQuickUnionUF(n*n);
    }

    // Opens the site (row, col) if it is not open already.
    public void open(int row, int col) {
    	validate(row, col);
    	if( ! isOpen(row, col) ) {
    		id[row][col] = 1;
    		closedSites--;
    		//connect the site to all open neighbors (left, right, up, down)
    		int p = m2a(row, col);
    		if (row - 1 > 0 && isOpen(row - 1, col)) { 
    			int q = m2a(row - 1, col); //up neighbor
    			uf.union(p, q);
    		}
    		if (row + 1 < n && isOpen(row + 1, col)) {
    			int q = m2a(row + 1, col); // down neighbor
    			uf.union(p, q);
    		}
    		if (col - 1 > 0 && isOpen(row, col - 1)) {
    			int q = m2a(row, col - 1); // left neighbor
    			uf.union(p, q);
    		}
    		if (col + 1 < n && isOpen(row, col + 1)) {
    			int q = m2a(row, col + 1); // right neighbor
    			uf.union(p, q);
    		}
    	}
    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	validate(row, col);
    	return id[row][col] == 1;
    }

    /* 
     * Is the site (row, col) full?
     * A full site is an open site that can be connected 
     * to an open site in the top row via a chain of 
     * neighboring (left, right, up, down) open sites.
     */
    public boolean isFull(int row, int col) {
    	boolean isFull = false;
    	validate(row, col);
    	int p = m2a(row, col);
    	for (int i = 0; i < n; i++) {
    		int q = m2a(0,i);
    		if ( uf.connected(p, q) )
    			isFull = true;
    	}
    	return isFull;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    
    	return n*n - closedSites;
    }

    /* 
     * Does the system percolate ?
     * The system percolates if there is a full site 
     * in the bottom row.
     */
    public boolean percolates() {
    	boolean percolates = false;
    	for (int i = 0; i < n; i++) {
    		if ( isFull(n,i) )
    			percolates = true;
    	}
    	return percolates;
    }
    
    // map a matrix element (row, col) to a 1D array index
    private int m2a(int row, int col) {
    	return n + row + col + 1;
    }

    // validate row and column indexes
    private void validate(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("row index " + row + " is not between 1 and " + n);
        }
        if (col < 1 || col > n) {
            throw new IllegalArgumentException("col index " + col + " is not between 1 and " + n);
        }
    }
    
    /*
     * 1) Initialize all sites to be blocked.
     * 2) Repeat the following until the system percolates:
     *       Choose a site uniformly at random among all blocked sites.
     *       Open the site.
     * 3) The fraction of sites that are opened when the system percolates 
     *    provides an estimate of the percolation threshold.
     */
    public static void main(String[] args) {
    	int n = StdIn.readInt(); // n-by-n grid
    	int T = StdIn.readInt(); // number of experiments
    	Percolation model = new Percolation(5);
    	int tests = 1;
    	while (! model.percolates() && tests <= T) {
    		 for (int i = 1; i <= n*n; i++) {
    			 // open randomly the sites
    		 }
    			 
            
    	}
    	
    }
}