import java.awt.*;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that.y == this.y) { return +0.0; } // horizontal
        if (that.x == this.x) { return Double.POSITIVE_INFINITY; } // vertical
        if (that.y == this.y && that.x == this.x) { return Double.NEGATIVE_INFINITY; } // same point
        else { return (double) ((that.y - this.y) / (that.x - this.x)); }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (that.y == this.y) { return this.x - that.x;  }
        else { return this.y - that.y; }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new slopeOrder();
    }

    private class slopeOrder implements Comparator<Point> {
        public int compare(Point a1, Point a2) {
            return (int) (a1.slopeTo(Point.this) - a2.slopeTo(Point.this));
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point a1 = new Point(1,1);
        Point a2 = new Point(3,1);
        Point a3 = new Point(2,3);
        Point a4 = new Point(4,3);
        Point a5 = new Point(2,4);
        Point a6 = new Point(2,2);
        Point a7 = new Point(3,3);
        Point[] parr = new Point[7];
        parr[0] = a1;
        parr[1] = a3;
        parr[2] = a2;
        parr[3] = a4;
        parr[4] = a5;
        parr[5] = a6;
        parr[6] = a7;
        StdDraw.show(0);
        StdDraw.setXscale(0, 5);
        StdDraw.setYscale(0, 5);
        for (Point x : parr) { x.draw(); }
        StdDraw.show();
        a1.drawTo(a2);
        a3.drawTo(a5);
        a1.drawTo(a3);
        a2.drawTo(a4);
        a1.drawTo(a5);
        a2.drawTo(a3);
        StdOut.println(a1.slopeTo(a2)); // horizontal
        StdOut.println(a3.slopeTo(a5)); // vertical
        StdOut.println(a1.slopeTo(a3)); // slope1
        StdOut.println(a2.slopeTo(a4)); // slope1
        StdOut.println(a1.slopeTo(a5)); // slope2
        StdOut.println(a2.slopeTo(a3)); // slope3
    }
}