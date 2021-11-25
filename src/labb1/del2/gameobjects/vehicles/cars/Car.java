package labb1.del2.gameobjects.vehicles.cars;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.CarController;
import labb1.del2.controllers.IControllable;
import labb1.del2.gameobjects.vehicles.Vehicle;
import labb1.del2.gameobjects.vehicles.vehicleparts.Engine;

public abstract class Car extends Vehicle {

    private int nrOfDoors;
    private Engine engine;

    Car(Rectangle rect, Color color, String modelName, int nrOfDoors, Engine engine) {
        super(rect, color, modelName);
        this.nrOfDoors = nrOfDoors;
        this.engine = engine;
    }

    Car(double x, double y, double width, double height, Color color, String modelName, int nrOfDoors, Engine engine) {
        this(new Rectangle(x, y, width, height), color, modelName, nrOfDoors, engine);
    }

    @Override
    public void incrementSpeed(double amount){
        if(engine.isTurnedOn()) {
            setSpeed(Math.min(getSpeed() + speedFactor() * amount, engine.getPower()));
        }
    }

    @Override
    public String[] getHudInfo() {
        return new String[] {
                "Model: " + getModelName(),
                "Engine On: " + getEngine().isTurnedOn(),
                String.format("Speed: %.0fkm/h", getSpeed()),
                "Nr of doors: " + getNrOfDoors()
        };
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

    @Override
    public IControllable getController() {
        return new CarController(this);
    }
}
