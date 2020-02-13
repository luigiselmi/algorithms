/**
 * This class implements depth-first search on a graph.
 */
package graphs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Search {
  
  private boolean[] marked; // array of vertices connected to the source
  private int count; // number of vertices connected to the source
  
  public Search(Graph G, int source) {
    marked = new boolean [G.V()];
    dfs(G, source);
  }
  
  private void dfs(Graph G, int v) {
    marked[v] = true;
    count++;
    for (int w : G.adj(v))
      if (!marked[w]) dfs(G, w);
  }
  
  /**
   * This method finds if a vertex is connected to the source vertex
   * given in the constructor.
   * @param v the vertex for which we want to know if it 
   * is connected to the source vertex.
   * @return
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
  
  public static void main(String[] args) {
    Graph G = new Graph(new In(args[0]));
    int s = Integer.parseInt(args[1]);
    Search search = new Search(G, s);
    for (int v = 0; v < G.V(); v++)
      if (search.marked(v))
        StdOut.print(v + " ");
    StdOut.println();
    if (search.count() != G.V())
      StdOut.print("NOT ");
    StdOut.println("connected");
  }
}
