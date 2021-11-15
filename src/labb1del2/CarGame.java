package labb1del2;

import javafx.scene.input.KeyEvent;
import labb1del2.movables.cars.Car;
import labb1del2.movables.cars.Saab95;

public class CarGame {
    public final static int GAME_WIDTH = 1600, GAME_HEIGHT = 900;

    private Car car;

    // TODO: Fully implement

    public CarGame() {
        car = new Saab95();
    }

    public void update(long now) {
        car.move();
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            // Basic car controls
            case W -> car.gas(1);
            case S -> car.brake(1);
            case A -> car.turnLeft();
            case D -> car.turnRight();
            // Reverse gear with Shift?
            // Switch car with 1,2...?
        }
    }

    public void keyReleased(KeyEvent keyEvent) {

    }

    public Car getCar() { return car; }

}
