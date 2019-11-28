package assignments.seam;

import java.util.Arrays;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
  
  private Picture picture = null;
  private int H = 0; // height of a picture
  private int W = 0; // width of a picture
  private double [][] energy = null;
  
  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    this.picture = picture;
    H = picture.height();
    W = picture.width();
    energy = new double[H][W];
    for (int row = 0; row < H; row++)
      Arrays.fill(energy[row], -1.0);
  }

  // current picture
  public Picture picture() {
    return picture;
  }

  // width of current picture
  public int width() {
    return W;
  }

  // height of current picture
  public int height() {
    return H;
  }

  /*
   * Computes the energy of pixel at column x and row y
   */
  public double energy(int x, int y) {
    if (x < 0 || x > W)
      throw new IllegalArgumentException("x is outside its prescribed range.");
    if (y < 0 || y > H)
      throw new IllegalArgumentException("y is outside its prescribed range.");
    
    if (energy != null && energy[x][y] > 0.0) 
      return energy[x][y];
    else 
      energy[x][y] = Math.sqrt( energyX(x, y) + energyY(x, y) );
    return energy[x][y];
 
  }
  
  /*
   * Computes the x-component of the gradient energy function
   */
  private double energyX(int x, int y) {
    int deltaRGBx = picture.getRGB(x + 1, y) - picture.getRGB(x - 1, y);
    int dRx = (deltaRGBx >> 16) & 0xFF;
    int dGx = (deltaRGBx >>  8) & 0xFF;
    int dBx = (deltaRGBx >>  0) & 0xFF;
    return dRx*dRx + dGx*dGx + dBx*dBx;
  }
  /*
   * Computes the y-component of the gradient energy function
   */
  private double energyY(int x, int y) {
    int deltaRGBy = picture.getRGB(x, y + 1) - picture.getRGB(x, y - 1);
    int dRy = (deltaRGBy >> 16) & 0xFF;
    int dGy = (deltaRGBy >>  8) & 0xFF;
    int dBy = (deltaRGBy >>  0) & 0xFF;
    return dRy*dRy + dGy*dGy + dBy*dBy;
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
    Picture pic = new Picture(args[0]);
    SeamCarver seam = new SeamCarver(pic);
    int width = seam.width();
    int height = seam.height();
    for (int x = 1; x < width; x++)
      for (int y = 1; y < height; y++)
        StdOut.printf("%f ", seam.energy(x, y));
  }
}
