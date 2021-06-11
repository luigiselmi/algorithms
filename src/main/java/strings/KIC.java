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
 * $ java -cp "lib/algs4.jar;target/classes" strings.KIC resources/strings/students.txt
 *  
 */
package strings;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class KIC {
  
  int N = 0; // number of students
  int R = 0; // key space size (classes)
  Student [] students;
  
  private class Student {
    int key;
    String name;
  }
  
  public KIC(In is) {
    students = new Student[10]; // default array capacity
    while (is.hasNextLine()) {
      int key = is.readInt();
      String name = is.readString();
      Student s = new Student();
      s.key = key;
      s.name = name;
      students[N] = s;
      N++;
      if (students.length == N) resize(2 * N); // doubles the array's capacity
    }
    if (students.length > N) resize(N); // resize to correct number of items
  }
  
  public int size() {
    return N;
  }
  
  public Student [] getStudents() {
    return students;
  }
  
  //sort by key-indexed counting
  public Student [] sort(Student [] s, int R) {
    return distribute(s, index(count(s, R)));
  }
  
  // Compute frequency counts, i.e. number of occurrences of a key
  private int [] count(Student [] s, int R) {
    int [] counts = new int[R + 1];
    for (int i = 0; i < s.length; i++)
      counts[s[i].key + 1]++;
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
  private Student [] distribute(Student [] records, int [] counts) {
    int N = records.length;
    Student [] sorted = new Student[N]; 
    for (int i = 0; i < N; i++)
      sorted[counts[records[i].key]++] = records[i];
    return sorted;
  }
  
  //changes the size of the array
  private void resize(int capacity) { 
    Student[] temp = new Student[capacity];
    for (int i = 0; i < N; i++)
      temp[i] = students[i];
    students = temp;
  }
  
  public static void main(String[] args) {
    In is = new In(args[0]);
    KIC kic = new KIC(is);
    
    System.out.println(kic.size());
    
    for (int i = 0; i < kic.size(); i++)
      System.out.println(kic.getStudents()[i].name);
    
  }
 
}

