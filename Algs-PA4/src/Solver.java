import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.Stack;

public class Solver {
    private Node searchNode;

    private class Node implements Comparable<Node> {
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
        public int compareTo(Node y) {
            return (this.moves + this.manhattan) - (y.moves + y.manhattan);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> pqTwin = new MinPQ<Node>();
        searchNode = new Node(initial, 0, null);
        Node searchNodeTwin = new Node(initial.twin(), 0, null);
        pq.insert(searchNode);
        pqTwin.insert(searchNodeTwin);
        while (!searchNode.board.isGoal() && !searchNodeTwin.board.isGoal()) {

            searchNode = pq.delMin();
            Iterator<Board> i = searchNode.board.neighbors().iterator();
            while (!searchNode.board.isGoal() & i.hasNext()) {
                int moves = searchNode.moves;
                Board neighbor = i.next();
                if (searchNode.prev != null && neighbor.equals(searchNode.prev.board)) { continue; }
                pq.insert(new Node(neighbor, ++moves, searchNode));
            }

            searchNodeTwin = pqTwin.delMin();
            Iterator<Board> iTwin = searchNodeTwin.board.neighbors().iterator();
            while (!searchNodeTwin.board.isGoal() & iTwin.hasNext()) {
                int moves = searchNodeTwin.moves;
                Board neighborTwin = iTwin.next();
                if (searchNodeTwin.prev != null && neighborTwin.equals(searchNodeTwin.prev.board)) { continue; }
                pqTwin.insert(new Node(neighborTwin, ++moves, searchNode));
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return searchNode.board.isGoal();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!searchNode.board.isGoal()) { return -1; }
        return searchNode.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Node tmp = searchNode;
        if (tmp.board.isGoal()) {
            Stack<Board> st = new Stack<Board>();
            st.push(tmp.board);
            while (tmp.prev != null) {
                tmp = tmp.prev;
                st.push(tmp.board);
            }
            Stack<Board> stReversed = new Stack<Board>();
            while (!st.empty()) {
                stReversed.push(st.pop());
            }
            return stReversed;
        }
        return null;
    }

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
        StdOut.println(solver.isSolvable());
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        StdOut.println("Minimum number of moves = " + solver.moves());
    }
}