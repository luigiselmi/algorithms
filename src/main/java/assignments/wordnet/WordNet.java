package assignments.wordnet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
  private ST<String, Integer> synsetsTable;
  private Iterable<String> nouns;
  
  /*
   * Constructor takes the name of the two input files. 
   * The 1st file contains the synsets. Each synset is made up of three elements
   * the synset id, the synset, and the synset definition, separated by a comma.
   * The constructor put each synset (key) and synset id (value) in a symbol table.
   * The synset definition is not used. 
   */
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null)
      throw new IllegalArgumentException("The constructor requeires synsets and hypernyms.");
    
    synsetsTable = new ST<String, Integer>();
    In synsetsIn = new In(synsets);
    while ( !synsetsIn.isEmpty() ) {
      String line = synsetsIn.readLine();
      Integer synsetId = Integer.parseInt(line.split(",")[0]);
      String synset = line.split(",")[1];
      synsetsTable.put(synset, synsetId);
    }
    
    In hypernymsIn = new In(hypernyms);
    
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
    return synsetsTable.keys();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    if (word == null)
      throw new IllegalArgumentException("A word cannot be null.");
    return synsetsTable.get(word) != null;
  }
  
  // distance between nounA and nounB (defined below)
  /*
  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null)
      throw new IllegalArgumentException("Both nouns must be provided.");
  }
  */
  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  /*
  public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null)
      throw new IllegalArgumentException("Both nouns must be provided.");
  }
  */
  // do unit testing of this class
  public static void main(String[] args) {
    WordNet wn = new WordNet(args[0], args[1]);
    
    //for (String noun: wn.nouns())
    //  StdOut.println(noun);
    
    StdOut.printf("\"yellowlegs\" is a noun ? %s ", wn.isNoun("yellowlegs"));
  }

}
