import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> arr;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        arr = new ArrayList<LineSegment>();
        for (Point point : points) {
            Arrays.sort(points, point.slopeOrder());
            for (int j = 1; j < points.length - 3; j++) {
                int k = 1;
                while (point.slopeTo(points[j]) == point.slopeTo(points[j + k])) {
                    k++;
                    if (k >= 2 && !(point.slopeTo(points[j]) == point.slopeTo(points[j + k]))) {
                        arr.add(new LineSegment(point, points[j+k-1]));
                    }
                }
            }
        }
    }

    // the number of line segments
    public           int numberOfSegments() {
        return arr.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return arr.toArray(new LineSegment[arr.size()]);
    }
}