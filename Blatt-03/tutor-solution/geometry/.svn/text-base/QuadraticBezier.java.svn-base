package geometry;

public class QuadraticBezier {
    Line first;
    Line second;

    public QuadraticBezier(Point start, Point control, Point end) {
        first = new Line(start, control);
        second = new Line(control, end);
    }

    public Point getStartPoint() {
        return first.getStartPoint();
    }

    public Point getControlPoint() {
        return first.getEndPoint();
    }

    public Point getEndPoint() {
        return second.getEndPoint();
    }

    public Point interpolate(double t) {
        Line temp = new Line(first.interpolate(t), second.interpolate(t));
        return temp.interpolate(t);
    }

    public QuadraticBezier mirrorAt(Point point) {
        return new QuadraticBezier(
            first.getStartPoint().mirrorAt(point),
            first.getEndPoint().mirrorAt(point),
            second.getEndPoint().mirrorAt(point));
    }

    public QuadraticBezier mirrorAt(Line line) {
        return new QuadraticBezier(
            first.getStartPoint().mirrorAt(line),
            first.getEndPoint().mirrorAt(line),
            second.getEndPoint().mirrorAt(line));
    }
}
