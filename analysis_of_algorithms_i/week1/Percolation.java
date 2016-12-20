public class Percolation {
   private int size;
   private boolean [] open;
   private int [] id;
   private int [] classSize;

   public Percolation(int n) {
       size = n;
       open = new boolean [n * n];
       id = new int [n * n];
       classSize = new int [n * n];
       
       for (int i = 0; i < (n * n); i++) {
           open[i] = false;
           id[i] = i;
           classSize[i] = 1;
       }
   }

   public void open(int row, int col) {
       int pos = pos(row, col);
       int [] sur = surroundings(row, col);
       for (int i = 0; i < sur.length; i++) {
           union(sur[i], pos);
       }
       
       open[pos] = true;
   }

   public boolean isOpen(int row, int col) {
       return open[pos(row, col)];
   }

   public boolean isFull(int row, int col) {
       return !open[pos(row, col)];
   }

   public boolean percolates() {
       for (int col1 = 1; col1 <= size; col1++) {
           for (int col2 = 1; col2 <= size; col2++) {
               if (find(pos(col1, 1), pos(col2, size))) return true;
           }
       }
       return false;
   }
   
   private void union(int pos1, int pos2) {
       int root1 = root(pos1);
       int root2 = root(pos2);
       if (classSize[root1] > classSize[root2]) {
           id[root2] = root1;
           classSize[root1] += classSize[root2];
       } else {
           id[root1] = root2;
           classSize[root2] += classSize[root1];
       }
   }
   
   private boolean find(int pos1, int pos2) {
       return root(pos1) == root(pos2);
   }
   
   private int root(int pos) {
       int i = pos;
       while (id[i] != i) {
           id[i] = id[id[i]];
           i = id[i];
       }
       return i;
   }
   
   private int[] surroundings(int row, int col) {
       int [] sur = new int[4];
       sur[0] = pos(row + 1, col);
       sur[1] = pos(row - 1, col);
       sur[2] = pos(row, col - 1);
       sur[3] = pos(row, col + 1);
       
       int len = 0;
       for (int i = 0; i < 4; i++) {
           if (sur[i] >= 0 && sur[i] < 4) len++;
       }
       
       int [] filteredSur = new int[len];
       int j = 0;
       for (int i = 0; i < 4; i++) {
           if (sur[i] >= 0 && sur[i] < 4) filteredSur[j++] = sur[i];
       }
       return filteredSur;
   }
   
   private int pos(int row, int col) {
       return size * (row - 1) + (row - 1);
   }
   
   private int xCoord(int pos) {
       return (pos / size) + 1;
   }
   
   private int yCoord(int pos) {
       return (pos / size) + 1;
   }

   public static void main(String[] args) {}
}
