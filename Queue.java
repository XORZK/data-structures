/** First In First Out Queue Data Structure. */
public class Queue<T extends Comparable<T>> {
  private int size = 0;
  private LinkedNode<T> head, tail;

  public Queue() {
    this.head = null;
    this.tail = null;
  }

  public Queue(T v) {
    this.head = this.tail = new LinkedNode<T>(v);
  }

  public void enqueue(T v) {
    LinkedNode<T> node = new LinkedNode<T>(v);
    this.size++;
    if (this.head == null) {
      this.head = this.tail = node;
    } else {
      this.tail.setNext(node);
      node.setPrev(this.tail);
      this.tail = node;
    }
  }

  public T dequeue() {
    if (this.head == null) return null;

    this.size--;
    T value = this.head.getValue();
    this.head = this.head.getNext();
    return value;
  }

  public int size() {
    return this.size;
  }

  public T back() {
    return (this.tail != null ? this.tail.getValue() : null);
  }

  public T front() {
    return (this.head != null ? this.head.getValue() : null);
  }

  public boolean empty() {
    return (this.head == null);
  }

  @Override
  public String toString() {
    LinkedNode<T> curr = this.head;
    String r = "";

    for (int i = 0; i < this.size; i++) {
      r += curr.toString() + (i != this.size-1 ? "<-" : "");
      curr = curr.getNext();
    }
    return r;
  }
}