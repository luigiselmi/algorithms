package assignment.collinear;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {
  
  private final int x;     // x-coordinate of this point
  private final int y;     // y-coordinate of this point

  /**
   * Initializes a new point.
   *
   * @param  x the <em>x</em>-coordinate of the point
   * @param  y the <em>y</em>-coordinate of the point
   */
  public Point(int x, int y) {
      /* DO NOT MODIFY */
      this.x = x;
      this.y = y;
  }

  /**
   * Draws this point to standard draw.
   */
  public void draw() {
      /* DO NOT MODIFY */
      StdDraw.point(x, y);
  }

  /**
   * Draws the line segment between this point and the specified point
   * to standard draw.
   *
   * @param that the other point
   */
  public void drawTo(Point that) {
      /* DO NOT MODIFY */
      StdDraw.line(this.x, this.y, that.x, that.y);
  }

  /**
   * Returns the slope between this point and the specified point.
   * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
   * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
   * +0.0 if the line segment connecting the two points is horizontal;
   * Double.POSITIVE_INFINITY if the line segment is vertical;
   * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
   *
   * @param  that the other point
   * @return the slope between this point and the specified point
   */
  public double slopeTo(Point that) {
      if (y == that.y) return 0.0; // horizontal
      else if (x == that.x) return Double.POSITIVE_INFINITY; // vertical
      else if ((x == that.x) && (y == that.y)) return Double.NEGATIVE_INFINITY; // (x0,y0) == (x1,y1)
      else return (that.y - y) / (double)(that.x - x);
  }

  /**
   * Compares two points by y-coordinate, breaking ties by x-coordinate.
   * Formally, the invoking point (x0, y0) is less than the argument point
   * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
   *
   * @param  that the other point
   * @return the value <tt>0</tt> if this point is equal to the argument
   *         point (x0 = x1 and y0 = y1);
   *         a negative integer if this point is less than the argument
   *         point; and a positive integer if this point is greater than the
   *         argument point
   */
  public int compareTo(Point that) {
      if (x == that.x && y == that.y) return 0; 
      else if (y < that.y || (y == that.y && x < that.x)) return -1;
      else return 1;
  }

  /**
   * Compares two points by the slope they make with this point.
   * The slope is defined as in the slopeTo() method.
   *
   * @return the Comparator that defines this ordering on points
   */
  public Comparator<Point> slopeOrder() {
      return new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
          if (slopeTo(p1) == slopeTo(p2)) return 0;
          else if (slopeTo(p1) < slopeTo(p2)) return -1;
          else return 1;
        }
      };
  }


  /**
   * Returns a string representation of this point.
   * This method is provide for debugging;
   * your program should not rely on the format of the string representation.
   *
   * @return a string representation of this point
   */
  public String toString() {
      /* DO NOT MODIFY */
      return "(" + x + ", " + y + ")";
  }

  /**
   * Unit tests the Point data type.
   */
  public static void main(String[] args) {
      Point p0 = new Point(0,0);
      Point p1 = new Point(3,1);
      Point p2 = new Point(1,3);
      
      LineSegment s01 = new LineSegment(p0,p1);
      LineSegment s02 = new LineSegment(p0,p2);
      s01.draw();
      s02.draw();
    
      StdOut.printf("P1(" + p1.x + "," + p1.y + ") is less than P2(" + p2.x + "," + p2.y + ") ? %d\n",  p1.compareTo(p2));
      StdOut.printf("Compare slope order P0-P1 and P0-P2: %d\n", p0.slopeOrder().compare(p1, p2));
  }
}
