/**
 * Test class for the Dijkstra algorithm to compute
 * the single-source shortest paths.
 * Example: find the shortest paths from vertex 0
 * 
 * $ java -cp "lib/algs4.jar;target/classes" graphs.TestDijkstraSP resources/graphs/tinyEWD.txt 0
 */
package graphs;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestDijkstraSP {

  public static void main(String[] args) {
    In in = new In(args[0]);
    EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
    int s = Integer.parseInt(args[1]);

    // compute shortest paths
    long start = System.currentTimeMillis();
    DijkstraSP sp = new DijkstraSP(G, s);
    long stop = System.currentTimeMillis();
    double runningTime = (stop - start)/1000.0;
    System.out.println("Dijkstra's SSP running time (s): " + runningTime);
    
    // print shortest path
    for (int t = 0; t < G.V(); t++) {
      if (sp.hasPathTo(t)) {
        StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
        for (DirectedEdge e : sp.pathTo(t)) {
          StdOut.print(e + "   ");
        }
        StdOut.println();
      }
      else {
        StdOut.printf("%d to %d         no path\n", s, t);
      }
    }
  }

}
