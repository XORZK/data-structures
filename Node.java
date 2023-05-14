/** Binary Node. */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
  private T value;
  private int depth, pos = 0;
  private Node<T> left, right, parent;
  private Vec2<Double> svg;

  public Node() {
    this.left = null;
    this.right = null;
    this.parent = null;
  }

  public Node(T v) {
    this.value = v;
    this.left = null;
    this.right = null;
    this.parent = null;
  }

  public Node(T v, Node<T> l, Node<T> r) {
    this.value = v;
    this.setLeft(l);
    this.setRight(r);
  }

  public Node<T> getLeft() {
    return this.left;
  }

  public Node<T> getRight() {
    return this.right;
  }

  public Node<T> getParent() {
    return this.parent;
  }

  public void setPos(int p) {
    this.pos = p;
  }

  public int getPos() {
    return this.pos;
  }

  /**    0
   *    0 1 
   *   0 1 2 3  --> (2x, 2x+1)
   *  0 1 2 3 4 5 6 7 
   */
  public void setLeft(Node<T> node) {
    this.left = node;
    this.left.setParent(this);
    this.left.setPos(2*this.pos);
  }

  public void setRight(Node<T> node) {
    this.right = node;
    this.right.setParent(this);
    this.right.setPos(2*this.pos+1);
  }

  public void setParent(Node<T> node) {
    this.parent = node;
    if (node != null) {
      this.depth = node.getDepth()+1;
    }
  }

  public void setLeft(T v) {
    Node<T> node = new Node<T>(v);
    this.setLeft(node);
  }

  public void setRight(T v)  {
    Node<T> node = new Node<T>(v);
    this.setRight(node);
  }

  public void removeRight() {
    this.right = null;
  }

  public void removeLeft() {
    this.left = null;
  }

  public void removeChildren() {
    this.left = null;
    this.right = null;
  }

  public T getValue() {
    return this.value;
  }

  public void setValue(T v) {
    this.value = v;
  }

  public int getDepth() {
    return this.depth;
  }

  public void setDepth(int d) {
    this.depth = d;
  }

  public boolean isLeaf() {
    return (this.left == null && this.right == null);
  }

  public int children() {
    return ((this.left == null ? 0 : 1) + (this.right == null ? 0 : 1));
  }

  public Node<T> dfs(T value) {
    if (this.value.equals(value)) return this;
    else {
      Node<T> ret = null;
      if (this.right != null && (ret = this.right.dfs(value)) != null) return ret;
      if (this.left != null && (ret = this.left.dfs(value)) != null) return ret;
    }
    return null;
  }

  public boolean contains(T v) {
    boolean ll = (this.left != null), rr = (this.right != null), eq = (this.value.equals(v));
    return (eq ? true : ll && rr ? (left.contains(v) || right.contains(v)) :
            ll && !rr ? (left.contains(v)) :
            !ll && rr ? (right.contains(v)) : false);
  }

  public Vec2<Double> getSVG() {
    return this.svg;
  }

  public void setSVG(double x, double y) {
    this.svg = new Vec2<Double>(x,y);
  }

  public void setSVG(Vec2<Double> s) {
    this.svg = s;
  }

  public String toString() {
    return String.format("{%s}", this.value.toString());
  }

  public int compareTo(Node<T> node) {
    return this.getValue().compareTo(node.getValue());
  }

  public int height() {
    int max = 0;
    Queue<Node<T>> q = new Queue<Node<T>>();
    q.enqueue(this);

    while (!q.empty()) {
      Node<T> node = q.dequeue();

      max = Math.max(max, node.getDepth() - this.getDepth());

      if (node.getRight() != null) q.enqueue(node.getRight());
      if (node.getLeft() != null) q.enqueue(node.getLeft());
    }

    return max+1;
  }

  public int df() {
    int l = (this.getLeft() != null ? this.getLeft().height() : 0), 
        r = (this.getRight() != null ? this.getRight().height() : 0);
    return l-r;
  }
}