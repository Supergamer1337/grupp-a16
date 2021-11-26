package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.Main;
import labb1.del2.View;
import labb1.del2.vehicles.TowingTruck;

public final class TowingTruckController implements IControllable {

    private final TowingTruck car;

    public TowingTruckController(TowingTruck car) {
        this.car = car;
    }
    @Override
    public void handleKeyPressed(KeyCode key) {
        if (key == KeyCode.Q) {
            car.getEngine().toggleEngineOn();
        }
    }

    @Override
    public void handleKeyReleased(KeyCode key) {
        switch(key) {
            case W -> car.accelerate(1);
            case S -> car.decelerate(1);
            case A -> car.turnLeft(Main.getDeltaTime());
            case D -> car.turnRight(Main.getDeltaTime());
            case R -> car.raiseRamp();
            case F -> car.lowerRamp();
        }
    }
}
