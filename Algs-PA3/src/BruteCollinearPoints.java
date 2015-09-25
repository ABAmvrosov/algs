import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> arr;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        Arrays.sort(points);
        for (int i1 = 0; i1 < points.length-4; i1++) {

            for (int i2 = i1; i2 < points.length-3; i2++) {

                for (int i3 = i2; i3 < points.length-2; i3++) {

                    if (points[i1].slopeTo(points[i2]) == points[i1].slopeTo(points[i3])) {
                        continue;
                    }

                    for (int i4 = i3; i4 < points.length-1; i4++) {
                        if (points[i1].slopeTo(points[i2]) == points[i1].slopeTo(points[i4])) {
                            arr.add(new LineSegment(points[i1], points[i2]));
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