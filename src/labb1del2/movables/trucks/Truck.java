package labb1del2.movables.trucks;

import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;
import labb1del2.movables.cars.Car;
import labb1del2.movables.parts.Flatbed;

public abstract class Truck extends Car {
    private static final double BASE_TURN_SPEED = 2;
    private static final int BASE_NR_OF_DOORS = 2;
    private Flatbed flatbed;

    Truck(Vector2D pos, double turnSpeed, double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        super(pos, turnSpeed, enginePower, color, modelName, weight, nrOfDoors);
        flatbed = new Flatbed();
    }
    Truck(Vector2D pos, double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        this(pos, BASE_TURN_SPEED, enginePower, color, modelName, weight, nrOfDoors);
    }

    Truck(double enginePower, Color color, String modelName, int weight, int nrOfDoors) {
        this(Vector2D.zero(), enginePower, color, modelName, weight, nrOfDoors);
    }
    Truck(double enginePower, Color color, String modelName, int weight) {
        this(enginePower, color, modelName, weight, BASE_NR_OF_DOORS);
    }

    public Flatbed getFlatbed() {
        return flatbed;
    }

    @Override
    public double speedFactor() {
        return 0;
    }
}
