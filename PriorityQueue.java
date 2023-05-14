public class PriorityQueue<T extends Comparable<T>> {
  private int size = 0;
  private PriorityNode<T> head, tail;

  public PriorityQueue() {
    this.head = this.tail = null;
  }

  public PriorityQueue(T v) {
    this.head = this.tail = new PriorityNode<T>(v);
  }

  /** Default priority is 0. */
  public void enqueue(T v, int p) {
    PriorityNode<T> node = new PriorityNode<T>(v, p);
    if (this.head == null) {
      this.head = this.tail = node;
    } else if (this.head.getPriority() <= node.getPriority()) {
      node.setNext(this.head);
      this.head.setPrev(node);
      this.head = node;
    } else if (this.tail.getPriority() >= node.getPriority()) {
      node.setPrev(this.tail);
      this.tail.setNext(node);
      this.tail = node;
    } else {
      PriorityNode<T> curr = this.head;
      /** Iterate until the correct position is found. */
      while (curr != null && curr.getPriority() > node.getPriority()) {
        /** Gets the Node that goes behind the new Node. 
         * {a} <- {b} <- {c} 
         * {a} <- {b} <- {n} <- {c}
         */
        curr = curr.getNext();
      }
      node.setNext(curr);
      node.setPrev(curr.getPrev());
      curr.getPrev().setNext(node);
      curr.setPrev(node);
    }
    this.size++;
  }

  public T dequeue() {
    if (this.head == null) return null;
    this.size--;
    T value = this.head.getValue();
    this.head = this.head.getNext();
    if (this.size == 0) this.tail = this.head;
    return value;
  }

  public T front() {
    return (this.head != null ? this.head.getValue() : null);
  }

  public T back() {
    return (this.tail != null ? this.tail.getValue() : null);
  }

  public PriorityNode<T> getHead() {
    return this.head;
  }

  public PriorityNode<T> getTail() {
    return this.tail;
  }

  public boolean empty() {
    return (this.head == null);
  }

  @Override
  public String toString() {
    String r = "";
    PriorityNode<T> curr = this.head;
    for (int i = 0; i < this.size; i++) {
      r += curr.toString() + (i != this.size - 1 ? " <- " : "");
      curr = curr.getNext();
    }
    return r;
  }
}
