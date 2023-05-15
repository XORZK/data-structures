/** First In Last Out Data Structure */
public class Stack<T extends Comparable<T>> {
	private int size = 0;
	private LinkedNode<T> head, tail;

	public Stack() {
		this.head = this.tail = null;
	}

	public Stack(T v) {
		this.head = this.tail = new LinkedNode<T>(v);

		if (this.head != null) {
			this.size++;
		}
	}

	public void push(T v) {
		LinkedNode<T> node = new LinkedNode<T>(v);
		this.size++;

		if (this.head == null) {
			this.head = node;
		} else {
			node.setNext(this.head);
			this.head.setPrev(node);
			this.head = node;
		}
	}

	public T pop() {
		if (this.head == null) {
			return null;
		}

		T value = this.head.getValue();

		this.head = this.head.getNext();

		return value;
	}

	public int size() {
		return this.size;
	}

	public boolean empty() {
		return (this.head == null);
	}

	@Override
	public String toString() {
		LinkedNode<T> curr = this.head;

		String rep = "";

		for (int i = 0; i < this.size; i++) {
			rep += curr.toString() + (i != this.size - 1 ? "<-" : "");
			curr = curr.getNext();
		}

		return rep;
	}
}
