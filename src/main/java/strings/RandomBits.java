/**
 * Writes random bits to standard output. The following command draws 
 * a picture representing bits as black and white pixels.
 * 
 * $ java -cp "lib/algs4.jar;target/classes" strings.RandomBits | java -cp "lib/algs4.jar" edu.princeton.cs.algs4.PictureDump 500 500
 */
package strings;

import edu.princeton.cs.algs4.BinaryStdOut;

public class RandomBits {
  
  public static void main(String[] args) {
    int x = 11111;
    for (int i = 0; i < 1000000; i++) {
      x = x * 314159 + 218281;
      BinaryStdOut.write(x > 0);
    }
    
    BinaryStdOut.close();
  
  }
}
