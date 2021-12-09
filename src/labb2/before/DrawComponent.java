package labb2.before;

import labb1.del2.vehicles.Vehicle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawComponent implements IObserver {
    private double x,y;
    private BufferedImage img;

    public DrawComponent(double x, double y, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public final int getX() {
        return (int) x;
    }

    public final int getY() {
        return (int) y;
    }

    public final BufferedImage getImg() {
        return img;
    }

    public void render(Graphics g) {
        g.drawImage(img, getX(), getY(), null);
    }

    @Override
    public void update(Vehicle vehicle) {
        x = vehicle.getPosX();
        y = vehicle.getPosY();
    }
}
