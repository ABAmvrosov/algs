import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Objects;

public class Solver {

    private class Node implements Comparator<Node> {
        private Board board;
        private int moves;
        private int manhattan;
        private Node prev;
        public Node(Board x, int y, Node z) {
            this.board = x;
            this.moves = y;
            this.prev = z;
            manhattan = board.manhattan();
        }
        public int compare (Node x, Node y) {
            return (x.moves + x.manhattan) - (y.moves + y.manhattan);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<Node> pq = new MinPQ<Node>();
        Node s_node = new Node(initial, 0, null);
        pq.insert(s_node);
        while (!s_node.board.isGoal()) {
            s_node = pq.delMin();
            Node tmp = s_node;
            while (s_node.board.neighbors().iterator().hasNext()) {
                pq.insert(new Node(s_node.board.neighbors().iterator().next(),++s_node.moves,s_node));
            }
        }
    }

    // is the initial board solvable?
    //public boolean isSolvable() {}

    // min number of moves to solve initial board; -1 if unsolvable
    //public int moves() {}

    // sequence of boards in a shortest solution; null if unsolvable
    //public Iterable<Board> solution() {}

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);
        /*
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        */
    }
}