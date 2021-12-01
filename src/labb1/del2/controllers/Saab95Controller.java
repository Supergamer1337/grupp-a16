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
            case Q -> car.toggleOn();
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
}
