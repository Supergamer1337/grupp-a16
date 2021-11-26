package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.View;
import labb1.del2.vehicles.Scania;

public class ScaniaController implements IControllable {

    private Scania car;

    public ScaniaController(Scania car) {
        this.car = car;
    }

    @Override
    public void handleKeyReleased(KeyCode key) {
        if (key == KeyCode.Q) {
            car.getEngine().toggleEngineOn();
        }
    }

    @Override
    public void handleKeyPressed(KeyCode key) {
        switch(key) {
            case W -> car.accelerate(1);
            case S -> car.decelerate(1);
            case A -> car.turnLeft(View.getDeltaTime());
            case D -> car.turnRight(View.getDeltaTime());
        }
        if (key == KeyCode.R && car.getSpeed() == 0) {
            car.getFlatbed().raiseFlatbed();
        } else if (key == KeyCode.F) {
            car.getFlatbed().lowerFlatbed();
        }
    }
}
