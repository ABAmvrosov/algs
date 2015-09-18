import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    private int N;      // size
    private Node first; // Item in Front of deque
    private Node last;  // Item in End of deque

    // Inner class
    private class Node {
        Item item;
        Node next;
        Node prev;
        Node(Node prev, Item item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public Deque() {} // construct an empty deque

    // Validating item for Null. PA2 Requirements.
    private void validate(Item item) throws java.lang.NullPointerException {
        if (item == null) {throw new java.lang.NullPointerException();}
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // Return the number of items on the deque
    public int size() {return N;}

    // Add the item to the Front
    public void addFirst(Item item){
        validate(item);
        Node oldfirst = first;
        Node temp = new Node(null,item,oldfirst);
        first = temp;
        if (oldfirst==null) {last=first;}
        else oldfirst.prev = first;
        N++;

    }

    // Add the item to the End
    public void addLast(Item item){
        validate(item);
        Node oldlast = last;
        Node temp = new Node(oldlast,item,null);
        last = temp;
        if (oldlast==null) {first=last;}
        else oldlast.next=last;
        N++;
    }

    // Remove and return the item from the front
    public Item removeFirst() throws java.util.NoSuchElementException {
        if (isEmpty()) {throw new java.util.NoSuchElementException();}
        Item item = first.item;
        Node next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        N--;
        return item;
    }

    // Remove and return the item from the end
    public Item removeLast() throws java.util.NoSuchElementException {
        if (isEmpty()) {throw new java.util.NoSuchElementException();}
        Item item = last.item;
        Node prev = last.prev;
        last.item = null;
        last.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        N--;
        return item;
    }

    // Return an iterator over items in order from front to end
    public Iterator<Item> iterator(){return new ListIterator();}

    // Implementation of ListIterator
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {return current != null;}
        public Item next() throws java.util.NoSuchElementException {
            if (isEmpty()) {throw new java.util.NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() throws java.lang.UnsupportedOperationException {throw new java.lang.UnsupportedOperationException();}
    }

    // Client for unit testing
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        StdOut.println("Size of deque is " + deque.size());
        StdOut.println("Is empty? " + deque.isEmpty());
        StdOut.print("Items in deque: ");
        for (Integer x : deque) {StdOut.print(x+" ");}
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        StdOut.println();
        StdOut.println("Size of deque is " + deque.size());
        StdOut.println("Is empty? " + deque.isEmpty());

    }
}
