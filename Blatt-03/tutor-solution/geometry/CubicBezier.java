package geometry;

public class CubicBezier {
    Line first;
    Line second;

    public CubicBezier(Point start, Point control1, Point control2,
            Point end) {
        first = new Line(start, control1);
        second = new Line(control2, end);
    }

    public Point getStartPoint() {
        return first.getStartPoint();
    }

    public Point getFirstControlPoint() {
        return first.getEndPoint();
    }

    public Point getSecondControlPoint() {
        return second.getStartPoint();
    }

    public Point getEndPoint() {
        return second.getEndPoint();
    }

    public Point interpolate(double t) {
        return new QuadraticBezier(first.interpolate(t), new Line(
                first.getEndPoint(), second.getStartPoint())
                .interpolate(t), second.interpolate(t))
                .interpolate(t);
    }

    public CubicBezier mirrorAt(Point point) {
        return new CubicBezier(first.getStartPoint().mirrorAt(point),
                first.getEndPoint().mirrorAt(point), second
                        .getStartPoint().mirrorAt(point), second
                        .getEndPoint().mirrorAt(point));
    }

    public CubicBezier mirrorAt(Line line) {
        return new CubicBezier(first.getStartPoint().mirrorAt(line),
                first.getEndPoint().mirrorAt(line), second
                        .getStartPoint().mirrorAt(line), second
                        .getEndPoint().mirrorAt(line));
    }
}
