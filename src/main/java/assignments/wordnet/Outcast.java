package assignments.wordnet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
  
  private WordNet wn;

  //constructor takes a WordNet object
  public Outcast(WordNet wordnet) {
    if (wordnet == null)
      throw new IllegalArgumentException("The constructor needs a not null WordNet lexicon.");
    this.wn = wordnet;
  }
  
  /*
   * Given an array of WordNet nouns, return an outcast,
   * i.e. the noun the least related to the others.
   */
  public String outcast(String[] nouns) {
    if (nouns == null || nouns.length < 2)
      throw new IllegalArgumentException("There must be at least two nouns (both included in WordNet).");
    int [][] nounsPairDistance = new int [nouns.length][nouns.length];
    for (int i = 0; i < nouns.length; i++) {
      for (int j = i + 1; j < nouns.length; j++) {
        nounsPairDistance[i][j] = wn.distance(nouns[i], nouns[j]); // distance is called only once for each pair of nouns
        nounsPairDistance[j][i] = nounsPairDistance[i][j];
      }
    }
    int maxNounDistance = 0;
    int mostDistantNounIndex = 0;
    int nounDistance = 0;
    for (int i = 0; i < nouns.length; i++) {
      for (int j = 0; j < nouns.length; j++)
        nounDistance += nounsPairDistance[i][j];
      if (nounDistance > maxNounDistance) {
        maxNounDistance = nounDistance; 
        mostDistantNounIndex = i;
      }
    }
    return nouns[mostDistantNounIndex];
  }
  
  public static void main(String[] args) {  
    WordNet wordnet = new WordNet(args[0], args[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < args.length; t++) {
      In in = new In(args[t]);
      String[] nouns = in.readAllStrings();
      StdOut.println(args[t] + ": " + outcast.outcast(nouns));
    }
  }
}
