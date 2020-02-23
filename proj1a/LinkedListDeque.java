import java.util.LinkedList;

public class LinkedListDeque<T> {

  // nested class Node
  public class Node {
    private Node next;
    private Node prev;
    public T val;

    public Node(Node prev, T val, Node next) {
      this.prev = prev;
      this.val = val;
      this.next = next;
    }
  }

  // other member variables
  private Node sentinel;
  private int size;

  /** Creates an empty list deque */
  public LinkedListDeque() {
    sentinel = new Node(null, null, null);
    sentinel.prev = sentinel;
    sentinel.next = sentinel;
    size = 0;
  }

  /** Creates a deep copy of the given deque */
  public LinkedListDeque(LinkedListDeque other) {
    this();

    Node ptrOther = other.sentinel.next;
    while (ptrOther != other.sentinel) {
      this.addLast(ptrOther.val);
      ptrOther = ptrOther.next;
    }

  }

  private T getRecursiveHelper(int index, Node node) {
    if (node == sentinel) return null;
    if (index == 0) return node.val;
    return getRecursiveHelper(index - 1, node.next);
  }

  /** Same as get, but has to use recursion */
  public T getRecursive(int index) {
    return getRecursiveHelper(index, sentinel.next);
  }

  /** Adds an item of type T to the front of the deque */
  public void addFirst(T item) {
    Node node = new Node(sentinel, item, sentinel.next);
    sentinel.next.prev = node;
    sentinel.next = node;
    size += 1;
  }

  /** Adds an item of type T to the back of the deque. **/
  public void addLast(T item) {
    Node node = new Node(sentinel.prev, item, sentinel);
    sentinel.prev.next = node;
    sentinel.prev = node;
    size += 1;
  }

  /** Returns true if deque is empty, false otherwise. */
  public boolean isEmpty() {
    return (size <= 0);
  }

  /** Returns the number of items in the deque. */
  public int size() {
    return size;
  }

  /** Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line. */
  public void printDeque() {
    Node curr = sentinel.next;
    while (curr != sentinel) {
      System.out.print( "-> " + curr.val);
      curr = curr.next;
    }
    System.out.println();
  }

  /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
  public T removeFirst() {
    if (isEmpty()) return null;
    T val = sentinel.next.val;
    sentinel.next = sentinel.next.next;
    sentinel.next.prev = sentinel;
    size -= 1;
    return val;
  }

  /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
  public T removeLast() {
    if (isEmpty()) return null;
    T val = sentinel.prev.val;
    sentinel.prev = sentinel.prev.prev;
    sentinel.prev.next = sentinel;
    size -= 1;
    return val;
  }

  /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
   * If no such item exists, returns null. Must not alter the deque!
   */
  public T get(int index) {
    if (index > size || isEmpty()) return null;
    Node curr = sentinel.next;
    while (index > 0) {
      curr = curr.next;
      index -= 1;
    }
    return curr.val;
  }

}
