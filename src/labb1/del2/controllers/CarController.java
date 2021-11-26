package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.View;
import labb1.del2.vehicles.Car;

public class CarController implements IControllable {

    private Car car;

    public CarController(Car car) {
        this.car = car;
    }

    @Override
    public void handleKeyPressed(KeyCode key) {
        switch(key) {
            case W -> car.accelerate(1);
            case S -> car.decelerate(1);
            case A -> car.turnLeft(View.getDeltaTime());
            case D -> car.turnRight(View.getDeltaTime());
        }
    }

    @Override
    public void handleKeyReleased(KeyCode key) {
        if (key == KeyCode.Q) {
            car.getEngine().toggleEngineOn();
        }
    }
}
