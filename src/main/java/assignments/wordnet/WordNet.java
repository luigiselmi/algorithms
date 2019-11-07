package assignments.wordnet;

import java.io.BufferedInputStream;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
  private ST<String, Queue<Integer>> nounsInvertedIndex;
  private int synsetNum = 0; // number of synsets
  private Digraph G;
  
  /*
   * Constructor takes the name of the two input files. 
   * The 1st file contains the synsets. Each synset is made up of three elements
   * the synset id, the synset, and the synset definition, separated by a comma.
   * Each synset can contain more than one noun so that each noun can be associated
   * to one or more synset and synset id. 
   * The constructor builds an inverted index with synset id as key and nouns as values 
   * The inverted index is based on a symbol table with key of type integer and values 
   * in a queue of strings so that more than one noun can be assigned to a synset id.
   * The synset definition (3rd field) is not used. 
   * The constructor builds the directed graph using the hypenyms of each synset.
   */
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null)
      throw new IllegalArgumentException("The constructor requires synsets and hypernyms.");
    
    nounsInvertedIndex = new ST<String, Queue<Integer>>();
    In synsetsIn = new In(synsets);
    while ( !synsetsIn.isEmpty() ) {
      String [] line = synsetsIn.readLine().split(",");
      String [] nouns = line[1].split(" ");
      for (int i = 0 ; i < nouns.length; i++) {
        if (!nounsInvertedIndex.contains(nouns[i])) nounsInvertedIndex.put(nouns[i], new Queue<Integer>());
        nounsInvertedIndex.get(nouns[i]).enqueue(synsetNum);
      }
      synsetNum++;
    }
    
    G = new Digraph(synsetNum);
    In hypernymsIn = new In(hypernyms);
    for (int i = 0 ; i < synsetNum - 1; i++) {
      String [] vertices = hypernymsIn.readLine().split(",");
        int v = Integer.parseInt(vertices[0]);
        for (int j = 1; j < vertices.length ; j++) {
          int w = Integer.parseInt(vertices[j]);
          G.addEdge(v, w); 
        }
    }
    
    
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
    return nounsInvertedIndex.keys();
  }

  // is the word a WordNet noun ?
  public boolean isNoun(String word) {
    if (word == null)
      throw new IllegalArgumentException("A word cannot be null.");
    return nounsInvertedIndex.get(word) != null;
  }
  
  // distance between nounA and nounB
  public int distance(String nounA, String nounB) {
    if (! this.isNoun(nounA) || ! this.isNoun(nounB))
      throw new IllegalArgumentException("Both nouns must be not null and WordNet nouns.");
    return 0;
  }
  
  /*
   *  A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   *  in a shortest ancestral path.
   */
  public String sap(String nounA, String nounB) {
    if (! this.isNoun(nounA) || ! this.isNoun(nounB))
      throw new IllegalArgumentException("Both nouns must be not null and WordNet nouns.");
    return null;
  }
  
  // do unit testing of this class
  public static void main(String[] args) {
    WordNet wn = new WordNet(args[0], args[1]);
    
    //for (String noun: wn.nouns())
    //  StdOut.println(noun);
    
    // look for synset ids associated to a noun
    StdOut.printf("Is \"yellowlegs\" a noun ? %s\n", wn.isNoun("yellowlegs"));
    for (int synsetId: wn.nounsInvertedIndex.get("zone"))   
      StdOut.printf("synset id for noun \"zone\" is %d\n", synsetId);
    
  }

}
