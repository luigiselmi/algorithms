package assignments.collinear;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
  
  Point [] points;   
  int numberOfSegments = 0;
  
  public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
    
    if (points == null)
      throw new IllegalArgumentException("The array cannot be null.");
    
    for (Point point: points )
      if (point == null)
        throw new IllegalArgumentException("No element in the array can be null.");
    
    for (int i = 0; i < points.length; i++) {
      Point p = points[i];
      for (int j = i + 1; j < points.length; j++) {
        Point q = points[j];
        if ( p.compareTo(q) == 0)
          throw new IllegalArgumentException("All points in the array must be different.");
      }
    }
    
     
    this.points = points;
  }
  
  public int numberOfSegments() {       // the number of line segments
    return numberOfSegments;
  }
  
  /**
   * The points belong to the same segment if the slope of the segments made of any two points, is the same. 
   * @return
   */
  public LineSegment[] segments() {               // the line segments containing 4 points                                          
    LineSegment [] segments = null;
    for (int i = 0; i < points.length; i++) {
      Point p = points[i];
      for (int j = i + 1; j < points.length; j++) {
        Point q = points[j];
        for (int k = j + 1; k < points.length; k++) {
          Point r = points[k];
          for (int l = k + 1; l < points.length; l++) {
            Point s = points[l];
            if ( (p.slopeTo(q) == p.slopeTo(r)) && (p.slopeTo(q) == p.slopeTo(s)) ) {
              Point [] temp = {p,q,r,s};
              Arrays.sort(temp);
              LineSegment segment = new LineSegment(temp[0],temp[temp.length - 1]);
              if (segments == null)
                segments = new LineSegment[1];
              if (numberOfSegments == segments.length)
                segments = resize(2 * segments.length, segments);  
              segments[numberOfSegments++] = segment;
            }
          }
        }
      }
    }
    return segments;
  }
  
 // Move segments array of size N <= max to a new array of size max.
 private LineSegment[] resize(int max, LineSegment [] segments) { 
   LineSegment[] temp = new LineSegment[max];
   for (int i = 0; i < numberOfSegments; i++)
    temp[i] = segments[i];
   segments = temp;
   return segments;
 }
  
  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    LineSegment [] segments = collinear.segments();
    StdOut.printf("Number of segments: %d\n", collinear.numberOfSegments());
    for(int i = 0; i < collinear.numberOfSegments(); i++) {
      StdOut.println(segments[i]);
      segments[i].draw();
      StdDraw.show();
    }
    
  }

}
