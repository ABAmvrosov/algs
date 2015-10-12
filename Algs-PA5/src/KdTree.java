import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.TreeSet;

public class KdTree {
    private Node root;

    private class Node {
        private Point2D p;
        private RectHV rect = new RectHV(0, 0, 1, 1);
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
            if (tmp < 0) {
                node.leftTree = insert(node.leftTree, p);
                node.leftTree.coordChoose = false;
                node.leftTree.rect = new RectHV(node.rect.xmin(), node.p.x(), node.rect.ymin(), node.rect.ymax());
            }
            else if (tmp >= 0) {
                node.rightTree = insert(node.rightTree, p);
                node.rightTree.coordChoose = false;
                node.rightTree.rect = new RectHV(node.p.x(), node.rect.xmax(), node.rect.ymin(), node.rect.ymax());
            }
        }
        else {
            double tmp = p.y() - node.p.y();
            if (tmp < 0) {
                node.leftTree = insert(node.leftTree, p);
                node.leftTree.coordChoose = true;
                node.leftTree.rect = new RectHV(node.rect.xmin(), node.rect.xmax(), node.rect.ymin(), node.p.y());
            }
            else if (tmp >= 0) {
                node.rightTree = insert(node.rightTree, p);
                node.rightTree.coordChoose = true;
                node.rightTree.rect = new RectHV(node.rect.xmin(), node.rect.xmax(), node.p.y(), node.rect.ymax());
            }
        }
        node.N = 1 + size(node.leftTree) + size(node.rightTree);
        return node;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return get(p) != null;
    }

    private Point2D get(Point2D p) {
        return get(root, p);
    }

    private Point2D get(Node node, Point2D p) {
        if (node == null) { return null; }
        if (node.p.compareTo(p) == 0) {
            return node.p;
        }
        else {
            if (node.coordChoose) {
                double tmp = p.x() - node.p.x();
                if (tmp < 0) {
                    return get(node.leftTree, p);
                }
                else {
                    return get(node.rightTree, p);
                }
            }
            else {
                double tmp = p.y() - node.p.y();
                if (tmp < 0) {
                    return get(node.leftTree, p);
                }
                else {
                    return get(node.rightTree, p);
                }
            }
        }
    }

    // draw all points to standard draw
    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) { return; }
        if (node.coordChoose) {
            StdDraw.setPenColor(Color.BLACK);
            node.p.draw();
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        }
        else {
            StdDraw.setPenColor(Color.BLACK);
            node.p.draw();
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }
        draw(node.leftTree);
        draw(node.rightTree);
    }

    // all points that are inside the rectangle
    //public Iterable<Point2D> range(RectHV rect) {}

    // a nearest neighbor in the set to point p; null if the set is empty
    //public Point2D nearest(Point2D p) {}

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree kt = new KdTree();
        Point2D t1 = new Point2D (0.1, 0.1);
        Point2D t2 = new Point2D (0.2, 0.15);
        Point2D t3 = new Point2D (0.05, 0.2);
        Point2D t4 = new Point2D (0.06, 0.4);
        Point2D t5 = new Point2D (0.04, 0.3);
        Point2D t6 = new Point2D (0.21, 0.14);
        Point2D t7 = new Point2D (0.19, 0.16);
        Point2D t8 = new Point2D (0.29, 0.16);
        kt.insert(t1);
        kt.insert(t2);
        kt.insert(t3);
        kt.insert(t4);
        kt.insert(t5);
        kt.insert(t6);
        kt.insert(t7);
        StdOut.println(kt.contains(t1));
        StdOut.println(kt.contains(t2));
        StdOut.println(kt.contains(t3));
        StdOut.println(kt.contains(t4));
        StdOut.println(kt.contains(t5));
        StdOut.println(kt.contains(t6));
        StdOut.println(kt.contains(t7));
        StdOut.println(kt.contains(t8));
    }
}