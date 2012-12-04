package geometry;

public class Line {
    Point start;
    Point end;

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

    public Point interpolate(double t) {
        return new Point((1 - t) * start.getX() + t * end.getX(),
                         (1 - t) * start.getY() + t * end.getY());
    }

    public Line mirrorAt(Point point) {
        return new Line(start.mirrorAt(point), end.mirrorAt(point));
    }

    public Line mirrorAt(Line line) {
        return new Line(start.mirrorAt(line), end.mirrorAt(line));
    }
}
