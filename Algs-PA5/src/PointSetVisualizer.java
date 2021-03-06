/******************************************************************************
 *  Compilation:  javac PointSetVisualizer.java
 *  Execution:    java PointSetVisualizer
 *  Dependencies: PointSet.java
 *
 *  Add the points that the user clicks in the standard draw window
 *  to a kd-tree and draw the resulting kd-tree.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

public class PointSetVisualizer {

    public static void main(String[] args) {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.show(0);
        PointSET PointSet = new PointSET();
        while (true) {
            if (StdDraw.mousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.setPenRadius(0.05);
                if (rect.contains(p)) {
                    StdOut.printf("%8.6f %8.6f\n", x, y);
                    PointSet.insert(p);
                    StdDraw.clear();
                    PointSet.draw();
                }
            }
            StdDraw.show(50);
        }

    }
}