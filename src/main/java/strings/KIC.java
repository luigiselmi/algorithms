/**
 * Key-indexed counting
 */
package strings;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class Record {
  int key;
  String name;
}

public class KIC {
  
  public Record [] sort(Record [] records, int R) {
    return distribute(records, index(count(records, R)));
  }
  
  // Compute frequency counts
  private int [] count(Record [] records, int R) {
    int [] counts = new int[R + 1];
    for (int i = 0; i < records.length; i++)
      counts[records[i].key + 1]++;
    return counts;
  }
 
  // Transform counts to indices
  private int [] index(int [] counts) {
    for (int r = 0; r < counts.length - 1; r++)
      counts[r+1] += counts[r];
    return counts;
  }
  
  // Distribute the records and copy back
  private Record [] distribute(Record [] records, int [] counts) {
    int N = records.length;
    Record [] sorted = new Record[N]; 
    for (int i = 0; i < N; i++)
      sorted[counts[records[i].key]++] = records[i];
    return sorted;
  }
  
  public static void main(String[] args) {
    int N = StdIn.readInt(); // number of records 
    int R = StdIn.readInt(); // key space size
    Record [] a = new Record[N];
    for (int i = 0; i < N; i++) {
      String name = StdIn.readString();
      int key = StdIn.readInt();
      Record rec = new Record();
      rec.key = key;
      rec.name = name;
      a[i] = rec;
    }
    
    KIC counter = new KIC();
    Record [] sorted  = counter.sort(a, R);
    for (int j = 0; j < N; j++)
      StdOut.printf("%-10s %-4d   %-10s %d \n", a[j].name, a[j].key, sorted[j].name, sorted[j].key);
    StdOut.println();
  }
}

