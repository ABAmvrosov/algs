import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> arr;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) { throw new java.lang.NullPointerException(); }
        arr = new ArrayList<LineSegment>();
        for (int i1 = 0; i1 < points.length-3; i1++) {

            for (int i2 = i1+1; i2 < points.length-2; i2++) {

                for (int i3 = i2+1; i3 < points.length-1; i3++) {

                    if (points[i1].slopeTo(points[i2]) == points[i1].slopeTo(points[i3])) {

                        for (int i4 = i3 + 1; i4 < points.length; i4++) {
                            if (points[i1].slopeTo(points[i2]) == points[i1].slopeTo(points[i4])) {
                                arr.add(new LineSegment(points[i1], points[i4]));
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