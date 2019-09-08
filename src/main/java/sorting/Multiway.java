package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {

  //This class should not be instantiated.
  private Multiway() { }
  
  public static void merge(In[] streams) {
    int N = streams.length;
    IndexMaxPQ<String> pq = new IndexMaxPQ<String>(N);
    
    for (int i = 0; i < N; i++)
      if (!streams[i].isEmpty())
        pq.insert(i, streams[i].readString());
    
    while (!pq.isEmpty()) {
      StdOut.print(pq.max() + " ");
      int i = pq.delMax();
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
