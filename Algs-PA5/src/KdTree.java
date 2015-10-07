import edu.princeton.cs.algs4.*;

import java.util.Iterator;
import java.util.TreeSet;

public class KdTree {
    private Node root;

    private class Node {
        private Point2D p;
        private RectHV rect;
        private Node leftTree, rightTree;
        private int N;
        private boolean coordChoose = true; // in true case - compare by X, otherwise Y

        Node(Point2D point, int N) {
            this.p = point;
            this.N = N;
        }
    }

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() { return size() == 0; }

    // number of points in the set
    public int size() { return size(root); }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.N;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        root = insert(root, p);
    }

    private Node insert(Node node, Point2D p) {
        if (node == null) return new Node(p, 1);
        if (node.coordChoose) {
            double tmp = p.x() - node.p.x();
            if (tmp < 0) node.leftTree = insert(node.leftTree, p);
            else if (tmp >= 0) node.rightTree = insert(node.rightTree, p);
            node.N = 1 + size(node.leftTree) + size(node.rightTree);
            return node;
        }

    }

    // does the set contain point p?
    public boolean contains(Point2D p) {

    }

    /*
    // draw all points to standard draw
    public void draw() {
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
    }


    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
    }
    */
    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}