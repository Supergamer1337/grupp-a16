package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.Main;
import labb1.del2.View;
import labb1.del2.vehicles.Car;

import java.util.Objects;

public final class CarController implements IControllable {

    private final Car car;

    public CarController(Car car) {
        this.car = car;
    }

    @Override
    public void handleKeyPressed(KeyCode key) {
        switch(key) {
            case W -> car.accelerate(1);
            case S -> car.decelerate(1);
            case A -> car.turnLeft(Main.getDeltaTime());
            case D -> car.turnRight(Main.getDeltaTime());
        }
    }

    @Override
    public void handleKeyReleased(KeyCode key) {
        if (key == KeyCode.Q) {
            car.toggleOn();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarController that = (CarController) o;
        return car.equals(that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car);
    }
}
