package exempel.lv1.asteroids.objects;

public abstract class GameObject extends MovableObject {
    protected double width, height;

    protected GameObject(double x, double y, double dx, double dy, double width, double height) {
        super(x, y, dx, dy);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getMaxX() {
        return x + width;
    }

    public double getMaxY() {
        return y + height;
    }
}
