import edu.princeton.cs.algs4.*;

import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> setOfPoints;

    // construct an empty set of points
    public PointSET() {
        setOfPoints = new TreeSet<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return setOfPoints.isEmpty();
    }

    // number of points in the set
    public int size() {
        return setOfPoints.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (!setOfPoints.contains(p)) { setOfPoints.add(p); }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return setOfPoints.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        Iterator<Point2D> pointIterator = setOfPoints.iterator();
        while (pointIterator.hasNext()) {
            Point2D p = pointIterator.next();
            StdDraw.point(p.x(), p.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<Point2D>();
        for (Point2D p : setOfPoints) {
            if (rect.contains(p)) { stack.push(p); }
        }
        return stack;
    }


    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (setOfPoints.isEmpty())  { return null; }
        Point2D nearestNeighbor = null;
        double dist = Double.POSITIVE_INFINITY;
        for (Point2D point : setOfPoints) {
            double tmpDist = p.distanceTo(point);
            if (tmpDist < dist) {
                dist = p.distanceTo(point);
                nearestNeighbor = point;
            }
        }
        return nearestNeighbor;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET pSet = new PointSET();
        pSet.insert(new Point2D(0.1, 0.1));
        pSet.insert(new Point2D(0.1, 0.2));
        pSet.insert(new Point2D(0.1, 0.3));
        Point2D p = new Point2D(0.4, 0.3);
        StdOut.println(pSet.nearest(p));
    }
}