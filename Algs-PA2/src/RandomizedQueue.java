import edu.princeton.cs.algs4.StdRandom;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;     // Array of items
    private int N=0;        // Number of items in array

    // Construct an empty randomized queue
    public RandomizedQueue(){
        arr = (Item[]) new Object[4];
    }

    // Is the queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // Return the number of items on the queue
    public int size(){
        return N;
    }

    // Add the item
    public void enqueue(Item item){
        if (N == arr.length) resize(arr.length*2);
        arr[N++] = item;
    }

    // Remove and return a random item
    public Item dequeue(){
        int index = StdRandom.uniform(0,N);
        Item item = arr[index];
        // arr[index] = null And what next?! exp->
        rearrange(index);
        N--;
        if (N-1 < arr.length/4) {resize(arr.length/2);}
        return item;
    }

    // Return (but do not remove) a random item
    public Item sample(){
        return arr[StdRandom.uniform(0,N)];
    }

    // Resize array
    private void resize(int size) {
        Item[] newarr = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            newarr[i] = arr[i];
        }
    }

    // Rearrange array
    private void rearrange(int index) {
        for (int i=index; i<N;i++){
            arr[index] = arr[index+1];
        }
    }

    // Return an independent iterator over items in random order
    public Iterator<Item> iterator(){return new ArrayIterator();}

    private class ArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {return i>0;}
        public void remove() throws java.lang.UnsupportedOperationException {throw new java.lang.UnsupportedOperationException();}
        public Item next() {
            // Under maintenance
            return arr[StdRandom.uniform(0,N)];
        }
    }

    // Clien for unit testing
    public static void main(String[] args){

    }
}
