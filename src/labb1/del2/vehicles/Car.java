package labb1.del2.vehicles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.CarController;
import labb1.del2.controllers.IControllable;
import labb1.del2.vehicleparts.Engine;
import labb1.del2.vehicleparts.IEngine;
import labb1.del2.vehicleparts.VehicleBody;

public abstract class Car extends Vehicle {

    private final int nrOfDoors;
    private final Engine engine;

    Car(Rectangle rect, Color color, String modelName, int nrOfDoors, Engine engine) {
        super(new VehicleBody(rect, color), modelName);
        this.nrOfDoors = nrOfDoors;
        this.engine = engine;
    }

    /*
    Car(double x, double y, double width, double height, Color color, String modelName, int nrOfDoors, Engine engine) {
        this(new Rectangle(x, y, width, height), color, modelName, nrOfDoors, engine);
    }
    */

    @Override
    public void incrementSpeed(double amount) {
        if(!engine.isTurnedOn()) {
            throw new IllegalStateException("Can't increment speed unless engine is turned on");
        }
        setSpeed(Math.min(getSpeed() + speedFactor() * amount, engine.getPower()));
    }

    @Override
    public void decrementSpeed(double amount) {
        setSpeed(Math.max(getSpeed() - speedFactor() * amount, 0));
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Engine On: " + isEngineOn(),
                String.format("Speed: %.0fkm/h", getSpeed()),
                "Nr of doors: " + getNrOfDoors()
        };
    }

    public abstract double speedFactor();

    public double getEnginePower() {
        return engine.getPower();
    }

    public boolean isEngineOn() {
        return engine.isTurnedOn();
    }

    public void turnOn() {
        engine.turnOn();
    }

    public void turnOff() {
        engine.turnOff();
    }

    public void toggleEngineOn() {
        engine.toggleOn();
    }

    public final int getNrOfDoors() {
        return nrOfDoors;
    }

    @Override
    IControllable getController() {
        return new CarController(this);
    }
}
