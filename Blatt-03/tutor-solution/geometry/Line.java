package geometry;

public class Line {
    /** Startpoint of the line. */
    Point start;

    /** Endpoint of the line. */
    Point end;

    /**
     * Constructor.
     *
     * @param start
     *            startpoint
     * @param end
     *            endpoint
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStartPoint() {
        return this.start;
    }

    public Point getEndPoint() {
        return this.end;
    }

    /**
     * Returns a point on this line.
     *
     * @param t
     *            defines how near it is to the starting point {@code t = 0}
     *            means that it is the starting point, {@code t=1} means it is
     *            the endpoint
     * @return the interpolated point
     */
    public Point interpolate(double t) {
        return new Point((1 - t) * start.getX() + t * end.getX(),
                (1 - t) * start.getY() + t * end.getY());
    }

    /**
     * Mirrors this line at the assigned point.
     *
     * @param point The point to mirror at.
     * @return The mirrored line.
     */
    public Line mirrorAt(Point point) {
        return new Line(start.mirrorAt(point), end.mirrorAt(point));
    }

    /**
     * Mirrors this line at the assigned one.
     * @param line  The line to mirror at.
     * @return  The mirrored line.
     */
    public Line mirrorAt(Line line) {
        return new Line(start.mirrorAt(line), end.mirrorAt(line));
    }
}
