package labb2.after.view;

import labb2.after.model.vehicles.IVehicle;
import labb2.after.observers.RenderedObserver;

import java.awt.*;
import java.awt.image.BufferedImage;

class DrawComponent {
    private final IVehicle vehicle;
    private double x, y;
    private final BufferedImage img;

    public DrawComponent(IVehicle vehicle, BufferedImage img) {
        this.vehicle = vehicle;
        x = vehicle.getX();
        y = vehicle.getY();
        this.img = img;
    }

    public final int getX() {
        return (int) x;
    }

    public final int getY() {
        return (int) y;
    }

    public void render(Graphics g) {
        g.drawImage(img, getX(), getY(), null);
    }

    public void update() {
        x = vehicle.getX();
        y = vehicle.getY();
    }
}
