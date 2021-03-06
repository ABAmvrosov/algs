import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

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
        if (that.y == this.y && that.x == this.x) { return Double.NEGATIVE_INFINITY; } // same point
        if (that.y == this.y) { return +0.0; } // horizontal
        if (that.x == this.x) { return Double.POSITIVE_INFINITY; } // vertical
        else { return  ((double) (that.y - this.y) / (double) (that.x - this.x)); }
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
        return new OrderBySlope();
    }

    private class OrderBySlope implements Comparator<Point> {
        public int compare(Point a1, Point a2) {
            double slope1 = a1.slopeTo(Point.this);
            double slope2 = a2.slopeTo(Point.this);
            if (slope1 == slope2) {
                return 0;
            }
            if (slope1 == Double.NEGATIVE_INFINITY | slope2 == Double.POSITIVE_INFINITY) {
                return -1;
            }
            if (slope2 == Double.NEGATIVE_INFINITY | slope1 == Double.POSITIVE_INFINITY) {
                return 1;
            }
            double value = slope1 - slope2;
            if (value < 0) { return -1; }
            return 1;
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
    }
}