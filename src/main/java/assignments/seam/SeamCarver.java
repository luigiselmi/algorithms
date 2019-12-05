package assignments.seam;

import java.util.Arrays;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
  
  private Picture picture = null;
  private int H = 0; // height of a picture
  private int W = 0; // width of a picture
  private static final double BORDER_PIXEL_ENERGY = 1000.0;
  private boolean isTranspose = false; // is the picture a transpose of the original one ?
  
  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    this.picture = picture;
    H = picture.height();
    W = picture.width();
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
    if (x == 0 || x == W - 1) return BORDER_PIXEL_ENERGY;
    else if (y == 0 || y == H - 1) return BORDER_PIXEL_ENERGY;
    else
      return Math.sqrt( energyX(x, y) + energyY(x, y) );
  }
  
  /*
   * Computes the x-component of the gradient energy function
   * of a pixel.
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
   * of a pixel.
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
    transposePicture();
    int[] seam = findVerticalSeam();
    transposePicture();
    return seam; 
  }
  
  public void transposePicture() {
    int tPicHeight = picture.width();
    int tPicWidth = picture.height();
    Picture tPic = new Picture(tPicWidth, tPicHeight);
    for (int i = 0; i < tPicWidth; i++)
        for (int j = 0; j < tPicHeight; j++)
            tPic.setRGB(i, j, picture.getRGB(j, i));
    picture = tPic;
    H = tPicHeight;
    W = tPicWidth;
    isTranspose = true;
  }

  /*
   * This methods creates a tree from a (source) pixel in the top row of the image
   * and computes the shortest path (i.e. minimum total energy) to a (target) pixel 
   * in the bottom row that is reachable from the source. It builds the path of minimum
   * energy by choosing at every step the child vertex of lower energy among the three
   * pixels that are below the current one. It repeats the computation for all the W 
   * pixels in the top row and finally returns the path of minimum energy. The energy
   * of each pixel is computed only once and then stored in a local array.
   */
  public int[] findVerticalSeam() {
    double [][] energy = new double[H][W];
    //initEnery(energy);
    int [] minSeam = null; // vertical seam of lowest total energy
    double minTotalEnergy = Double.POSITIVE_INFINITY; // lowest energy
    for (int x = 0; x < W; x++) {
      int [] seam = new int[H]; // stores the row indexes of the minimum energy path
      seam[0] = x;
      double seamEnergy = energy(x,0); 
      for (int y = 0; y < H - 1; y++) {
        double childMinTotalEnergy = Double.POSITIVE_INFINITY;
        for (int j = -1 + seam[y]; j < seam[y] + 2; j++) {
          if (j < 0 || j > W - 1) continue;
          double childTotalEnergy = pixelEnergy(energy, j,y + 1) + seamEnergy;
          if (childTotalEnergy < childMinTotalEnergy) {
            childMinTotalEnergy = childTotalEnergy; 
            seam[y + 1] = j;
          }
        }
        seamEnergy = childMinTotalEnergy;
      }
      if (seamEnergy < minTotalEnergy) {
        minTotalEnergy = seamEnergy;
        minSeam = seam;
      }
    }
      
    return minSeam;
  }
  /*
   * Initialize an array to store the pixels' energy. 
   */
  private void initEnery(double [][] energy) {
    Arrays.fill(energy[0], 1000.0); // top row, y=0
    for (int y = 1; y < H - 1; y++)
      Arrays.fill(energy[y], 1, W - 1, -1.0);
    Arrays.fill(energy[H - 1], 1000.0); // bottom row, y=H-1
    for (int y = 1; y < H - 1; y++) {
      energy[y][0] = 1000.0;
      energy[y][W - 1] = 1000.0;
    }
  }
  
  // computes the energy of a pixel if it's not been already computed.
  private double pixelEnergy(double [][] energy, int x, int y) {
    if (energy != null && energy[y][x] > 0.0) 
      return energy[y][x];
    else 
      energy[y][x] = energy(x, y);
    return energy[y][x];
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null)
      throw new IllegalArgumentException("A seam cannot be null.");
    if (seam.length > W)
      throw new IllegalArgumentException("The size of a horizontal seam cannot be bigger than the width of the picture.");
  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {
    if (seam == null)
      throw new IllegalArgumentException("A seam cannot be null.");
    if (seam.length > H)
      throw new IllegalArgumentException("The size of a vertical seam cannot be bigger than the height of the picture.");
  }

  //  unit testing 
  public static void main(String[] args) {
    Picture pic = new Picture(args[0]);
    SeamCarver seam = new SeamCarver(pic);
    int width = seam.width();
    int height = seam.height();
    
    // energy computation
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++)
        StdOut.printf("%9.0f ", seam.energy(x, y));
      StdOut.println();
    }
    
    // vertical seam
    int [] vseam = seam.findVerticalSeam();
    for (int y = 0; y < seam.height(); y++)
      StdOut.printf("%d\n", vseam[y]);
    
    // transpose picture
    seam.transposePicture();
  }
}
