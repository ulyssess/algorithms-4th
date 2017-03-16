import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by wangaichao on 17/3/12.
 */
public class BruteCollinearPoints {

    private int lineNumber = 0;
    private LineSegment[] trueSegments;
    private LineSegment[] segments;
    private Vector<LineSegment> tmpVector;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        if (points == null)
            throw new java.lang.NullPointerException();

        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new java.lang.IllegalArgumentException();
        }

        tmpVector = new Vector<LineSegment>();

        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                for (int k = j; k < points.length; k++) {
                    for (int m = k; m < points.length; m++) {

                        if (points[i].compareTo(points[j]) == 0 ||
                                points[i].compareTo(points[k]) == 0 ||
                                points[i].compareTo(points[m]) == 0 ||
                                points[j].compareTo(points[k]) == 0 ||
                                points[j].compareTo(points[m]) == 0 ||
                                points[k].compareTo(points[m]) == 0)
                            continue;

                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
                                &&  points[i].slopeTo(points[j]) == points[i].slopeTo(points[m])) {

                            LineSegment lineSegment = new LineSegment(points[i], points[m]);
                            tmpVector.addElement(lineSegment);
                            lineNumber++;
                        }
                    }
                }
            }
        }

        trueSegments = tmpVector.toArray(new LineSegment[tmpVector.size()]);
    }

    // the number of line segments
    public int numberOfSegments()
    {
        return lineNumber;
    }

    // the line segments
    public LineSegment[] segments()
    {
        segments = trueSegments.clone();
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        StdOut.println(collinear.numberOfSegments());

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
