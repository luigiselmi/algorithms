package assignments.collinear;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
  
  private Point [] points;
  private int numberOfSegments = 0;

  public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 or more points
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
  public int numberOfSegments() {        // the number of line segments
    return numberOfSegments;
  }
  public LineSegment[] segments() {
    LineSegment [] segments = null;
    for (int i = 0; i < points.length; i++) {
      Point p = points[i];
      Point [] qpoints = removePoint(i); // remove point p
      /*
      double [] slopes = new double [qpoints.length - 1];
      for (int j = 0; j < i; j++) {
        Point q = qpoints[j];
        slopes[j] = p.slopeTo(q);
      }
      for (int j = i + 1; j < qpoints.length; j++) {
        Point q = qpoints[j];
        slopes[j-1] = p.slopeTo(q);
      }
      */
      // Sort the points according to the slopes they makes with p.
      Arrays.sort(qpoints, p.slopeOrder() );
      // Check if any 3 (or more) adjacent points in the sorted 
      // order have equal slopes with respect to p.
      int numAdiacentPoints = 1;
      int start = 0;
      int stop = 0;
      for (int k = 0; k < qpoints.length - 1; k++) {
        if (p.slopeTo(qpoints[k]) == p.slopeTo(qpoints[k + 1])) {
          if (numAdiacentPoints == 1) start = k;
          numAdiacentPoints++;
        }
        else {
          stop = k;
        //}
        
        if (stop > 0 && numAdiacentPoints >= 3) {
          Point [] collinearPoints = new Point[numAdiacentPoints + 1];
          collinearPoints[0] = p;
          for (int l = 1; l <= numAdiacentPoints; l++) {
            collinearPoints[l] = qpoints[start + l - 1]; 
          }
          Arrays.sort(collinearPoints);
          LineSegment segment = new LineSegment(collinearPoints[0],collinearPoints[collinearPoints.length - 1]);
          numberOfSegments++;
          if (segments == null) segments = new LineSegment[1];
          if (numberOfSegments > segments.length) segments = resize(numberOfSegments, segments);  
          segments[segments.length - 1] = segment;
          break; // stop after having found all collinear points to p
        } }
      }
    }
    
    return segments;
  }
  // remove one point from the point array
  private Point[] removePoint(int i) {
    Point [] qpoints = new Point[points.length - 1];
    for (int j = 0; j < i; j++) {
      qpoints[j] = points[j];
    }
    for (int j = i + 1; j < points.length; j++) {
      qpoints[j - 1] = points[j];
    }
    return qpoints;
  }
  
  //Move segments array of size N <= max to a new array of size max.
  private LineSegment[] resize(int max, LineSegment [] segments) { 
    LineSegment[] temp = new LineSegment[max];
    for (int i = 0; i < segments.length; i++)
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
        StdDraw.show();
    }
    
  }

}
