import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int N; //dimension of board
    private int[][] myblocks;
    private int blank_i;
    private int blank_j;

    /*--------------------------------------------------
    construct a board from an N-by-N array of blocks
    (where blocks[i][j] = block in row i, column j)
    ---------------------------------------------------*/
    public Board(int[][] blocks) {
        this.N = blocks.length;
        myblocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) { blank_i = i; blank_j = j; }
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
    public int manhattan() {
        int manhattan = 0;
        int dif, draw, dcol;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (myblocks[i][j] == 0) {
                    dif = Math.abs((N*i+j+1) - N*N);
                    draw = dif / N;
                    dcol = dif % N;
                    manhattan += (draw + dcol);
                }
                if (myblocks[i][j] != 0 && myblocks[i][j] != N*i+j+1) {
                    dif = Math.abs((N*i+j+1) - myblocks[i][j]);
                    draw = dif / N;
                    dcol = dif % N;
                    manhattan += (draw + dcol);
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (myblocks[i][j] == 0 && (N*i+j+1) != N*N) {return false; }
                if (myblocks[i][j] != 0 && myblocks[i][j] != N*i+j+1) { return false; }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board twin = new Board(this.myblocks);
        int temp;
        int i = 0;
        int j = 0;
        if (twin.myblocks[i][j] != 0 && twin.myblocks[i][j+1] != 0) {
            temp = twin.myblocks[i][j];
            twin.myblocks[i][j] = twin.myblocks[i][j+1];
            twin.myblocks[i][j+1] = temp;
        }
        else {
            temp = twin.myblocks[i+1][j];
            twin.myblocks[i+1][j] = twin.myblocks[i+1][j+1];
            twin.myblocks[i+1][j+1] = temp;
        }
        return twin;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) { return true; }
        if (y == null) { return false; }
        if (y.getClass() != this.getClass()) { return false; }
        Board that = (Board) y;
        if (that.N != this.N) { return false; }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.myblocks[i][j] != that.myblocks[i][j]) { return false; }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<Board>();
        if (blank_i != 0) {
            Board tmp = new Board(this.myblocks);
            tmp.myblocks[blank_i][blank_j] = tmp.myblocks[blank_i - 1][blank_j];
            tmp.myblocks[blank_i - 1][blank_j] = 0;
            stack.push(tmp);
        }
        if (blank_i != 2) {
            Board tmp = new Board(this.myblocks);
            tmp.myblocks[blank_i][blank_j] = tmp.myblocks[blank_i + 1][blank_j];
            tmp.myblocks[blank_i + 1][blank_j] = 0;
            stack.push(tmp);
        }
        if (blank_j != 0) {
            Board tmp = new Board(this.myblocks);
            tmp.myblocks[blank_i][blank_j] = tmp.myblocks[blank_i][blank_j - 1];
            tmp.myblocks[blank_i][blank_j - 1] = 0;
            stack.push(tmp);
        }
        if (blank_j != 2) {
            Board tmp = new Board(this.myblocks);
            tmp.myblocks[blank_i][blank_j] = tmp.myblocks[blank_i][blank_j + 1];
            tmp.myblocks[blank_i][blank_j + 1] = 0;
            stack.push(tmp);
        }
        return stack;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", myblocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        /*
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        In in2 = new In(args[1]);
        int N2 = in2.readInt();
        int[][] blocks2 = new int[N2][N2];
        for (int i = 0; i < N2; i++) {
            for (int j = 0; j < N2; j++) {
                blocks2[i][j] = in2.readInt();
            }
        }
        Board initial2 = new Board(blocks2);

        //StdOut.println(initial.hamming());
        //StdOut.println(initial.manhattan());
        //StdOut.println(initial.isGoal());
        StdOut.println("initial \n" + initial.toString());
        StdOut.println("neighbors \n" + initial.neighbors());
        StdOut.println("twin " + initial.twin());
        //StdOut.println(initial.blank_i + " " + initial.blank_j);
        //StdOut.println(initial2.toString());
        //StdOut.println(initial.equals(initial2));
        */
    }
}