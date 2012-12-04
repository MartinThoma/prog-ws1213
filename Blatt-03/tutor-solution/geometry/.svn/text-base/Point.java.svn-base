package geometry;

public class Point {
    private final double x;
    private final double y;

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point that) {
        double xDiff = that.getX() - x;
        double yDiff = that.getY() - y;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public double manhattanDistance(Point that) {
        double xDiff = that.getX() - x;
        double yDiff = that.getY() - y;

        return Math.abs(xDiff) + Math.abs(yDiff);
    }

    public Point mirrorAt(Point point) {
        return new Point(2 * point.getX() - this.x, 2 * point.getY() - this.y);
    }

    public Point mirrorAt(Line line) {
        // shamelessly stolen from stack overflow...
        // don't do this at home
        Point p1 = line.getStartPoint();
        Point p2 = line.getEndPoint();

        double A = p2.getY() - p1.getY();
        double B = -(p2.getX() - p1.getX());
        double C = -A * p1.getX() - B * p1.getY();

        double M = Math.sqrt(A * A + B * B);

        double Ap = A / M;
        double Bp = B / M;
        double Cp = C / M;

        double D = Ap * x + Bp * y + Cp;

        return new Point(x - 2 * Ap * D, y - 2 * Bp * D);
    }
}
