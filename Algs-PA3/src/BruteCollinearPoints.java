import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> arr;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) { throw new java.lang.NullPointerException(); }
        arr = new ArrayList<LineSegment>();
        Point min;
        Point max;
        for (int i1 = 0; i1 < points.length-3; i1++) {

            for (int i2 = i1+1; i2 < points.length-2; i2++) {
                if (points[i1].compareTo(points[i2]) == 0) { throw new java.lang.IllegalArgumentException(); }
                min = points[i1];
                max = points[i1];
                if (min.compareTo(points[i2]) > 0) { min = points[i2]; }
                if (max.compareTo(points[i2]) < 0) { max = points[i2]; }

                for (int i3 = i2+1; i3 < points.length-1; i3++) {
                    if (points[i2].compareTo(points[i3]) == 0) { throw new java.lang.IllegalArgumentException(); }
                    if (points[i1].slopeTo(points[i2]) == points[i1].slopeTo(points[i3])) {
                        if (min.compareTo(points[i3]) > 0) { min = points[i3]; }
                        if (max.compareTo(points[i3]) < 0) { max = points[i3]; }

                        for (int i4 = i3 + 1; i4 < points.length; i4++) {
                            if (points[i1].compareTo(points[i4]) == 0) { throw new java.lang.IllegalArgumentException(); }
                            if (points[i2].compareTo(points[i4]) == 0) { throw new java.lang.IllegalArgumentException(); }
                            if (points[i1].slopeTo(points[i2]) == points[i1].slopeTo(points[i4])) {
                                if (min.compareTo(points[i4]) > 0) { min = points[i4]; }
                                if (max.compareTo(points[i4]) < 0) { max = points[i4]; }
                                arr.add(new LineSegment(min, max));
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