package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.View;
import labb1.del2.gameobjects.vehicles.cars.TowingTruck;

public class TowingTruckController implements IControllable {

    private TowingTruck car;

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
            case A -> car.turnLeft(View.getDeltaTime());
            case D -> car.turnRight(View.getDeltaTime());
            case R -> car.raiseRamp();
            case F -> car.lowerRamp();
        }
    }
}
