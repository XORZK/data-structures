import java.io.PrintWriter;
import java.io.IOException;;

public class BinaryTree<T extends Comparable<T>> {
  protected Node<T> root;

  public BinaryTree() {
    this.root = null;
  }

  public BinaryTree(T value) {
    this.root = new Node<T>(value);
    this.root.setDepth(0);
    this.root.setPos(0);
  }

  public void add(T v) {
    Node<T> node = new Node<T>(v), curr = this.root;
    if (this.root == null) {
      this.root = node;
      this.root.setDepth(0);
      this.root.setPos(0);
    } else {
      while (true) {
        int compare = v.compareTo(curr.getValue());
        if (compare <= 0) {
          if (curr.getLeft() == null) {
            curr.setLeft(node);
            node.setDepth(curr.getDepth()+1);
            return;
          }
          curr = curr.getLeft();
        } else {
          if (curr.getRight() == null) {
            curr.setRight(node);
            node.setDepth(curr.getDepth()+1);
            return;
          }
          curr = curr.getRight();
        }
      }
    }
  }

  public boolean remove(Node<T> node) {
    if (node == null) return false;

    if (node.children() == 0) {
      if (node.getParent() != null) {
        if (node.getParent().getRight() == node) {
          node.getParent().removeRight();
        }
        else {
          node.getParent().removeLeft();
        }
      } else { node = null; }
    } else if (node.children() == 1) {
      Node<T> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
      boolean R = (node.getParent().getRight() == node);
      if (R) {
        node.getParent().setRight(child);
      } else {
        node.getParent().setLeft(child);
      }
      node = null;
    } else {
      Node<T> successor = this.rightSuccessor(node);
      if (successor != null) {
        node.setValue(successor.getValue());
        this.remove(successor);
      }
    }

    return true;
  }
  
  public boolean remove(T value) {
    Node<T> node = this.find(value);
    return this.remove(node);
  }

  public Node<T> find(T value) {
    if (this.root == null) return null;

    Queue<Node<T>> q = new Queue<Node<T>>();
    q.enqueue(this.root);
    while (!q.empty()) {
      Node<T> node = q.dequeue();
      if (node.getValue().equals(value)) return node;
      if (node.getRight() != null) q.enqueue(node.getRight());
      if (node.getLeft() != null) q.enqueue(node.getLeft());
    }
    return null;
  }

  public Node<T> successor(T value) {
    if (this.root == null) return null;

    Node<T> curr = null;
    Queue<Node<T>> q = new Queue<Node<T>>();
    q.enqueue(this.root);

    while (!q.empty()) {
      Node<T> node = q.dequeue();

      if (node.getValue().compareTo(value) > 0) {
        if (curr == null || curr.getValue().compareTo(node.getValue()) > 0) curr = node;
      }

      if (node.getRight() != null) q.enqueue(node.getRight());
      if (node.getLeft() != null) q.enqueue(node.getLeft());
    }
    return curr;
  }

  public Node<T> rightSuccessor(Node<T> node) {
    if (node == null) return null;
    T orig = node.getValue();
    Node<T> curr = null;
    Queue<Node<T>> q = new Queue<Node<T>>();
    q.enqueue(node.getRight());

    while (!q.empty()) {
      Node<T> n = q.dequeue();
      if (n.getValue().compareTo(orig) > 0) {
        if (curr == null || curr.getValue().compareTo(n.getValue()) > 0) curr = n;
      }
      if (n.getRight() != null) q.enqueue(n.getRight());
      if (n.getLeft() != null) q.enqueue(n.getLeft());
    }


    return curr;
  }

  public Node<T> getRoot() {
    return this.root;
  }

  public void setRoot(Node<T> node) {
    this.root = node;
    if (this.root != null) {
      this.root.setDepth(0);
      this.root.setPos(0);
    }
  }

  public boolean contains(T v) {
    return (this.root.contains(v));
  }

  public Node<T> bfs(T value) {
    Queue<Node<T>> q = new Queue<Node<T>>();
    if (this.root != null) q.enqueue(this.root);
    while (!q.empty()) {
      Node<T> curr = q.dequeue();
      if (curr.getValue().equals(value)) return curr;
      if (curr.getRight() != null) q.enqueue(curr.getRight());
      if (curr.getLeft() != null) q.enqueue(curr.getLeft());
    }
    return null;
  }

  public Node<T> dfs(T value) {
    return this.root.dfs(value);
  }

