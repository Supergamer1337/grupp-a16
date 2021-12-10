package labb2.after.view;

import labb1.del2.vehicles.Vehicle;
import labb2.before.IObserver;

import java.awt.*;
import java.awt.image.BufferedImage;

class DrawComponent implements IObserver {
    private double x,y;
    private final BufferedImage img;

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

    @Override
    public void render(Graphics g) {
        g.drawImage(img, getX(), getY(), null);
    }

    @Override
    public void update(Vehicle vehicle) {
        x = vehicle.getPosX();
        y = vehicle.getPosY();
    }
}
