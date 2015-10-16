import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;

/************************************************
 * Solves level-order traversal quiz's using    *
 * added printLevelOrder() method               *
 ************************************************/
public class ExWeek4BST_Q1 {
    public static void main(String[] args) {
        BSTwithTraversalOrder<Integer, Double> bst = new BSTwithTraversalOrder<Integer, Double>();
        Integer[] arr = {71, 57, 74, 10, 61, 73, 88, 11, 63, 38, 65, 66};
        for (int i = 0; i < arr.length; i++) {
            bst.put(arr[i], (double) arr[i]);
        }
        bst.delete(88);
        bst.delete(74);
        bst.delete(57);
        bst.printLevelOrder();
    }
}
