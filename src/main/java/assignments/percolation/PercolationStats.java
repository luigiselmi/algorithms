package assignments.percolation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final int n;
	private double [] thresholds;
	private final int trials;
	private static final double CONFIDENCE_95 = 1.96;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
	  if (n <= 0 || trials <= 0)
	    throw new IllegalArgumentException("Grid dimensions " + n + " must be a positive number and at least one trial " + trials + " should be performed");
	  
	  this.n = n;
	  this.trials = trials;
	  thresholds = new double[trials];
	  executeSetOfTrials(trials);
  }

  // sample mean of percolation threshold
  public double mean() {
  	return StdStats.mean(thresholds);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    if (trials == 1)
      return Double.NaN;
    else
      return StdStats.stddev(thresholds);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
  	return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trials);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
  	return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trials);
  }
  
  /**
   * 1) Choose randomly a site among the blocked ones
   * 2) Open the site
   * @param t
   */
  private void executeTrial(int t) {
    Percolation system = new Percolation(n);
    while (!system.percolates()) {
      int row = StdRandom.uniform(1, n + 1);
      int col = StdRandom.uniform(1, n + 1);
      if (!system.isOpen(row, col))
        system.open(row, col); // open the site
    }
    thresholds[t] = (double) system.numberOfOpenSites() / (n*n) ; // when it percolates computes the threshold
  }

  private void executeSetOfTrials(int trials) {
  	for (int t = 0; t < trials; t++) 
  	  executeTrial(t);
  }
  
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int numberOfTrials = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, numberOfTrials);
  	StdOut.printf("%s %20s %1f\n", "mean", "=", stats.mean());
  	StdOut.printf("%s %18s %1f\n", "stddev", "=", stats.stddev());
  	StdOut.printf("%s %1s [%f, %1f]\n", "95% confidence interval", "=", stats.confidenceLo(), stats.confidenceHi());
  }
    
}
