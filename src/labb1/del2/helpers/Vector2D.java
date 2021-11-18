package labb1.del2.helpers;


public class Vector2D {
    private double x,y;

    public Vector2D(double x, double y) {
        this.x  = x;
        this.y = y;
    }

    public Vector2D() { this(0, 0); }

    public double getX() { return x; }
    public double getY() { return y; }

    public Vector2D add(Vector2D vector) {
        return new Vector2D(x + vector.getX(), y + vector.getY());
    }

    public Vector2D add(double x, double y) {
        return add(new Vector2D(x, y));
    }

    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    public Vector2D getNormalized() {
        double length = x * x + y * y;
        return new Vector2D(x / length, y / length);
    }

    public static Vector2D angleToVector2D(double angle) {
        double tempX = Math.cos(angle);
        double tempY = Math.sin(angle);
        return new Vector2D(tempX, tempY);
    }
}
