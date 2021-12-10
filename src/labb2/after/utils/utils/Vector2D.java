package labb2.after.utils.utils;


import static java.lang.Math.atan2;

public class Vector2D {
    private final double x,y;

    /**
     * Creates a new Vector2D with the given x and y coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a zero vector.
     */
    public Vector2D() { this(0, 0); }

    /**
     * Returns the x-coordinate of this vector.
     * @return The x-coordinate.
     */
    public double getX() { return x; }

    /**
     * Returns the y-coordinate of this vector.
     * @return The y-coordinate.
     */
    public double getY() { return y; }

    /**
     * Adds the given vector to this vector.
     * @param vector The vector to add.
     * @return The sum of the two vectors.
     */
    public Vector2D add(Vector2D vector) {
        return new Vector2D(x + vector.getX(), y + vector.getY());
    }

    /**
     * Adds the given vector of coordinates to this vector.
     * @param x The x-coordinate to add.
     * @param y The y-coordinate to add.
     * @return The sum of the two vectors.
     */
    public Vector2D add(double x, double y) {
        return add(new Vector2D(x, y));
    }

    /**
     * Gets a copy of the vector.
     * @return A copy of the vector.
     */
    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    /**
     * Normalizes the vector.
     * @return The normalized vector.
     */
    public Vector2D getNormalized() {
        double length = x * x + y * y;
        return new Vector2D(x / length, y / length);
    }


    /**
     * Returns the vector represented in radians
     * @return The vector in radians
     */
    public double getAsRadians() {
        return atan2(y, x);
    }

    /**
     * Converts an angle to a vector.
     * @param angle The angle to convert.
     * @return The vector.
     */
    public static Vector2D angleToVector2D(double angle) {
        double tempX = Math.cos(angle);
        double tempY = Math.sin(angle);
        return new Vector2D(tempX, tempY);
    }
}
