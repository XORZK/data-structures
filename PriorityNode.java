public class PriorityNode<T extends Comparable<T>> {
  private T value;
  private int priority = 0;
  private PriorityNode<T> prev, next;

  public PriorityNode(T v) {
    this.value = v;
    this.prev = null;
    this.next = null;
  }

  public PriorityNode(T v, int p) {
    this.value = v;
    this.priority = p;
    this.prev = null;
    this.next = null;
  }

  public PriorityNode(T v, PriorityNode<T> p, PriorityNode<T> n) {
    this.value = v;
    this.prev = p;
    this.next = n;
  }

  public PriorityNode(T v, T p, T n) {
    this.value = v;
    this.prev = new PriorityNode<T>(p);
    this.next = new PriorityNode<T>(n);
  }

  public void setPrev(T v) {
    this.prev = new PriorityNode<T>(v);
  }

  public void setNext(T v) {
    this.next = new PriorityNode<T>(v);
  }

  public void setPrev(PriorityNode<T> p) {
    this.prev = p;
  }

  public void setNext(PriorityNode<T> n) {
    this.next = n;
  }

  public void setPriority(int p) {
    this.priority = p;
  }

  public void setValue(T v) {
    this.value = v;
  }

  public int getPriority() {
    return this.priority;
  }

  public T getValue() {
    return this.value;
  }

  public PriorityNode<T> getPrev() {
    return this.prev;
  }

  public PriorityNode<T> getNext() {
    return this.next;
  }

  @Override
  public String toString() {
    return String.format("{%s, %d}", this.value.toString(), this.priority);
  }
}