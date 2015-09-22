import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by ABAmvrosov on 21.09.2015.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int x, y;
        x = k;
        RandomizedQueue<String> RQ = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++) {
            RQ.enqueue(StdIn.readString());
        }
        while (!StdIn.isEmpty()) {
            y = StdRandom.uniform(1, (x++) + 1);
            if (y == 1) {
                RQ.dequeue();
                RQ.enqueue(StdIn.readString());
            }
        }
        for (int i = 0; i < k; i++) { StdOut.println(RQ.dequeue()); }
    }
}
