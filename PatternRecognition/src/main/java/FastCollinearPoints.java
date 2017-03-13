import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

/**
 * Created by wangaichao on 17/3/12.
 */
public class FastCollinearPoints {

    private int lineNumber = 0;
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)     {

        Arrays.sort(points);
        Vector<LineSegment> tmpVector = new Vector<LineSegment>();

        Comparator<Point> nameComparator = points[0].slopeOrder();
        Arrays.sort(points, nameComparator);

        int temp;

        for (int j = 0; j < points.length - 3; j = temp, j++) {
            temp = j;
            while (temp < points.length - 2 && points[temp].slopeTo(points[temp + 1]) == points[temp].slopeTo(points[temp + 2])) {
                temp++;
            }

            if (temp - j >= 2) {
                LineSegment lineSegment = new LineSegment(points[j], points[temp + 1]);
                tmpVector.addElement(lineSegment);
            }
        }

        segments = tmpVector.toArray(new LineSegment[tmpVector.size()]);
    }

    // the number of line segments
    public int numberOfSegments()
    {
        return lineNumber;
    }

    // the line segments
    public LineSegment[] segments()
    {
        return segments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
