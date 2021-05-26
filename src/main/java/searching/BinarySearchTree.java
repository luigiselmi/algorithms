/**
 * A binary search tree is a symbol table that keep the keeps the
 * keys in order at insertion time. The key in a node is smaller 
 * than any key in its right subtree and larger than any key in
 * its left subtree. 
 */
package searching;

import fundamentals.Queue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

  private Node root; // root of BST
  
  private class Node {
    private Key key; // key
    private Value val; // associated value
    private Node left, right; // references to subtrees
    private int N; // # nodes in subtree rooted here
    
    public Node(Key key, Value val, int N)  { 
      this.key = key; this.val = val; this.N = N; 
    }
  }
  
  /*
   * ******************* Main Symbol Table APIs ********************
   */
  
  public int size() { 
    return size(root); 
  }
  private int size(Node x) {
    if (x == null) return 0;
    else return x.N;
  }
  
  // Return value associated with key in the subtree rooted at x;
  // return null if key not present in subtree rooted at x.
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
 
  // Search for key. Update value if found; grow table if new.
  public void put(Key key, Value val) { 
    root = put(root, key, val);
  }
  // Change key’s value to val if key in subtree rooted at x.
  // Otherwise, add new node to subtree associating key with val.
  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val, 1);
    int cmp = key.compareTo(x.key); // cmp < 0 if key < x.key, cmp = 0 if key equal x.key cmp > 0 otherwise.
    if (cmp < 0) x.left = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.val = val;
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }
  
  public boolean contains(Key key) {
    return get(key) != null;
  }
  
  // Deletes a node x by replacing it with its successor.
  // Because x has a right child, its successor is the node
  // with the smallest key in its right subtree
  public void delete(Key key) { 
    root = delete(root, key); 
  }
  private Node delete(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = delete(x.left, key);
    else if (cmp > 0) x.right = delete(x.right, key);
    else {
      if (x.right == null) return x.left;
      if (x.left == null) return x.right;
      Node t = x;
      x = min(t.right); 
      x.right = deleteMin(t.right);
      x.left = t.left;
    }
    x.N = size(x.left) + size(x.right) + 1;
    return x;
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
  
  // The minimum is on the left so the method 
  // only needs to return the reference to right child
  // (if it exists)
  public void deleteMin() {
    root = deleteMin(root);
  }
  private Node deleteMin(Node x) {
    if (x.left == null) return x.right;
    x.left = deleteMin(x.left);
    x.N = size(x.left) + size(x.right) + 1;
    return x;
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

  //The maximum is on the right so the method 
  // only needs to return the reference to left child
  // (if it exists)
  public void deleteMax() {
    root = deleteMax(root);
  }
  private Node deleteMax(Node x) {
    if (x.right == null) return x.left;
    x.right = deleteMax(x.right);
    x.N = size(x.left) + size(x.right) + 1;
    return x;
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
  
  public void print() {
    print(root);
  }
  private void print(Node x) {
    if (x == null) return;
    print(x.left);
    System.out.print(x.key);
    print(x.right);
  }
  
  public static void main(String[] args) {
    BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
    String [] keys = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
    for (int i = 0; i < keys.length; i++) 
      bst.put(keys[i], i);
    
    for (String x: bst.keys())
      System.out.print(x);
    
    System.out.println("\nMinimum key: " + bst.min());
    bst.deleteMin();
    System.out.println("Minimum key: " + bst.min());
    
    System.out.println("Maximum key: " + bst.max());
    bst.deleteMax();;
    System.out.println("Maximum key: " + bst.max());
    
    bst.delete("M");
    
    for (String x: bst.keys())
      System.out.print(x);
    
  }

}
