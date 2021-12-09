package labb2.after.model.vehicleparts;

import javafx.scene.paint.Color;

public class CarAppearance {
    private final int nrOfDoors;
    private Color color;

    public CarAppearance(int nrOfDoors, Color color) {
        this.nrOfDoors = nrOfDoors;
        this.color = color;
    }

    /**
     * Gets the number of doors the car has.
     * @return The number of doors on the car.
     */
    public final int getNrOfDoors() {
        return nrOfDoors;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
