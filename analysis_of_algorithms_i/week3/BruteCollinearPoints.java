import java.util.Arrays;

public class BruteCollinearPoints {
  private LineSegment[] segments;
  private int lineCount;

  public BruteCollinearPoints(Point[] points) {
    Arrays.sort(points);
    int length = points.length;
    LineSegment[] tempSegments = new LineSegment[length];
    lineCount = 0;

    for (int i = 0; i < length - 3; i++) {
      for (int j = i + 1; j < length - 2; j++) {
        for (int k = j + 1; k < length - 1; k++) {
          for (int l = k + 1; l < length; l++) {
            double ij = points[i].slopeTo(points[j]);
            double ik = points[i].slopeTo(points[k]);
            double il = points[i].slopeTo(points[l]);

            if (ij == ik && ik == il)
              tempSegments[lineCount++] = new LineSegment(points[i], points[j]);
          }
        }
      }
    }

    segments = new LineSegment[lineCount];
    for (int i = 0; i < lineCount; i++)
      segments[i] = tempSegments[i];
  }

  public int numberOfSegments() {
    return lineCount;
  }

  public LineSegment[] segments() {
    return segments;
  }
}