  /** BFS to find max depth of Tree. */
  public int depth() {
    if (this.root == null) return 0;
    int max = 0;
    Queue<Node<T>> q = new Queue<Node<T>>();
    q.enqueue(this.root);

    while (!q.empty()) {
      Node<T> curr = q.dequeue();
      max = Math.max(curr.getDepth(), max);
      if (curr.getRight() != null) q.enqueue(curr.getRight());
      if (curr.getLeft() != null) q.enqueue(curr.getLeft());
    }
    return max+1;
  }

  public int size() {
    if (this.root == null) return 0;
    int max = 0;
    Queue<Node<T>> q = new Queue<Node<T>>();
    q.enqueue(this.root);
    while (!q.empty()) {
      max++;
      Node<T> curr = q.dequeue();
      if (curr.getRight() != null) q.enqueue(curr.getRight());
      if (curr.getLeft() != null) q.enqueue(curr.getLeft());
    }
    return max;
  }

  /** Computes the position of each Node in an SVG sheet. */
  public String computePos(int shift, int depth, int radius, int height, int width, String rep) {
    /** Distance between nodes with the same depth is essentially the (width / (2^depth+1)). */
    Queue<Node<T>> q = new Queue<Node<T>>();
    if (this.root != null) q.enqueue(this.root);

    /** BFS and add nodes to SVG String representation. */
    while (!q.empty()) {
      Node<T> node = q.dequeue(), parent = node.getParent();

      /** Boundaries are (shift, width-shift) --> split into 2^n + 1 portions, where n is the depth.  */
      double segment = (width-2*shift)/(Math.pow(2, node.getDepth()) + 1);

      node.setSVG(shift + ((node.getPos()+1) * segment), (shift + 4 * node.getDepth() * radius));


      if (parent != null) {
        rep += String.format("<line x1=%.2f y1=%.2f x2=%.2f y2=%.2f style=\"stroke: black\" />", parent.getSVG().getX(), parent.getSVG().getY(), node.getSVG().getX(), node.getSVG().getY());
      } 

      if (node.getRight() != null) q.enqueue(node.getRight());
      if (node.getLeft() != null) q.enqueue(node.getLeft());
    }

    return rep;
  }

  public void svg(String fn) {
    int shift = 100, 
        depth = this.depth(), radius = 40, 
        height = shift + 6 * radius * depth,
        width = (int) (2 * shift + radius * Math.pow(2, depth));

    String rep = "", f = "";

    rep = this.computePos(shift, depth, radius, height, width, rep);

    Queue<Node<T>> q = new Queue<Node<T>>();
    if (this.root != null) q.enqueue(root);

    while (!q.empty()) {
      Node<T> node = q.dequeue();
      rep += String.format("<circle stroke-width=2 stroke=black fill=white cx=%.2f cy=%.2f r=%d />", node.getSVG().getX(), node.getSVG().getY(), radius);
      rep += String.format("<text x=%.2f y=%.2f font-family=monospace font-size=16px text-anchor=middle> %s </text>", node.getSVG().getX(), node.getSVG().getY() +radius/10, node.toString());

      if (node.getRight() != null) q.enqueue(node.getRight());
      if (node.getLeft() != null) q.enqueue(node.getLeft());
    }

    f = String.format("<svg width=%d height=%d> %s </svg>", width, height, rep);

    /** Writes to File. */
    try { PrintWriter writer = new PrintWriter(fn); writer.write(f); writer.close(); } catch (IOException e) { System.out.println("ERROR OCCURRED IN CREATING FILE."); e.printStackTrace(); }
  }

  public void delete(Node<T> node) {
    if (node.getParent() != null) {
      boolean R = (node.getParent().getRight() == node);
      if (R) {
        node.removeRight();
      } else {
        node.removeLeft();
      }
    } else {
      node = null;
    }
  }

  @Override
  public String toString() {
    int depth = 0;
    String r = "";
    Queue<Node<T>> q = new Queue<Node<T>>();
    if (this.root != null) q.enqueue(this.root);

    while (!q.empty()) {
      Node<T> node = q.dequeue();
      if (depth != node.getDepth()) { r += "\n"; depth = node.getDepth(); }
      r += node.toString() + "\t";

      if (node.getLeft() != null) q.enqueue(node.getLeft());
      if (node.getRight() != null) q.enqueue(node.getRight());
    }
    return r;
  }
};