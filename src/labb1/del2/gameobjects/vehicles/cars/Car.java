package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import labb1.del2.gameobjects.vehicles.Vehicle;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;

public abstract class Car extends Vehicle {

    private int nrOfDoors;
    private Engine engine;

    Car(double x, double y, double width, double height, Color color, String modelName, int nrOfDoors, Engine engine) {
        super(x, y, width, height, color, modelName);
        this.nrOfDoors = nrOfDoors;
        this.engine = engine;
    }

    @Override
    public void incrementSpeed(double amount){
        setSpeed(Math.min(getSpeed() + speedFactor() * amount, engine.getPower()));
    }

    @Override
    public void decrementSpeed(double amount) {
        setSpeed(Math.max(getSpeed() - speedFactor() * amount, 0));
    }

    public abstract double speedFactor();

    public final int getNrOfDoors() {
        return nrOfDoors;
    }
    public final Engine getEngine() {
        return engine;
    }
}