/** Doubly Linked Node */
public class LinkedNode<T> {
  private T value;
  private LinkedNode<T> prev, next;

  public LinkedNode(T v) {
    this.value = v;
    this.prev = null;
    this.next = null;
  }

  public LinkedNode(T v, LinkedNode<T> p, LinkedNode<T> n) {
    this.value = v;
    this.prev = p;
    this.next = n;
  }

  public LinkedNode(T v, T p, T n) {
    this.value = v;
    this.prev = new LinkedNode<T>(p);
    this.next = new LinkedNode<T>(n);
  }

  public void setPrev(T v) {
    this.prev = new LinkedNode<T>(v);
  }

  public void setNext(T v) {
    this.next = new LinkedNode<T>(v);
  }

  public void setPrev(LinkedNode<T> p) {
    this.prev = p;
  }

  public void setNext(LinkedNode<T> n) {
    this.next = n;
  }

  public LinkedNode<T> getNext() {
    return this.next;
  }

  public LinkedNode<T> getPrev() {
    return this.prev;
  }

  public T getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return String.format("{%s}", this.value.toString());
  }
}
