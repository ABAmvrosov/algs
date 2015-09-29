import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> arr;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        arr = new ArrayList<LineSegment>();
        int a;
        for (Point point : points) {
            Arrays.sort(points, point.slopeOrder());

            for (int j = 1; j < points.length - 3; j++) {
                for (int k = j+1; k < points.length; k++) {
                    a = 0;
                    while (points[j].slopeTo(points[k + a]) == 0) {
                        a++;
                    }
                    if (a >=1) {
                        arr.add(new LineSegment(point, points[k + a]));
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