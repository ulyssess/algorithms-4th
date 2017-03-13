import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by wangaichao on 17/3/12.
 */
public class BruteCollinearPoints {

    private int lineNumber = 0;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        Arrays.sort(points);
        segments = new LineSegment[points.length];

        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++)
                for (int k = j; k < points.length; k++)
                    for (int l = k; l < points.length; l++) {
                        if (points[i].compareTo(points[j]) == 0 ||
                            points[i].compareTo(points[k]) == 0 ||
                            points[i].compareTo(points[l]) == 0 ||
                            points[j].compareTo(points[k]) == 0 ||
                                )
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
                        &&  points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {

                            StdOut.println(points[i].toString());
                            StdOut.println(points[j].toString());
                            StdOut.println(points[k].toString());
                            StdOut.println(points[l].toString());

                            LineSegment lineSegment = new LineSegment(points[i], points[l]);
                            segments[lineNumber] = lineSegment;
                            StdOut.println(lineNumber);
                            lineNumber++;

                            StdOut.println();
                            StdOut.println();
                            StdOut.println();

                        }
                    }
        }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
