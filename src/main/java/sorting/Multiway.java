/**
 * This class reads strings from files and outputs the string in alphabetical order.
 * It uses an index priority queue.
 * java -cp "lib/algs4.jar;target/classes" sorting.Multiway resources/sorting/stream1.txt resources/sorting/stream2.txt resources/sorting/stream3.txt
 */
package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {

  //This class should not be instantiated.
  private Multiway() { }
  
  public static void merge(In[] streams) {
    int N = streams.length;
    IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
    
    for (int i = 0; i < N; i++)
      if (!streams[i].isEmpty()) {
        String str = streams[i].readString();
        pq.insert(i, str);
      }
    
    while (!pq.isEmpty()) {
      StdOut.print(pq.minKey() + " ");
      int i = pq.delMin();
      if (!streams[i].isEmpty())
        pq.insert(i, streams[i].readString());
      
    }
  }

  public static void main(String[] args) {
    int N = args.length;
    In[] streams = new In[N];
    for (int i = 0; i < N; i++)
      streams[i] = new In(args[i]);
    merge(streams);
  }
}
