package assignments.burrows;

public class MoveToFront {
  
  //apply move-to-front encoding, reading from standard input and writing to standard output
  public static void encode() {
    
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
