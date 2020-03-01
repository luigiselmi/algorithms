/**
 * The purpose of move-to-front algorithm is to move the most frequent 
 * characters in a text to the front of the characters set, e.g. ASCII, 
 * and use the new position as index in the character set in encoding.
 * The encoding phase is fast since the most used characters are moved
 * in the front of the character set. The same happens in the decoding 
 * phase.  
 * 
 * Execution: 
 * $ java -cp "lib/algs4.jar;target/classes" assignments.burrows.MoveToFront - < resources/strings/abra.txt
 *        | java -cp "lib/algs4.jar" edu.princeton.cs.algs4.HexDump 16
 */
package assignments.burrows;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
  
  // Apply move-to-front encoding, reading from standard input and writing to standard output.
  // Reads characters and outputs indexes from an ordered sequence.
  public static void encode() {
    Alphabet alphabet = Alphabet.EXTENDED_ASCII;
    int R = alphabet.radix();
    // initialize the ordered sequence
    char [] orderedSequence = new char[R];
    for (int i = 0; i < R; i++)
      orderedSequence[i] = alphabet.toChar(i); 
    
    int indexInputChar = 0; // index of the input char in the ordered sequence
    while ( ! BinaryStdIn.isEmpty() ) {
      // read a character 
      char c = BinaryStdIn.readChar();  
      // find the character in the ordered sequence
      for (int j = 0; j < R; j++)
        if (orderedSequence[j] == c) {
          indexInputChar = j;
          break;
        }
      
      // write the character's 8-bit index
      BinaryStdOut.write(indexInputChar, 8);
      
      // move the character to front of ordered sequence
      for (int j = indexInputChar; j > 0 ; j--)
        orderedSequence[j] = orderedSequence[j - 1];
      orderedSequence[0] = c;
      
    }
    
    BinaryStdOut.close();    
  }

  // Apply move-to-front decoding, reading from standard input and writing to standard output.
  // Reads indexes in an ordered sequence and outputs characters.
  public static void decode() {
    Alphabet alphabet = Alphabet.EXTENDED_ASCII;
    int R = alphabet.radix();

    // initialize the ordered characters' sequence 
    char [] orderedSequence = new char[R];
    for (int i = 0; i < R; i++)
      orderedSequence[i] = alphabet.toChar(i); 
    
    while ( ! BinaryStdIn.isEmpty() ) {
      
      int index = BinaryStdIn.readChar(); // read input character index
      
      // write character
      char output = orderedSequence[index];
      BinaryStdOut.write(output);
      
      // move the character to front of ordered characters' sequence
      for (int j = index; j > 0 ; j--)
        orderedSequence[j] = orderedSequence[j - 1];
      orderedSequence[0] = output;
      
    }
    
    BinaryStdOut.close();
    
  }

  // if args[0] is "-", apply move-to-front encoding
  // if args[0] is "+", apply move-to-front decoding
  public static void main(String[] args) {
    if      (args[0].equals("-")) encode();
    else if (args[0].equals("+")) decode();
    else throw new IllegalArgumentException("Illegal command line argument");
  }
}
