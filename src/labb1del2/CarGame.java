package labb1del2;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import labb1del2.helpers.Vector2D;
import labb1del2.movables.cars.Car;
import labb1del2.movables.cars.Saab95;
import labb1del2.movables.cars.Volvo240;
import labb1del2.movables.trucks.Scania;

public class CarGame {
    public final static int GAME_WIDTH = 1600, GAME_HEIGHT = 900;

    private Car controlledCar;

    public CarGame() {
        controlledCar = new Saab95(new Vector2D(GAME_WIDTH/2, GAME_HEIGHT/2));
    }

    public void update(double now) {
        controlledCar.drive(now);
    }

    public void keyPressed(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        switch (key) {
            // Basic car controls
            case W -> controlledCar.gas(1);
            case S -> controlledCar.brake(1);
            case A -> controlledCar.turnLeft(CarGUI.getDeltaTime());
            case D -> controlledCar.turnRight(CarGUI.getDeltaTime());
            // Reverse gear with Shift?
            // Switch car with 1,2, etc
            case DIGIT1 -> controlledCar = new Saab95(controlledCar.getPos());
            case DIGIT2 -> controlledCar = new Volvo240(controlledCar.getPos());
            case DIGIT3 -> controlledCar = new Scania(controlledCar.getPos());
        }
    }

    public void keyReleased(KeyEvent keyEvent) {

    }

    public Car getControlledCar() { return controlledCar; }
}
