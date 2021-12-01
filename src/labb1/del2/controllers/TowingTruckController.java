package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.Main;
import labb1.del2.View;
import labb1.del2.vehicles.TowingTruck;

import java.util.Objects;

public final class TowingTruckController implements IControllable {

    private final TowingTruck car;

    public TowingTruckController(TowingTruck car) {
        this.car = car;
    }

    @Override
    public void handleKeyReleased(KeyCode key) {
        if (key == KeyCode.Q) {
            car.toggleOn();
        }
    }

    @Override
    public void handleKeyPressed(KeyCode key) {
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
