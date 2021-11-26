package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.View;
import labb1.del2.vehicles.Saab95;

public final class Saab95Controller implements IControllable {

    private final Saab95 car;

    public Saab95Controller(Saab95 car) {
        this.car = car;
    }

    @Override
    public void handleKeyReleased(KeyCode key) {
        switch (key) {
            case Q -> car.getEngine().toggleEngineOn();
            case E -> car.toggleTurbo();
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
    }
}
