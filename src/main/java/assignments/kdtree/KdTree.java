package assignments.kdtree;

import java.util.Comparator;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
  
  private Node root; // root of BST
  
  private static class Node {
    private Node leftBottom; // the left/bottom subtree
    private Node rightTop; // the right/top subtree
    private Point2D point;
    private RectHV rect; // the axis-aligned rectangle corresponding to this node
    private int N; // # nodes in subtree rooted here
    private boolean vertical;
    
    public Node(Point2D p, int N, boolean vertical, RectHV rect) {
      this.point = p;
      this.N = N;
      this.vertical = vertical;
      this.rect = rect;
    }
  }

  //construct an empty set of points
  public KdTree() {}
  
  //is the set empty?
  public boolean isEmpty() {
    return size(root) == 0;
  }
  
  //number of points in the set
  public int size() {
    return size(root);
  }
  private int size(Node x) {
    if (x == null) return 0;
    else return x.N;
  }
  
  //add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null)
      throw new IllegalArgumentException("A 2D point cannot be null.");
    RectHV rootRect = new RectHV(0.0,0.0,1.0,1.0);
    root = insert(root, p, true, rootRect);
  } 
  private Node insert(Node x, Point2D p, boolean orientationVertical, RectHV rect) {
    if (x == null) return new Node(p, 1, orientationVertical, rect);
    if (x.vertical == true) {
      Comparator<Point2D> comparator = Point2D.X_ORDER;
      int cmp = comparator.compare(p, x.point); // if (p.x < q.x) return -1; if (p.x > q.x) return +1; if (p.x == q.x) return 0;
      if (cmp < 0) {
        RectHV lbRec = new RectHV(x.rect.xmin(), x.rect.ymin(), x.point.x(), x.rect.ymax());
        x.leftBottom  = insert(x.leftBottom, p, ! x.vertical, lbRec);
      }
      else if (cmp > 0) {
        RectHV rtRec = new RectHV(p.x(), x.point.y(), x.point.x(), x.point.y());
        x.rightTop = insert(x.rightTop, p, ! x.vertical, rtRec);
      }
      else x.point = p;
      x.N = 1 + size(x.leftBottom) + size(x.rightTop);
    }
    else {
      Comparator<Point2D> comparator = Point2D.Y_ORDER;
      int cmp = comparator.compare(p, x.point); // if (p.y < q.y) return -1; if (p.y > q.y) return +1; if (p.y == q.y) return 0;
      if (cmp < 0) {
        RectHV lbRec = new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.point.y());
        x.leftBottom  = insert(x.leftBottom, p, ! x.vertical, lbRec);
      }
      else if (cmp > 0) {
        RectHV rtRec = new RectHV(x.rect.xmin(), x.point.y(), x.rect.xmax(), x.rect.ymax());
        x.rightTop = insert(x.rightTop, p, ! x.vertical, rtRec);
      }
      else x.point = p;
      x.N = 1 + size(x.leftBottom) + size(x.rightTop);
    }
     
    return x;
  }
  
  //does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null)
      throw new IllegalArgumentException("A 2D point cannot be null.");
    return contains(root, p);
  }
  private boolean contains(Node x, Point2D p) {
    return get(x, p) != null;
  }
  /*
   * Return value associated with key in the subtree rooted at x;
   * return null if key not present in subtree rooted at x.
   */
  private Point2D get(Node x, Point2D p) {
    if (p == null) throw new IllegalArgumentException("calls get() with a null key");
    if (x == null) return null;
    if (x.vertical == true) {
      Comparator<Point2D> comparator = Point2D.X_ORDER;
      int cmp = comparator.compare(p, x.point); // if (p.x < q.x) return -1; if (p.x > q.x) return +1; if (p.x == q.x) return 0;
      if      (cmp < 0) return get(x.leftBottom, p);
      else if (cmp > 0) return get(x.rightTop, p);
      else return x.point;
    }
    else {
      Comparator<Point2D> comparator = Point2D.Y_ORDER;
      int cmp = comparator.compare(p, x.point); // if (p.y < q.y) return -1; if (p.y > q.y) return +1; if (p.y == q.y) return 0;
      if      (cmp < 0) return get(x.leftBottom, p);
      else if (cmp > 0) return get(x.rightTop, p);
      else return x.point;
    }
  }
  
 
  //draw all points to standard draw
  /*
  public void draw() {
    for (Point2D p: pointSet)
      StdDraw.point(p.x(), p.y());
  }
  */
  /*
   * All points that are inside the rectangle (or on the boundary)
   * Check all points in the set to find out whether they lie within
   * the rectangle (brute force approach)
   */
  /*
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> insideRect = new Queue<Point2D>();
    if (rect == null)
      throw new IllegalArgumentException("A rectangle cannot be null.");
    for (Point2D p: pointSet)
      if (rect.contains(p))
        insideRect.enqueue(p);
    return insideRect;
  }
  */
  /*
   * A nearest neighbor in the set to point p; null if the set is empty.
   * Check all points in the set to find out which is the closest to the
   * given one (brute force approach).
   */
  /*
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
  */
  //unit testing of the methods (optional)
  public static void main(String[] args) {
    // Setup the empty binary search tree
    KdTree kdtree = new KdTree();
    
    // create some data points
    double x0 = 0.7, y0 = 0.2;    
    Point2D p0 = new Point2D(x0,y0);
    double x1 = 0.5, y1 = 0.4;    
    Point2D p1 = new Point2D(x1,y1);
    double x2 = 0.2, y2 = 0.3;    
    Point2D p2 = new Point2D(x2,y2);
    double x3 = 0.4, y3 = 0.7;    
    Point2D p3 = new Point2D(x3,y3);
    double x4 = 0.9, y4 = 0.6;    
    Point2D p4 = new Point2D(x4,y4);
    
    // insert the points into the tree
    // and see if they can be reached
    kdtree.insert(p0);
    StdOut.printf("kd-tree contains p0 ? %s\n", kdtree.contains(p0));
    
    kdtree.insert(p1);
    StdOut.printf("kd-tree contains p1 ? %s\n", kdtree.contains(p1));
    
    kdtree.insert(p2);
    StdOut.printf("kd-tree contains p2 ? %s\n", kdtree.contains(p2));
    
    kdtree.insert(p3);
    StdOut.printf("kd-tree contains p3 ? %s\n", kdtree.contains(p3));
    
    kdtree.insert(p4);
    StdOut.printf("kd-tree contains p4 ? %s\n", kdtree.contains(p4));
    
  }
}
