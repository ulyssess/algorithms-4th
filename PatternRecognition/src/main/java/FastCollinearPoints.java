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
    private Vector<LineSegment> tmpVector;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)     {

        int temp;
        int nowNumberShadow;
        tmpVector = new Vector<LineSegment>();
        Point[] oldPoints = points.clone();

        if (points == null)
            throw new java.lang.NullPointerException();

        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new java.lang.IllegalArgumentException();
        }

        for (int nowNumber = points.length; nowNumber > 3; nowNumber = nowNumberShadow, nowNumber--) {

            nowNumberShadow = nowNumber;

            Point[] newPoints = new Point[nowNumberShadow];
            for (int k = 0, m = 0; k < newPoints.length && m < oldPoints.length; m++) {
                if (oldPoints[m] != null)
                    newPoints[k++] = oldPoints[m];
            }

            Arrays.sort(newPoints);
            Comparator<Point> nameComparator = newPoints[0].slopeOrder();
            Arrays.sort(newPoints, nameComparator);

            for (int j = 1; j < newPoints.length - 1; j = temp, j++) {
                temp = j;
                while (temp < newPoints.length - 1 && newPoints[0].slopeTo(newPoints[temp]) == newPoints[0].slopeTo(newPoints[temp + 1])) {
                    temp++;
                }

                if (temp - j >= 2) {
                    LineSegment lineSegment = new LineSegment(newPoints[0], newPoints[temp]);
                    tmpVector.addElement(lineSegment);

                    for (int m = 0; m < oldPoints.length; m++) {
                        if (m >= j && m <= temp)
                            newPoints[m] = null;
                    }

                    nowNumberShadow = nowNumberShadow - (temp - j + 1);
                    break;
                }
            }
            newPoints[0] = null;

            oldPoints = newPoints;
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
        return tmpVector.toArray(new LineSegment[tmpVector.size()]);
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
