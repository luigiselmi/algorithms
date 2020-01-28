/**
 * Key-indexed counting
 */
package strings;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KIC {

  private int N; // number of records
  private static int R = 5; // alphabet's size
  private Record [] a;
  private int [] count = new int[R + 1];
  
  private static class Record {
    int key;
    String name;
  }
  
  public KIC(Record [] a) {
    this.a = a;
    this.N = a.length;
  }
  
  public Record [] sort() {
    return distribute(a, index(count()));
  }
  
  // Compute frequency counts
  public int [] count() {
    for (int i = 0; i < N; i++)
      count[a[i].key + 1]++;
    return count;
  }
 
  // Transform counts to indices
  public int [] index(int [] count) {
    for (int r = 0; r < R; r++)
      count[r+1] += count[r];
    return count;
  }
  
  // Distribute the records and copy back
  private Record [] distribute(Record [] a, int [] count) {
    Record [] aux = new Record[N]; 
    for (int i = 0; i < N; i++)
      aux[count[a[i].key]++] = a[i];
      // Copy back.
      for (int i = 0; i < N; i++)
      a[i] = aux[i];
    return a;
  }
  
  public static void main(String[] args) {
    int N = StdIn.readInt(); 
    Record [] a = new Record[N];
    for (int i = 0; i < N; i++) {
      String name = StdIn.readString();
      int key = StdIn.readInt();
      Record rec = new Record();
      rec.key = key;
      rec.name = name;
      a[i] = rec;
    }
    
    KIC counter = new KIC(a);
    int [] sorted = counter.count();
    for (int j = 1; j < counter.R + 1; j++)
      //StdOut.printf("%s %d %s %d \n", sorted[j].name, sorted[j].key, a[j].name, a[j].key);
      StdOut.printf("%d \n", sorted[j]);
    StdOut.println();
    
    int [] indexed = counter.index(sorted);
    for (int j = 1; j < counter.R + 1; j++)
      StdOut.printf("%d \n", indexed[j]);
    StdOut.println();
    
    Record [] as = counter.distribute(a, indexed);
    for (int j = 0; j < N; j++)
      StdOut.printf("Name: %s, Class: %d \n", as[j].name, as[j].key);
  }

}
