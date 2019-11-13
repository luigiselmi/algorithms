package assignments.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

public class WordNet {
  private ST<String, Queue<Integer>> nounsInvertedIndex;
  private ST<Integer, Queue<String>> synsetsIndex;
  private int synsetNum = 0; // number of synsets
  private Digraph G;
  private SAP sap;
  //private int rootVertex;
  
  /*
   * The constructor takes the name of two input files. 
   * The 1st file contains the synsets. Each synset is made up of three elements
   * the synset id (integer), the synset (string), and the synset definition, separated by a comma.
   * Each synset can contain more than one noun so that each noun can be associated
   * to one or more synset and synset id. 
   * The constructor builds two indices: the synset ids index, and the nouns inverted index.
   * The index of synset ids is based on a symbol table and uses a synset id as key and a queue of
   * strings for nouns associated to each synset id.
   * The inverted index uses noun as key and synset ids as values 
   * The inverted index is based on a symbol table with key of type integer and values 
   * in a queue of strings so that more than one noun can be assigned to a synset id.
   * The synset definition (3rd field) is not used. 
   * The constructor builds the directed graph using the hypenyms of each synset.
   */
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null)
      throw new IllegalArgumentException("The constructor requires synsets and hypernyms.");
    synsetsIndex = new ST<Integer, Queue<String>>(); 
    nounsInvertedIndex = new ST<String, Queue<Integer>>();
    In synsetsIn = new In(synsets);
    while ( !synsetsIn.isEmpty() ) {
      String [] line = synsetsIn.readLine().split(",");
      String [] nouns = line[1].split(" ");
      for (int i = 0 ; i < nouns.length; i++) {
        // populate the synset ids index
        if (! synsetsIndex.contains(synsetNum)) synsetsIndex.put(synsetNum, new Queue<String>());
        synsetsIndex.get(synsetNum).enqueue(nouns[i]);
        // populate the nouns inverted index
        if (! nounsInvertedIndex.contains(nouns[i])) nounsInvertedIndex.put(nouns[i], new Queue<Integer>());
        nounsInvertedIndex.get(nouns[i]).enqueue(synsetNum);
      }
      synsetNum++;
    }
    
    G = new Digraph(synsetNum);
    In hypernymsIn = new In(hypernyms);
    for (int i = 0 ; i < synsetNum; i++) {
      String [] vertices = hypernymsIn.readLine().split(",");
        int v = Integer.parseInt(vertices[0]);
        for (int j = 1; j < vertices.length ; j++) {
          int w = Integer.parseInt(vertices[j]);
          G.addEdge(v, w); 
        }
    }
    
    if ( ! isRootedDAG(G) )
      throw new IllegalArgumentException("The input to the constructor does not correspond to a rooted DAG.");
    
    sap = new SAP(G);
    
  }
  /*
   * A directed graph (digraph) has a (topological) order 
   * if and only if it is a directed acyclic graph (DAG). 
   * A DAG is rooted if it has only one root (i.e. a vertex 
   * without outgoing edges).
   */
  private boolean isRootedDAG(Digraph G) {
    boolean hasOrder = new Topological(G).hasOrder();
    int numberOfVerticesWithoutOutgoingEdges = 0;
    if (hasOrder) 
      for (int v = 0; v < G.V(); v++) 
        if (G.outdegree(v) == 0) {
          numberOfVerticesWithoutOutgoingEdges++;  
          //rootVertex = v;
        }
    boolean isRootedDAG = hasOrder && (numberOfVerticesWithoutOutgoingEdges == 1);
    return isRootedDAG;
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
  
  // length of shortest ancestral path between two nouns
  public int distance(String nounA, String nounB) {
    if (! this.isNoun(nounA) || ! this.isNoun(nounB))
      throw new IllegalArgumentException("Both nouns must be not null and WordNet nouns.");
    Queue<Integer> synsetsA = nounsInvertedIndex.get(nounA);
    Queue<Integer> synsetsB = nounsInvertedIndex.get(nounB);
    int distance = sap.length(synsetsA, synsetsB);
    return distance;
  }
  
  /*
   *  A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   *  in a shortest ancestral path.
   */
  public String sap(String nounA, String nounB) {
    if (! this.isNoun(nounA) || ! this.isNoun(nounB))
      throw new IllegalArgumentException("Both nouns must be not null and WordNet nouns.");
    Queue<Integer> synsetsA = nounsInvertedIndex.get(nounA);
    Queue<Integer> synsetsB = nounsInvertedIndex.get(nounB);
    int commonAncestor = sap.ancestor(synsetsA, synsetsB);
    return synsetsIndex.get(commonAncestor).toString();
  }
  
  // do unit testing of this class
  public static void main(String[] args) {
    WordNet wn = new WordNet(args[0], args[1]);
    
    //for (String noun: wn.nouns())
    //  StdOut.println(noun);
    
    // look for synset ids associated to a noun
    StdOut.printf("Is \"yellowlegs\" a noun ? %s\n", wn.isNoun("yellowlegs"));
    assert wn.isNoun("yellowlegs");
    
    for (int synsetId: wn.nounsInvertedIndex.get("zone"))   
      StdOut.printf("synset id for noun \"zone\" is %d\n", synsetId);
    
    // test shortest ancestral path
    StdOut.printf("The SAP for \"worm\" and \"bird\" is %s.\n", wn.sap("worm", "bird"));
    
    // test distance between nouns
    StdOut.printf("The distance between the nouns \"worm\" and \"bird\" is %s.\n", wn.distance("worm", "bird"));
    assert wn.distance("worm", "bird") == 5;
    
    StdOut.printf("The distance between the nouns \"white_marlin\" and \"mileage\" is %s.\n", wn.distance("white_marlin", "mileage"));
    assert wn.distance("white_marlin", "mileage") == 23;
    
    StdOut.printf("The distance between the nouns \"Black_Plague\" and \"black_marlin\" is %s.\n", wn.distance("Black_Plague", "black_marlin"));
    assert wn.distance("Black_Plague", "black_marlin") == 33;
    
    StdOut.printf("The distance between the nouns \"American_water_spaniel\" and \"histology\" is %s.\n", wn.distance("American_water_spaniel", "histology"));
    assert wn.distance("American_water_spaniel", "histology") == 27;
    
    StdOut.printf("The distance between the nouns \"Brown_Swiss\" and \"barrel_roll\" is %s.\n", wn.distance("Brown_Swiss", "barrel_roll"));
    assert wn.distance("Brown_Swiss", "barrel_roll") == 29;
    
  }

}
