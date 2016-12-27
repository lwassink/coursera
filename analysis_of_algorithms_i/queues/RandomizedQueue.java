import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private int idx;
  private int capacity;
  private Item [] items;

  public RandomizedQueue() {
    idx = 0;
    capacity = 4;
    items = (Item[]) new Object[4];
  }

  public boolean isEmpty() {
    return idx == 0;
  }

  public int size() {
    return idx;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }

    items[idx++] = item;
    if (idx == capacity) grow();
  }

  public Item dequeue() {
    if (idx <= 0)
      throw new java.util.NoSuchElementException();

    int randIndex = StdRandom.uniform(idx);

    Item el = items[randIndex];
    items[randIndex] = items[--idx];
    items[idx] = null;

    if (idx < capacity / 4) shrink();

    return el;
  }

  public Item sample() {
    if (idx <= 0)
      throw new java.util.NoSuchElementException();

    return items[StdRandom.uniform(idx)];
  }

  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private int [] shuffledIndices;
    private int iterIdx;

    public RandomizedQueueIterator() {
      shuffledIndices = new int[idx];

      for (int i = 0; i < idx; i++) {
        int randomIndex = StdRandom.uniform(i + 1);
        shuffledIndices[i] = shuffledIndices[randomIndex];
        shuffledIndices[randomIndex] = i;
      }

      iterIdx = 0;
    }

    public boolean hasNext() {
      return iterIdx < idx;
    }

    public Item next() {
      if (iterIdx >= idx)
        throw new java.util.NoSuchElementException();

      return items[shuffledIndices[iterIdx++]];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
  }

  private void grow() {
    Item [] grownItems = (Item[]) new Object[2 * capacity];
    for (int i = 0; i < capacity; i++)
      grownItems[i] = items[i];
    capacity *= 2;
    items = grownItems;
  }

  private void shrink() {
    Item [] shrunkItems = (Item[]) new Object[2 * capacity];
    capacity = capacity / 2;
    for (int i = 0; i < capacity; i++)
      shrunkItems[i] = items[i];
    items = shrunkItems;
  }
}

