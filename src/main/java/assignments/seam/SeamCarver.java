package assignments.seam;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
  
  private Picture picture = null;
  private int H = 0; // height of a picture
  private int W = 0; // width of a picture
  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    this.picture = picture;
    H = picture.height();
    W = picture.width();
  }

  // current picture
  public Picture picture() {
    return picture; // to be implemented
  }

  // width of current picture
  public int width() {
    return W; // to be implemented
  }

  // height of current picture
  public int height() {
    return H; // to be implemented
  }

  // energy of pixel at column x and row y
  public double energy(int x, int y) {
    if (x < 0 || x > W)
      throw new IllegalArgumentException("x is outside its prescribed range.");
    if (y < 0 || y > H)
      throw new IllegalArgumentException("y is outside its prescribed range.");
    
    return 0.0; // to be implemented
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    return null; // to be implemented
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    return null; // to be implemented
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null)
      throw new IllegalArgumentException("A seam cannot be null.");
  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {
    if (seam == null)
      throw new IllegalArgumentException("A seam cannot be null.");
  }

  //  unit testing (optional)
  public static void main(String[] args) {
    
  }
}
