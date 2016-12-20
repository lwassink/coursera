import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   private int [] p;
   private int T;
   public PercolationStats(int n, int trials) {
       Percolation perc;
       p = Int[trials];
       t = trails;
       
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

   private int runTrial(int n) {
       Percolation perc = new Percolation(n);
       int cutoff = 0;
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
       return cutoff;
   }
   
   public static void main(String[] args) {
       int n = parseInt(args[0]);
       int T = parseInt(args[1]);
       PercolationStats perc = new PercolationStats(n, T);
       System.out.println("mean                    = %i", perc.mean());
       System.out.println("stddev                  = %i", perc.stddev());
       System.out.println("95% confidence interval = %i, %i", perc.confidenceLo(), perc.confidenceHi());
   }
}