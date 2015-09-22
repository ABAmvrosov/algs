import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ABAmvrosov on 21.09.2015.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> RQ = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
                RQ.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) { StdOut.println(RQ.dequeue()); }
    }
}
