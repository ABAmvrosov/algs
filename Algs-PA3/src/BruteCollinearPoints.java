import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> arr;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) { throw new java.lang.NullPointerException(); }
        arr = new ArrayList<LineSegment>();
        for (Point p : points) {

            for (Point p2 : points) {
                if (p.compareTo(p2) == 0) { continue; }

                for (Point p3 : points) {
                    if (p.compareTo(p3) == 0 | p2.compareTo(p3) == 0) { continue; }
                    if (p.slopeTo(p2) == p.slopeTo(p3)) {

                        for (Point p4 : points) {
                            if (p.compareTo(p4) == 0 | p2.compareTo(p4) == 0 | p3.compareTo(p4) == 0) { continue; }
                            if (p.slopeTo(p2) == p.slopeTo(p4)) {

                                arr.add(new LineSegment(p, p4));
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() { return arr.size(); }

    // the line segments
    public LineSegment[] segments() {
        return arr.toArray(new LineSegment[arr.size()]);
    }
}