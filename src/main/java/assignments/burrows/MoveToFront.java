package assignments.burrows;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
  
  //apply move-to-front encoding, reading from standard input and writing to standard output
  public static void encode() {
    Alphabet alphabet = Alphabet.EXTENDED_ASCII;
    int R = alphabet.radix();
    
    char [] orderedSequence = new char[R];
    for (int i = 0; i < R; i++)
      orderedSequence[i] = alphabet.toChar(i); 
    
    int indexInputChar = 0;
    while ( ! BinaryStdIn.isEmpty() ) {
      char c = BinaryStdIn.readChar();  
      for (int j = 0; j < R; j++)
        if (orderedSequence[j] == c) {
          indexInputChar = j;
          break;
        }
      for (int j = indexInputChar; j > 0 ; j--)
        orderedSequence[j] = orderedSequence[j - 1];
      orderedSequence[0] = c;
      BinaryStdOut.write(indexInputChar, 8);
    }
    BinaryStdOut.close();    
  }

  // apply move-to-front decoding, reading from standard input and writing to standard output
  public static void decode() {
    
  }

  // if args[0] is "-", apply move-to-front encoding
  // if args[0] is "+", apply move-to-front decoding
  public static void main(String[] args) {
    if      (args[0].equals("-")) encode();
    else if (args[0].equals("+")) decode();
    else throw new IllegalArgumentException("Illegal command line argument");
  }
}
