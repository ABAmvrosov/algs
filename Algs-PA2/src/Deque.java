import java.util.Iterator;
import java.util.Objects;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private class Node {
        Item item;
        Node next;
    }
    public Deque() {
    }
    public boolean isEmpty() {
        return first == null;
    }                 // is the deque empty? {
    public int size()                        // return the number of items on the deque
    public void addFirst(Item item)          // add the item to the front
    public void addLast(Item item)           // add the item to the end
    public Item removeFirst()                // remove and return the item from the front
    public Item removeLast()                 // remove and return the item from the end
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    public static void main(String[] args)   // unit testing
}
