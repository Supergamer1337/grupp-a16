package exempel.lv1.asteroids.objects;

/*
    Class representing a single Asteroid

 */
public class Asteroid extends GameObject{

    public Asteroid(double x, double y, double width, double height, double dx, double dy) {
        super(x, y, dx, dy, width, height);
    }
}