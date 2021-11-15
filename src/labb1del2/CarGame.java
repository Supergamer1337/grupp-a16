package labb1del2;

import javafx.scene.input.KeyEvent;
import labb1del2.movables.cars.Car;

public class CarGame {
    public final static int GAME_WIDTH = 1600, GAME_HEIGHT = 900;

    private Car car;

    // TODO: Fully implement

    public CarGame() {

    }

    public void update(long now) {

    }

    public void keyPressed(KeyEvent keyEvent) {
        car.gas(1);
    }

    public void keyReleased(KeyEvent keyEvent) {

    }

    public Car getCar() { return car; }

}
