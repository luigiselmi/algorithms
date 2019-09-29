package searching;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WordCounter {

  /*
   * This test class takes a sequence of strings from standard input, 
   * builds a symbol table that associates the value i with the ith 
   * string in the input, and then prints the table
   */
	public static void main(String[] args) {
		ST<String, Integer> st = new ST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
  		String key = StdIn.readString();
  		st.put(key, i);
  	}
		int size = st.size();
		StdOut.printf("Symbol Table size: %d\n", size);
		StdOut.println("Distinct words:");
		for (String s : st.keys())
		  StdOut.println(s);

	}

}
