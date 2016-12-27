import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Percolation {
  private int size;
  private boolean [] open;
  private WeightedQuickUnionUF uf;

  public Percolation(int n) {
    if (n < 1) throw new IllegalArgumentException();
    size = n;
    open = new boolean [n * n];
    uf = new WeightedQuickUnionUF((n * n) + 2);

    for (int i = 0; i < n; i++)
      uf.union(n * n, i);
    for (int i = n * (n - 1); i < (n * n); i++)
      uf.union((n * n) + 1, i);

    for (int i = 0; i < (n * n); i++) {
      open[i] = false;
    }
  }

  public void open(int row, int col) {
    if (row < 1 || row > size) throw new IndexOutOfBoundsException();
    if (col < 1 || col > size) throw new IndexOutOfBoundsException();

    int pos = pos(row, col);
    int [] sur = surroundings(row, col);
    for (int i = 0; i < sur.length; i++) {
      if (isOpen(xCoord(sur[i]), yCoord(sur[i])))
        uf.union(sur[i], pos);
    }

    open[pos] = true;
  }

  public boolean isOpen(int row, int col) {
    if (row < 1 || row > size) throw new IndexOutOfBoundsException();
    if (col < 1 || col > size) throw new IndexOutOfBoundsException();

    return open[pos(row, col)];
  }

  public boolean isFull(int row, int col) {
    return !open[pos(row, col)];
  }

  public boolean percolates() {
    return uf.connected(size * size, (size * size) + 1);
  }

  private int[] surroundings(int row, int col) {
    int [] sur = new int[4];
    int len = 0;

    if (row < size)
      sur[len++] = pos(row + 1, col);
    if (row > 1)
      sur[len++] = pos(row - 1, col);
    if (col < size)
      sur[len++] = pos(row, col + 1);
    if (col > 1)
      sur[len++] = pos(row, col - 1);

    int [] filteredSur = new int[len];
    for (int i = 0; i < len; i++) {
      filteredSur[i] = sur[i];
    }

    return filteredSur;
  }

  private int pos(int row, int col) {
    return size * (row - 1) + (col - 1);
  }

  private int xCoord(int pos) {
    return (pos / size) + 1;
  }

  private int yCoord(int pos) {
    return (pos % size) + 1;
  }

  public static void main(String[] args) {
    Percolation perc = new Percolation(3);
    StdOut.println(perc.percolates());
    perc.open(1,1);
    StdOut.println(perc.percolates());
    perc.open(3,1);
    StdOut.println(perc.percolates());
    perc.open(2,1);
    StdOut.println(perc.percolates());
  }
}

