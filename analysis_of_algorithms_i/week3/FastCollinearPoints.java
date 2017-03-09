import java.util.Arrays;

public class FastCollinearPoints {
  private LineSegment[] segments;
  private int numSegments;

  public FastCollinearPoints(Point[] points) {
    numSegments = 0;
    LineSegment[] tempSegments = new LineSegment[points.length];

    Point[] pointsCopy = new Point[points.length];
    for (int i = 0; i < points.length; i++)
      pointsCopy[i] = points[i];

    for (int i = 0; i < points.length; i++) {
      Arrays.sort(pointsCopy, points[i].slopeOrder());
      tempSegments = addSegments(points, tempSegments);
    }

    tempSegments = new LineSegment[numSegments];
    for (int i = 0; i < numSegments; i++)
      segments[i] = tempSegments[i];
  }

  public int numberOfSegments() {
    return numSegments;
  }

  public LineSegment[] segments() {
    return segments;
  }

  private LineSegment[] addSegments(Point[] points, LineSegment[] tempSegments) {
    int i = 0;

    while (i < points.length - 2) {
      int j = i + 1;
      double slope = points[i].slopeTo(points[j]);

      while (slope == points[i].slopeTo(points[j])) j++;

      if (j - i >= 4 && notDuplicateSegment(points[i], points[j]))
        tempSegments[numSegments++] = new LineSegment(points[i], points[j]);

      i = j;
    }

    return tempSegments;
  }
}

