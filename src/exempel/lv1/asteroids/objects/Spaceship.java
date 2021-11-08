package exempel.lv1.asteroids.objects;


import exempel.lv1.asteroids.objects.Asteroid;
import exempel.lv1.asteroids.objects.GameObject;

/*
    Class representing a Spaceship

 */
public class Spaceship extends GameObject {
    public static final double MAX_DX = 2;
    public static final double MAX_DY = 2;

    public Spaceship(double x, double y, double width, double height, double dx, double dy) {
        super(x, y, dx, dy, width, height);
    }

    public Spaceship(double x, double y, int width, int height) {
        this(x, y, width, height, 0, 0);
    }


    public boolean intersects(Asteroid asteroid) {
        boolean above = asteroid.getMaxY() < getY();
        boolean below = asteroid.getY() > getMaxY();
        boolean leftOf = asteroid.getMaxX() < getX();
        boolean rightOf = asteroid.getX() > getMaxX();
        return !(above || below || leftOf || rightOf);
    }

    public void stop() {
        dx = dy = 0;
    }

}
