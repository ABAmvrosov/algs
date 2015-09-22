import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;     // Array of items
    private int N = 0;        // Number of items in array

    // Construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[4];
    }

    private void validate(Item item) {
        if (item == null) { throw new java.lang.NullPointerException(); }
    }

    // Is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // Return the number of items on the queue
    public int size() {
        return N;
    }

    // Add the item
    public void enqueue(Item item) {
        validate(item);
        if (N == arr.length) resize(arr.length*2);
        arr[N++] = item;
    }

    // Remove and return a random item
    public Item dequeue() {
        if (isEmpty()) { throw new java.util.NoSuchElementException(); }
        int index = StdRandom.uniform(0, N);
        Item item = arr[index];
        arr[index] = arr[N-1];
        arr[N-1] = null;
        N--;
        if (arr.length > 4 && N-1 < arr.length/4) { resize(arr.length/2); }
        return item;
    }

    // Return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) { throw new java.util.NoSuchElementException(); }
        return arr[StdRandom.uniform(0, N)];
    }

    // Resize array
    private void resize(int size) {
        Item[] newarr = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            newarr[i] = arr[i];
        }
        arr = newarr;
    }

    // Return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new ArrayIterator(); }

    private class ArrayIterator implements Iterator<Item> {
        private int[] ids;
        private int a = N;
        ArrayIterator() {
            ids = new int[N];
            for (int i = 0; i < N; i++) { ids[i] = i; }
            StdRandom.shuffle(ids);
            }

        public boolean hasNext() { return a > 0; }
        public void remove()  { throw new java.lang.UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) { throw new java.util.NoSuchElementException(); }
            return arr[ids[--a]];
        }
    }

    // Clien for unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> RQ = new  RandomizedQueue<Integer>();
        // Adding in new RandomizedQueue
        for (int i = 0; i < 10; i++) { RQ.enqueue(i); }
        StdOut.println("Size of RandomizedQueue is " + RQ.size());
        StdOut.println("Is empty? " + RQ.isEmpty());
        StdOut.print("Items in RandomizedQueue: ");
        for (Integer x : RQ) { StdOut.print(x+" "); }
        // Removing items from RandomizedQueue
        for (int i = 0; i < 10; i++) { RQ.dequeue(); }
        StdOut.println();
        StdOut.println("Size of RandomizedQueue is " + RQ.size());
        StdOut.println("Is empty? " + RQ.isEmpty());
        // Adding items again
        RQ.enqueue(1);
        RQ.enqueue(2);
        RQ.enqueue(3);
        StdOut.println("Size of RandomizedQueue is " + RQ.size());
        StdOut.println("Is empty? " + RQ.isEmpty());
        StdOut.print("Items in RandomizedQueue: ");
        for (Integer x : RQ) { StdOut.print(x+" "); }
    }
}
