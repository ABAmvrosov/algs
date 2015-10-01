import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> arr;

    public FastCollinearPoints(Point[] points) {
        arr = new ArrayList<LineSegment>();

        Point[] sorted = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) { throw new NullPointerException(); }
            for (int j = i; j < points.length; j++) {
                if (i != j && points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
            }
            sorted[i] = points[i];
        }

        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];
            Arrays.sort(sorted);
            Arrays.sort(sorted, origin.slopeOrder());
            for (int j = 1; j < sorted.length-2;) {
                int k = 1;

                while (((j+k) < sorted.length) && (origin.slopeTo(sorted[j]) == origin.slopeTo(sorted[j+k]))) {
                    k++;
                }

                if (k > 2 && (origin.compareTo(sorted[j]) < 0)) {
                    arr.add(new LineSegment(origin, sorted[j + k - 1]));
                }
                j += k;
            }
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return arr.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return arr.toArray(new LineSegment[arr.size()]);
    }

    public static void main (String[] args) {

    }
}
