/**
 * This class implements depth-first search on a graph.
 * It can be used to search for all vertices connected
 * to a vertex passed as argument. For example to search
 * for all vertices connected to a source vertex 3 in a 
 * graph given in an input file tinyG.txt run the command
 * 
 * $ java -cp "lib/algs4.jar;target/classes"  graphs.DepthFirstSearch resources/graphs/tinyG.txt 3
 * 
 */
package graphs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch {
  
  private boolean[] marked; // array of vertices connected to the source
  private int count; // number of vertices connected to the source
  private int[] edgeTo; // each element contains the last vertex connected to the current one
  private final int source; // source vertex
  
  public DepthFirstSearch(Graph G, int s) {
    marked = new boolean [G.V()];
    edgeTo = new int[G.V()];
    source = s;
    dfs(G, source);
  }
  
  private void dfs(Graph G, int v) {
    marked[v] = true;
    count++;
    for (int w : G.adj(v)) 
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(G, w);
      }
  }
  
  /**
   * This method finds if a vertex is connected to the 
   * source vertex given in the constructor.
   * @param v the vertex for which we want to know if it 
   * is connected to the source vertex.
   * @return true if connected, false otherwise.
   */
  public boolean marked(int v) {
    return marked[v];
  }
  /**
   * 
   * @return the number of vertices connected to the source vertex.
   */
  public int count() {
    return count;
  }
  /**
   * 
   * @param v
   * @return the list of vertices in the path from the source
   */
  public Iterable<Integer> pathTo(int v) {
    if (!marked(v)) return null;
    Stack<Integer> path = new Stack<Integer>();
    for (int x = v; x != source; x = edgeTo[x])
      path.push(x);
    path.push(source);
    return path;
  }
  
  public static void main(String[] args) {
    Graph G = new Graph(new In(args[0]));
    int s = Integer.parseInt(args[1]);
    DepthFirstSearch search = new DepthFirstSearch(G, s);
    
    // Are all the vertices in the graph connected ?
    StdOut.print("The graph is ");
    if (search.count() != G.V())
      StdOut.print("NOT ");
    StdOut.println("connected.");
    
    // print the list of vertices connected to the source vertex.
    StdOut.print("The source vertex " + s + " is connected to vertices: ");
    for (int v = 0; v < G.V(); v++)
      if (search.marked(v))
        StdOut.print(v + " ");
    StdOut.println();
    
    // Find paths from the source vertex to all the connected vertices
    for (int v = 0; v < G.V(); v++) {
      StdOut.print(s + " to " + v + ": ");
      if (search.marked(v))
        for (int x : search.pathTo(v))
          if (x == s) StdOut.print(x);
          else StdOut.print("-" + x);
      StdOut.println();
    }
    
  }
}
