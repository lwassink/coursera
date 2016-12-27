import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
  private double [] p;
  private int T;
  public PercolationStats(int n, int trials) {
    Percolation perc;
    p = new double[trials];
    T = trials;

    for (int i = 0; i < T; i++) {
      p[i] = runTrial(n);
    }
  }

  public double mean() {
    return StdStats.mean(p);
  }

  public double stddev() {
    return StdStats.stddev(p);
  }

  public double confidenceLo() {
    return this.mean() - (1.96 * this.stddev() / Math.sqrt(T));
  }

  public double confidenceHi() {
    return this.mean() + (1.96 * this.stddev() / Math.sqrt(T));
  }

  private double runTrial(int n) {
    Percolation perc = new Percolation(n);
    double cutoff = 0;
    while (!perc.percolates()) {
      while (true) {
        int X = StdRandom.uniform(n) + 1;
        int Y = StdRandom.uniform(n) + 1;
        if (perc.isFull(X, Y)) {
          perc.open(X, Y);
          cutoff++;
          break;
        }
      }
    }
    return cutoff / (n * n);
  }

  public static void main(String[] args) {
    int n = StdIn.readInt();
    int T = StdIn.readInt();
    StdOut.println(n);
    StdOut.println(T);
    PercolationStats perc = new PercolationStats(n, T);
    StdOut.print("mean                    = ");
    StdOut.println(perc.mean());
    StdOut.print("stddev                  = ");
    StdOut.println(perc.stddev());
    StdOut.print("95% confidence interval = ");
    StdOut.print(perc.confidenceLo());
    StdOut.print(", ");
    StdOut.println(perc.confidenceHi());
  }
}
