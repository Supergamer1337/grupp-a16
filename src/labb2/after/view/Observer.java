package labb2.after.view;

import labb1.del2.vehicles.Vehicle;

import java.awt.*;

public interface Observer {
    void update(Vehicle v);
    void render(Graphics g);
}
