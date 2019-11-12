package assignments.wordnet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

  private Digraph G;
  private BreadthFirstDirectedPaths bdfs;
  
  //constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    if (G == null)
      throw new IllegalArgumentException("The constructor requires a digraph.");
    this.G = G;
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    validateVertex(v);
    validateVertex(w); 
    int commonAncestor = ancestor(v, w);
    BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G, v);
    BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G, w);
    if (commonAncestor != -1) return bfdpV.distTo(commonAncestor) + bfdpW.distTo(commonAncestor);
    else return -1;
  }

  /*
   * Finds a common ancestor of the input vertices v and w that participates 
   * in a shortest ancestral path; -1 if no such path. 
   * A vertex can be the ancestor of itself.
   * The algorithm checks whether there is a path between any vertex and both
   * input vertex v and w. If the two paths exist, it will look for a common 
   * vertex in both paths that can be an ancestor of both v and w.
   * The algorithm computes the length of the sum of the distances of v and w 
   * from the candidate ancestor. If the length is less than the previous one
   * the candidate ancestor is updated.   
   */
  public int ancestor(int v, int w) {
    validateVertex(v);
    validateVertex(w);
    BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G, v);
    BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G, w);
    int commonAncestor = -1;
    int shortestAncestralDistance = G.E();
    for (int vertex = 0; vertex < G.V(); vertex++)
      if (bfdpV.hasPathTo(vertex) && bfdpW.hasPathTo(vertex)) {
        Iterable<Integer> itV = bfdpV.pathTo(vertex);
        Iterable<Integer> itW = bfdpW.pathTo(vertex);
        for (int ancestorV: itV)
          for (int ancestorW: itW)
            if (ancestorV == ancestorW) {
              int ancestralDistance = bfdpV.distTo(ancestorV) + bfdpW.distTo(ancestorW);
              if (ancestralDistance <shortestAncestralDistance) {
                shortestAncestralDistance = ancestralDistance; 
                commonAncestor = ancestorV;
              }
            }
      }
    return commonAncestor;
  }

  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null)
      throw new IllegalArgumentException("The paths must not be null.");
    validateIterables(v, w);
    int commonAncestor = ancestor(v, w);
    BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G, v);
    BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G, w);
    int distFromV = bfdpV.distTo(commonAncestor);
    int distFromW = bfdpW.distTo(commonAncestor);
    return distFromV + distFromW;
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null)
      throw new IllegalArgumentException("The paths must not be null.");
    validateIterables(v, w);
    BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G, v);
    BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G, w);
    int commonAncestor = -1;
    int shortestAncestralDistance = G.E();
    for (int vertex = 0; vertex < G.V(); vertex++)
      if (bfdpV.hasPathTo(vertex) && bfdpW.hasPathTo(vertex)) {
        Iterable<Integer> itV = bfdpV.pathTo(vertex);
        Iterable<Integer> itW = bfdpW.pathTo(vertex);
        for (int ancestorV: itV)
          for (int ancestorW: itW)
            if (ancestorV == ancestorW) {
              int ancestralDistance = bfdpV.distTo(ancestorV) + bfdpW.distTo(ancestorW);
              if (ancestralDistance <shortestAncestralDistance) {
                shortestAncestralDistance = ancestralDistance; 
                commonAncestor = ancestorV;
              }
            }
      }
    return commonAncestor;
  }
  
  //throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(int v) {
      int V = G.V();
      if (v < 0 || v >= V)
          throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
  }
  
  private void validateIterables(Iterable<Integer> v, Iterable<Integer> w) {
    for (int vertex: v)
      validateVertex(vertex);
    for (int vertex: w)
      validateVertex(vertex);
  }

  // do unit testing of this class
  public static void main(String[] args) {
    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    while (!StdIn.isEmpty()) {
        int v = StdIn.readInt();
        int w = StdIn.readInt();
        int length   = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
  }
  
}
