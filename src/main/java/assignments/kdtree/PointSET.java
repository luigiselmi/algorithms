package assignments.kdtree;

import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
/*
 * This class represents a data structure where points on a plane 
 * are ordered in a binary search tree according to their coordinates.
 * The 2D point that has the lowest y value goes on the left and 
 * the point with higher y value goes on the right. In case the y 
 * value is the same for two points they are ordered according to 
 * their x value. The binary search tree is then used to look for 
 * points that are within a rectangle or the nearest point to a
 * given one. This class does not use any techniques, such as gridding,
 * to reduce the number of evaluation for insertion and searching. 
 */
public class PointSET {
  
  private TreeSet<Point2D> pointSet = null; 

  //construct an empty set of points
  public PointSET() {
    this.pointSet = new TreeSet<Point2D>();
  }
  
  //is the set empty?
  public boolean isEmpty() {
    return this.pointSet.isEmpty();
  }
  
  //number of points in the set
  public int size() {
    return this.pointSet.size();
  }
  
  //add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null)
      throw new IllegalArgumentException("A 2D point cannot be null.");
    if (! pointSet.contains(p))
      pointSet.add(p);
  }

  //does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null)
      throw new IllegalArgumentException("A 2D point cannot be null.");
    return pointSet.contains(p);
  }
  
  //draw all points to standard draw
  public void draw() {
    for (Point2D p: pointSet)
      StdDraw.point(p.x(), p.y());
  }
  
  /*
   * All points that are inside the rectangle (or on the boundary)
   * Check all points in the set to find out whether they lie within
   * the rectangle (brute force approach)
   */
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> insideRect = new Queue<Point2D>();
    if (rect == null)
      throw new IllegalArgumentException("A rectangle cannot be null.");
    for (Point2D p: pointSet)
      if (rect.contains(p))
        insideRect.enqueue(p);
    return insideRect;
  }
  
  /*
   * A nearest neighbor in the set to point p; null if the set is empty.
   * Check all points in the set to find out which is the closest to the
   * given one (brute force approach).
   */
  public Point2D nearest(Point2D p) {
    if (p == null)
      throw new IllegalArgumentException("A 2D point cannot be null.");
    Point2D nearestNeighbor = pointSet.ceiling(p);
    double minDistance = nearestNeighbor.distanceTo(p);
    for (Point2D point: pointSet) {
      double distance = p.distanceTo(point);
      if (distance < minDistance) {
        minDistance = distance;
        nearestNeighbor = point;
      }
    }
    return nearestNeighbor;
  }

  //unit testing of the methods (optional)
  public static void main(String[] args) {
    // initialize the data structures from file
    String filename = args[0];
    In in = new In(filename);
    PointSET brute = new PointSET();
    while (!in.isEmpty()) {
        double x = in.readDouble();
        double y = in.readDouble();
        Point2D p = new Point2D(x, y);
        brute.insert(p);
    }
    
    // draw the rectangle
    double x0 = 0.25, y0 = 0.25;      
    double x1 = 0.75, y1 = 0.75;      
    RectHV rect = new RectHV(x0, y0, x1, y1);
    rect.draw();
  
    // draw the points within the rectangle
    for (Point2D p: brute.range(rect))
      p.draw();
    
    double xc = 0.5, yc = 0.5;
    Point2D centre = new Point2D(xc, yc);
    Point2D nearest = brute.nearest(centre);
    nearest.drawTo(centre);
  
  }
}
