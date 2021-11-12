package labb2.helpers;

public class Vector2D {
    private double x,y;

    public Vector2D(double x, double y) {
        this.x  = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void add(Vector2D vector) {
        x += vector.x;
        y += vector.y;
    }

    public void add(double x, double y) {
        add(new Vector2D(x, y));
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

    public static Vector2D zero() {
        return new Vector2D(0,0);
    }
}
