package assignments.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WordNetTestClient {

  public static void main(String[] args) {
    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    StdOut.printf("Input Graph", G);
    //SAP sap = new SAP(G);
    while (!StdIn.isEmpty()) {
        int v = StdIn.readInt();
        int w = StdIn.readInt();
        //int length   = sap.length(v, w);
        //int ancestor = sap.ancestor(v, w);
        //StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
  }
}
