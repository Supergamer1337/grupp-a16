package labb2.after;

import labb1.del2.vehicles.Vehicle;

import java.awt.*;

public interface IObserver {
    void update(Vehicle v);
    void render(Graphics g);
}
