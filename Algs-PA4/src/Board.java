import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int N; //dimension of board
    private int[][] myblocks;

    /*--------------------------------------------------
    construct a board from an N-by-N array of blocks
    (where blocks[i][j] = block in row i, column j)
    ---------------------------------------------------*/
    public Board(int[][] blocks) {
        this.N = blocks.length;
        myblocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                myblocks[i][j] = blocks[i][j];
            }
        }
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (myblocks[i][j] != N*i+j+1) { hamming++; }
            }
        }
        if (myblocks[N-1][N-1] == 0) { hamming--; }
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    //public int manhattan() {}

    // is this board the goal board?
    //public boolean isGoal() {}

    // a board that is obtained by exchanging any pair of blocks
    //public Board twin() {}

    // does this board equal y?
    //public boolean equals(Object y) {}

    // all neighboring boards
    //public Iterable<Board> neighbors() {}

    // string representation of this board (in the output format specified below)
    //public String toString() {}

    // unit tests (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);
        StdOut.print(initial.hamming());
    }
}