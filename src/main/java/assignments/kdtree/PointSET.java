package assignments.kdtree;

import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
  
  private boolean isEmpty;
  private int size;
  private TreeSet<Point2D> pointSet = null; 

  //construct an empty set of points
  public PointSET() {
    this.pointSet = new TreeSet<Point2D>();
    this.isEmpty = true;
    this.size = 0;
  }
  
  //is the set empty?
  public boolean isEmpty() {
    return this.isEmpty;
  }
  
  //number of points in the set
  public int size() {
    return this.size;
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
  
  //all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> insideRect = new Queue<Point2D>();
    if (rect == null)
      throw new IllegalArgumentException("A rectangle cannot be null.");
    for (Point2D p: pointSet)
      if (rect.contains(p))
        insideRect.enqueue(p);
    return insideRect;
  }
  
  //a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null)
      throw new IllegalArgumentException("A 2D point cannot be null.");
    Point2D nearestNeighbor = null;
    Point2D ceilingPoint = pointSet.ceiling(p); 
    double minDistance = ceilingPoint.distanceSquaredTo(p);
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
    
  }
}
