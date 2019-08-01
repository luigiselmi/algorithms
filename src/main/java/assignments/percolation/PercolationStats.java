package assignments.percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private Percolation model;
	private int n;
	private double [] thresholds;
	
	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		this.n = n;
		if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Grid dimensions " + n + " must be a positive number and at least one trial " + trials + " should be performed");
    	
    	model = new Percolation(n);
    	thresholds = new double[trials];
    	executeTrials();
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return mean() - 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return mean() + 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    private void executeTrials() {
    	for (int t = 0; t < thresholds.length; t++) {
	    	while (! model.percolates()) {
	    	    // choose randomly a site among the blocked ones
	    		int row = StdRandom.uniform(1, n + 1);
	    		int col = StdRandom.uniform(1, n + 1);
	    		if ( ! model.isOpen(row, col) )
	    			model.open(row, col); // open the site
	    	}
    	    // when it percolates computes the threshold
	    	thresholds[t] = (double) model.numberOfOpenSites() / (n*n) ;
    	}
    }
    // test client (see below)
    public static void main(String[] args) {
    	int n = StdIn.readInt(); // n-by-n grid
    	int trials = StdIn.readInt(); // number of experiments
    	PercolationStats stats = new PercolationStats(n, trials);
    	System.out.println("mean = " + stats.mean());
    	System.out.println("stddev = " + stats.stddev());
    	System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
    
}
