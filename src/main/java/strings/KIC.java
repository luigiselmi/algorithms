/**
 * Key-indexed counting.
 * This class can be used to read an array of records and order them by their integer key.
 * The procedure is:
 * 1) count the occurrences of each key for all records
 * 2) use the occurrences to build an index by key, e.g. indexes for key 1, then for key 2
 * 3) create a new array of records ordered by the key using the index 
 * The sorting is stable, the relative order of records with the same key is kept when they
 * are sorted by key.
 * Execution:
 * 
 * $ java -cp "lib/algs4.jar;target/classes" strings.KIC < resources/strings/students_by_section.txt
 *  
 */
package strings;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class Record {
  int key;
  String name;
}

public class KIC {
  //sort by key-indexed counting
  public Record [] sort(Record [] records, int R) {
    return distribute(records, index(count(records, R)));
  }
  
  // Compute frequency counts, i.e. number of occurrences of a key
  private int [] count(Record [] records, int R) {
    int [] counts = new int[R + 1];
    for (int i = 0; i < records.length; i++)
      counts[records[i].key + 1]++;
    return counts;
  }
 
  // Transform counts to indices, i.e. computes the starting value of the 
  // index for each key.
  private int [] index(int [] counts) {
    for (int r = 0; r < counts.length - 1; r++)
      counts[r+1] += counts[r];
    return counts;
  }
  
  // Distribute the records
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

