import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class KdTree {
    private Node root;
    private Point2D nearestP;
    private double dist;

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
        if (this.contains(p)) { return; }
        if (root == null) {
            root = new Node(p, 1);
            root.rect = new RectHV(0, 0, 1, 1);
        }
        else { root = insert(root, p); }
    }

    private Node insert(Node node, Point2D p) {
        if (node == null) return new Node(p, 1);
        if (node.coordChoose) {
            double tmp = p.x() - node.p.x();
            if (tmp < 0) {
                node.leftTree = insert(node.leftTree, p);
                node.leftTree.coordChoose = false;
                if (node.leftTree.rect == null) {
                    node.leftTree.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
                }
            }
            else if (tmp >= 0) {
                node.rightTree = insert(node.rightTree, p);
                node.rightTree.coordChoose = false;
                if (node.rightTree.rect == null) {
                    node.rightTree.rect = new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                }
            }
            } else {
            double tmp = p.y() - node.p.y();
            if (tmp < 0) {
                node.leftTree = insert(node.leftTree, p);
                node.leftTree.coordChoose = true;
                if (node.leftTree.rect == null) {
                    node.leftTree.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
                }
            }
            else if (tmp >= 0) {
                node.rightTree = insert(node.rightTree, p);
                node.rightTree.coordChoose = true;
                if (node.rightTree.rect == null) {
                    node.rightTree.rect = new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
                }
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
            StdDraw.setPenColor(StdDraw.BLACK);
            node.p.draw();
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLACK);
            node.p.draw();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }
        draw(node.leftTree);
        draw(node.rightTree);
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> st = new Stack<Point2D>();
        range(root, rect, st);
        return st;
    }

    private void range(Node node, RectHV rect, Stack<Point2D> st) {
        if (node == null) { return; }
        if (rect.contains(node.p)) { st.push(node.p); }
        if (node.rect.intersects(rect)) {
            range(node.leftTree, rect, st);
            range(node.rightTree, rect, st);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (root == null) { return null; }
        dist = root.p.distanceTo(p);
        nearestP = root.p;
        nearest(root, p);
        return nearestP;
    }

    private void nearest(Node node, Point2D p) {
        if (node == null) { return; }
        if (node.leftTree != null && node.leftTree.rect.contains(p)) {
            if (node.leftTree.rect.distanceTo(p) < dist) {
                if (node.leftTree.p.distanceTo(p) < dist) {
                    dist = node.leftTree.p.distanceTo(p);
                    nearestP = node.leftTree.p;
                }
                nearest(node.leftTree, p);
            }
            if (node.rightTree != null && node.rightTree.rect.distanceTo(p) < dist) {
                if (node.rightTree.p.distanceTo(p) < dist) {
                    dist = node.rightTree.p.distanceTo(p);
                    nearestP = node.rightTree.p;
                }
                nearest(node.rightTree, p);
            }
        }
        else {
            if (node.rightTree != null && node.rightTree.rect.distanceTo(p) < dist) {
                if (node.rightTree.p.distanceTo(p) < dist) {
                    dist = node.rightTree.p.distanceTo(p);
                    nearestP = node.rightTree.p;
                }
                nearest(node.rightTree, p);
            }
            if (node.leftTree != null && node.leftTree.rect.distanceTo(p) < dist) {
                if (node.leftTree.p.distanceTo(p) < dist) {
                    dist = node.leftTree.p.distanceTo(p);
                    nearestP = node.leftTree.p;
                }
                nearest(node.leftTree, p);
            }
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree kt = new KdTree();
        Point2D t1 = new Point2D(0.2, 0.7);
        Point2D t2 = new Point2D(0.4, 0.7);
        Point2D t3 = new Point2D(0.6, 0.7);
        Point2D t4 = new Point2D(0.6, 0.3);
        Point2D t5 = new Point2D(0.6, 0.6);
        kt.insert(t1);
        kt.insert(t2);
        kt.insert(t3);
        kt.insert(t4);
        StdOut.println(kt.contains(t1));
        StdOut.println(kt.contains(t2));
        StdOut.println(kt.contains(t3));
        StdOut.println(kt.contains(t4));
        StdOut.println(kt.contains(t5));
        RectHV r = new RectHV(0.1, 0.5, 0.8, 0.8);
        StdOut.println(kt.range(r));
        StdOut.println(kt.nearest(t5));
    }
}