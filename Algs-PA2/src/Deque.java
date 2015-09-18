import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    private int N; // size
    private Node first;
    private Node last;

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

    private void validate(Item item) throws java.lang.NullPointerException {
        if (item == null) {throw new java.lang.NullPointerException();}
    }

    public boolean isEmpty() {
        return first == null;
    }                 // is the deque empty?

    public int size() {return N;}                        // return the number of items on the deque

    public void addFirst(Item item){
        validate(item);
        Node oldfirst = first;
        Node newNode = new Node(null,item,oldfirst);
        first = newNode;
        if (oldfirst==null) {last=first;}
        else oldfirst.prev = first;
        N++;

    }          // add the item to the front

    public void addLast(Item item){
        validate(item);
        Node oldlast = last;
        Node newNode = new Node(oldlast,item,null);
        last = newNode;
        if (oldlast==null) {first=last;}
        else oldlast.next=last;
        N++;
    }           // add the item to the end

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
    }                // remove and return the item from the front

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
    }                 // remove and return the item from the end

    public Iterator<Item> iterator(){return new ListIterator();} // return an iterator over items in order from front to end

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

    }   // unit testing
}
