package labb2;

import javafx.scene.input.KeyEvent;
import labb2.helpers.Vector2D;
import labb2.movables.Car;

public class CarGame {
    public final static int GAME_WIDTH = 1600, GAME_HEIGHT = 900;

    private Car car;



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
