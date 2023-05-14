public class LinkedList<T extends Comparable<T>> {
  private int size = 0;
  private LinkedNode<T> head, tail;

  LinkedList() {
    this.head = null;
    this.tail = null;
  }

  LinkedList(T v) {
    this.head = new LinkedNode<T>(v);
    this.tail = this.head;
  }

  public void add(T v) {
    LinkedNode<T> node = new LinkedNode<T>(v);
    if (this.tail == null && this.head == null) {
      this.head = this.tail = node;
    } else {
      this.tail.setNext(node);
      node.setPrev(this.tail);
      this.tail = node;
    }
    this.size++;
  }

  public int size() {
    if (this.head == null) {
      return 0;
    } 

    int size = 0;
    LinkedNode<T> curr = this.head;

    while (curr != null) {
      curr = curr.getNext();
      ++size;
    }
    
    return size;
  }

  public LinkedNode<T> getNode(int index) {
    assert(index >= 0 && index < this.size);
    boolean forward = (index < (this.size - index - 1));
    LinkedNode<T> curr = (forward ? this.head : this.tail);

    if (forward) {
      for (int i = 0; i < index; i++) curr = curr.getNext();
    } else {
      for (int i = this.size-1; i > index; i--) curr = curr.getPrev();
    }

    return curr;
  }

  public T get(int index) {
    return this.getNode(index).getValue();
  }

  public void remove(int index) {
    if (index == 0) {
      this.head = this.head.getNext();
      if (this.size == 1) this.tail = null;
    }
    else if (index == size-1) {
      this.tail = this.tail.getPrev();
      if (this.size == 1) this.head = null;
    }
    else {
      LinkedNode<T> removed = this.getNode(index);
      removed.getPrev().setNext(removed.getNext());
      removed.getNext().setPrev(removed.getPrev());
    }
    size--;
  }

  public LinkedNode<T> getTail() {
    return this.tail;
  }

  public LinkedNode<T> getHead() {
    return this.head;
  }

  @Override
  public String toString() {
    LinkedNode<T> curr = this.head;
    String r = "";
    for (int i = 0; i < this.size; i++) {
      r += curr.toString() + (i != this.size-1 ? "<->" : "");
      curr = curr.getNext();
    }
    return r;
  }
}
