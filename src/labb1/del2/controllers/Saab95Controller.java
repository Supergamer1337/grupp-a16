package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.Main;
import labb1.del2.View;
import labb1.del2.vehicles.Saab95;

import java.util.Objects;

public final class Saab95Controller implements IControllable {

    private final Saab95 car;

    public Saab95Controller(Saab95 car) {
        this.car = car;
    }

    @Override
    public void handleKeyReleased(KeyCode key) {
        switch (key) {
            case Q -> car.toggleEngineOn();
            case E -> car.toggleTurbo();
        }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saab95Controller that = (Saab95Controller) o;
        return car.equals(that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car);
    }
}
