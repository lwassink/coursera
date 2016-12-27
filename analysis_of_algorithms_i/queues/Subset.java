import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    int count = 0;
    RandomizedQueue<String> rq = new RandomizedQueue<String>();

    while (!StdIn.isEmpty()) {
      count++;
      String line = StdIn.readString();
      if (rq.size() < size) {
        rq.enqueue(line);
      } else {
        int chance = StdRandom.uniform(count);
        if (chance < size) {
          rq.dequeue();
          rq.enqueue(line);
        }
      }
    }

    for (int i = 0; i < size; i++)
      StdOut.println(rq.dequeue());
  }
}

