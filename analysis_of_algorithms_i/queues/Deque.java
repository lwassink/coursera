import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private DoubleNode<Item> first;
  private DoubleNode<Item> last;
  private int size;

  public Deque() {
    size = 0;
    first = null;
    last = null;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null)
      throw new NullPointerException();

    if (isEmpty()) {
      first = new DoubleNode<Item>(item);
      last = first;
    } else {
      first = first.prepend(item);
    }
    size++;
  }

  public void addLast(Item item) {
    if (item == null)
      throw new NullPointerException();

    if (isEmpty()) {
      first = new DoubleNode<Item>(item);
      last = first;
    } else {
      last = last.append(item);
    }
    size++;
  }

  public Item removeFirst() {
    if (size <= 0)
      throw new java.util.NoSuchElementException();

    if (size == 1) {
      Item item = first.content;
      first = null;
      last = null;
      size--;
      return item;
    } else {
      Item item = first.content;
      first = first.next;
      first.prev = null;
      size--;
      return item;
    }
  }

  public Item removeLast() {
    if (size <= 0)
      throw new java.util.NoSuchElementException();

    if (size == 1) {
      Item item = last.content;
      first = null;
      last = null;
      size--;
      return item;
    } else {
      Item item = last.content;
      last = last.prev;
      last.next = null;
      size--;
      return item;
    }
  }

  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    private DoubleNode<Item> node;

    public DequeIterator() {
      this.node = first;
    }

    public boolean hasNext() {
      return this.node != null;
    }

    public Item next() {
      if (node == null)
        throw new java.util.NoSuchElementException();

      DoubleNode<Item> current = node;
      node = node.next;
      return current.content;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    Deque<Integer> d = new Deque<Integer>();
    d.addFirst(2);
    d.addLast(3);
    d.addFirst(-1);
    d.addLast(6);
    d.addFirst(8);
    d.addFirst(9);
    d.addLast(6);
    d.addLast(11);

    for (Integer node : d) {
      System.out.println(node);
    }
  }

  private class DoubleNode<Item> {
    public Item content;
    public DoubleNode<Item> next;
    public DoubleNode<Item> prev;

    public DoubleNode(Item item) {
      content = item;
      next = null;
      prev = null;
    }

    public DoubleNode<Item> append(Item item) {
      next = new DoubleNode<Item>(item);
      next.prev = this;
      return next;
    }

    public DoubleNode<Item> prepend(Item item) {
      prev = new DoubleNode<Item>(item);
      prev.next = this;
      return prev;
    }
  }
}

