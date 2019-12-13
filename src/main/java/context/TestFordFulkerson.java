package context;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestFordFulkerson {
  public static void main(String [] args) {
    In in = new In(args[0]);
    FlowNetwork G = new FlowNetwork(in);
    StdOut.println(G);
    int s = 0; // source vertex
    int t = G.V() - 1; // target vertex
    
    // compute maximum flow and minimum cut
    FordFulkerson maxflow = new FordFulkerson(G, s, t);
    StdOut.println("Max flow from " + s + " to " + t);
    for (int v = 0; v < G.V(); v++) {
        for (FlowEdge e : G.adj(v)) {
            if ((v == e.from()) && e.flow() > 0)
                StdOut.println("   " + e);
        }
    }
    
    // print min-cut
    StdOut.print("Min cut: ");
    for (int v = 0; v < G.V(); v++) {
        if (maxflow.inCut(v)) StdOut.print(v + " ");
    }
    StdOut.println();

    StdOut.println("Max flow value = " +  maxflow.value());
 
  }

}
