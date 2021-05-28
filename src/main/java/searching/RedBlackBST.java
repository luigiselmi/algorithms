/**
 * A Red-Black binary search tree is a perfectly balanced 
 * symbol table with guaranteed log(N) time for insertion 
 * and search. Guaranteed means the cost will not exceed 
 * the logarithmic time even in the worst case (e.g. when
 * the input keys are ordered.).
 */
package searching;

import fundamentals.Queue;


public class RedBlackBST<Key extends Comparable<Key>, Value> {

  private static final boolean RED = true;
  private static final boolean BLACK = false;
  
  private Node root; // root of BST
  
  private class Node {
    Key key; // key
    Value val; // associated data
    Node left, right; // subtrees
    int N; // # nodes in this subtree
    boolean color; // color of link from parent to this node
    Node(Key key, Value val, int N, boolean color) {
      this.key = key;
      this.val = val;
      this.N = N;
      this.color = color;
    }
  }
  
  //Search for key. Update value if found; grow table if new.
  public void put(Key key, Value val) { 
    root = put(root, key, val);
    root.color = BLACK;
  }
  // Change key’s value to val if key in subtree rooted at x.
  // Otherwise, add new node to subtree associating key with val.
  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val, 1, RED); // standard insert with red link to parent
    int cmp = key.compareTo(x.key); // cmp < 0 if key < x.key, cmp = 0 if key equal x.key cmp > 0 otherwise.
    if (cmp < 0) x.left = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.val = val;
    
    // keep the correspondence between Red-Black BST and 2-3 tree
    // by performing the following operations after an insertion
    if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
    if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
    if (isRed(x.left) && isRed(x.right)) flipColors(x);
    
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }
  
  //Return value associated with key in the subtree rooted at x;
  // return null if key not present in subtree rooted at x.
  // (same code as in BinarySearchTree.java)
  public Value get(Key key) { 
    return get(root, key); 
  }
  private Value get(Node x, Key key) { 
    if (x == null) return null;
    int cmp = key.compareTo(x.key); // cmp < 0 if key < x.key, cmp = 0 if key equal x.key cmp > 0 otherwise.
    if (cmp < 0) return get(x.left, key);
    else if (cmp > 0) return get(x.right, key);
    else return x.val;
  }
  
  public boolean contains(Key key) {
    return get(key) != null;
  }
  
  // Checks whether the link to the parent is red
  private boolean isRed(Node x) {
    if (x == null) return false;
    return x.color == RED;
  }
  
  private Node rotateLeft(Node h) {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = h.color;
    h.color = RED;
    x.N = h.N;
    h.N = 1 + size(h.left) + size(h.right);
    return x;
  }
  
  private Node rotateRight(Node h) {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = h.color;
    h.color = RED;
    x.N = h.N;
    h.N = 1 + size(h.left) + size(h.right);
    return x;
  }
  
  private void flipColors(Node h) {
    h.color = RED;
    h.left.color = BLACK;
    h.right.color = BLACK;
  }
  
  public int size() { 
    return size(root); 
  }
  private int size(Node x) {
    if (x == null) return 0;
    else return x.N;
  }
  
  /*
   * returns a queue with all the unique keys
   * used in the symbol table.
   */
  public Iterable<Key> keys() { 
    return keys(min(), max()); 
  }
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new Queue<Key>();
    keys(root, queue, lo, hi);
    return queue;
  }
  private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
    if (x == null) return;
    int cmplo = lo.compareTo(x.key);
    int cmphi = hi.compareTo(x.key);
    if (cmplo < 0) keys(x.left, queue, lo, hi);
    if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
    if (cmphi > 0) keys(x.right, queue, lo, hi);
  }
  
  //The smallest key is on the left of each node
  public Key min() {
    return min(root).key;
  }
  private Node min(Node root) {
    Node minNode = root;
    for (Node x = root; x != null; x = x.left)
      minNode = x;
    return minNode;
  }
 
  //The largest key is on the right of each node
  public Key max() {
    return max(root).key;
  }
  private Node max(Node root) {
    Node maxNode = root;
    for (Node x = root; x != null; x = x.right)
      maxNode = x;
    return maxNode;
  }
  
  public static void main(String[] args) {
    RedBlackBST<String, Integer> rbbst = new RedBlackBST<String, Integer>();
    String [] keys = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
    for (int i = 0; i < keys.length; i++) 
      rbbst.put(keys[i], i);
    
    for (String x: rbbst.keys())
      System.out.print(x);

  }

}
