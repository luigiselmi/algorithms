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
    initEnery();
  }
  
  private void initEnery() {
	  Arrays.fill(energy[0], 1000.0); // first row, y=0
    for (int y = 1; y < H - 1; y++)
      Arrays.fill(energy[y], 1, W - 1, -1.0);
	  Arrays.fill(energy[H - 1], 1000.0); // last row, y=H-1
	  for (int y = 1; y < H - 1; y++) {
	    energy[y][0] = 1000.0;
	    energy[y][W - 1] = 1000.0;
	  }
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
    if (x < 0 || x > W - 1)
      throw new IllegalArgumentException("x is outside its prescribed range: 0 < x < W - 1 where W is the width of the image.");
    if (y < 0 || y > H - 1)
      throw new IllegalArgumentException("y is outside its prescribed range: 0 < y < H - 1 where H is the height of the image.");
    
    if (energy != null && energy[y][x] > 0.0) 
      return energy[y][x];
    else 
      energy[y][x] = Math.sqrt( energyX(x, y) + energyY(x, y) );
    return energy[y][x];
 
  }
  
  /*
   * Computes the x-component of the gradient energy function
   */
  private int energyX(int x, int y) {
    int rgbx2 = picture.getRGB(x + 1, y);
    int rgbx1 = picture.getRGB(x - 1, y);
    int rx2 = (rgbx2 >> 16) & 0xFF;
    int gx2 = (rgbx2 >>  8) & 0xFF;
    int bx2 = (rgbx2 >>  0) & 0xFF;
    int rx1 = (rgbx1 >> 16) & 0xFF;
    int gx1 = (rgbx1 >>  8) & 0xFF;
    int bx1 = (rgbx1 >>  0) & 0xFF;
    int dRx = rx2 - rx1;
    int dGx = gx2 - gx1;
    int dBx = bx2 - bx1;
    int energyX = dRx*dRx + dGx*dGx + dBx*dBx;
    return energyX;
  }
  /*
   * Computes the y-component of the gradient energy function
   */
  private int energyY(int x, int y) {
    int rgby2 = picture.getRGB(x, y + 1);
    int rgby1 = picture.getRGB(x, y - 1);
    int ry2 = (rgby2 >> 16) & 0xFF;
    int gy2 = (rgby2 >>  8) & 0xFF;
    int by2 = (rgby2 >>  0) & 0xFF;
    int ry1 = (rgby1 >> 16) & 0xFF;
    int gy1 = (rgby1 >>  8) & 0xFF;
    int by1 = (rgby1 >>  0) & 0xFF;
    int dRy = ry2 - ry1;
    int dGy = gy2 - gy1;
    int dBy = by2 - by1;
    int energyY = dRy*dRy + dGy*dGy + dBy*dBy; 
    return energyY; 
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
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++)
        StdOut.printf("%9.0f ", seam.energy(x, y));
      StdOut.println();
    }
  }
}
