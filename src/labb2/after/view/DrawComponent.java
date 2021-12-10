package labb2.after.view;

import labb2.after.model.vehicles.IVehicle;

import java.awt.*;
import java.awt.image.BufferedImage;

class DrawComponent {
    private final IVehicle vehicle;
    private final Image img;

    public DrawComponent(IVehicle vehicle, BufferedImage img) {
        this.vehicle = vehicle;
        this.img = img;
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) vehicle.getX(), (int) vehicle.getY(), null);
    }
}
