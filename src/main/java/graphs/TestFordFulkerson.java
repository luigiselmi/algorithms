/**
 * Test the Ford-Fulkerson algorithm to compute the max-flow
 * of a flow network. Each edge in the flow network is 
 * assigned a capacity. The algorithm computes the maximum
 * flow from source to sink given the capacity constraints.
 * 
 * $ java -cp "lib/algs4.jar;target/classes" graphs.TestFordFulkerson resources/graphs/tinyFN.txt
 * 
 */
package graphs;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestFordFulkerson {

  public static void main(String[] args) {
    // creates flow network from file
    FlowNetwork G = new FlowNetwork(new In(args[0]));
    int s = 0, t = G.V() - 1;
    System.out.println("Number of vertices: " + G.V() + ", Number of edges: " + G.E());
    StdOut.println(G);

    // computes maximum flow and minimum cut
    FordFulkerson maxflow = new FordFulkerson(G, s, t);
    StdOut.println("Max flow from source vertex " + s + " to sink vertex " + t);
    for (int v = 0; v < G.V(); v++) 
        for (FlowEdge e : G.adj(v)) 
            if ((v == e.from()) && e.flow() > 0)
                StdOut.println("   " + e);
        
    StdOut.println("Max flow value = " + maxflow.value());

  }

}
