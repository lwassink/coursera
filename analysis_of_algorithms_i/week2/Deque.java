public class Deque<Item> implements Iterable<Item> {
    private DoubleNode<Item> first;
    private DoubleNode<Item> last;
    private Int size;
    
    public Deque() {
        size = 0;
    }
   public boolean isEmpty() {
       return size == 0;
   }
   
   public int size() {
       return size;
   }
   
   public void addFirst(Item item) {
       if (isEmpty()) {
           first = item;
           last = item;
       } else {
           link(item, first);
           first = item;
       }
       size++;
   }
   
   public void addLast(Item item) {
       if (isEmpty()) {
           first = item;
           last = item;
       } else {
           link(last, item);
           last = item;
       }
       size++;
   }
   
   public Item removeFirst() {
       if (size <= 0) {
           throw new UnsupportedOperationException;
       } else if (size == 1) {
           Item item = first.content;
           first = null;
           last = null;
           return item;
       } else {
           Item item = first.content;
           first = first.next;
           return item;
       }
   }
   
   public Item removeLast() {
       if (size <= 0) {
           throw new UnsupportedOperationException;
       } else if (size == 1) {
           Item item = last.content;
           first = null;
           last = null;
           return item;
       } else {
           Item item = last.content;
           last = last.prev;
           return item;
       }
   }
   
   public Iterator<Item> iterator() {
   }
       
   public static void main(String[] args) {}
       
   private void link(DoubleNode<Item> a, DoubleNode<Item> b) {
       a.next = b;
       b.previous = a;
   }
   
   private class DoubleNode<Item> {
       public Item content;
       public DoubleNode<Item> next;
       4public DoubleNode<Item> prev;
   }
}