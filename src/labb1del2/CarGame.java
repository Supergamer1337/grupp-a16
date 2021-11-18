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
        controlledCar = new Saab95(new Vector2D(GAME_WIDTH / 2.0, GAME_HEIGHT / 2.0));
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
        if(controlledCar instanceof Scania scania) {
            switch (key) {
                case R -> scania.getFlatbed().raiseFlatbed(1);
                case F -> scania.getFlatbed().lowerFlatbed(1);
            }
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        switch(key) {
            case Q -> controlledCar.stopEngine();
            case E -> controlledCar.startEngine();
        }

        if (controlledCar instanceof Saab95 saab95) {
            if (key == KeyCode.T) {
                if (saab95.isTurboOn()) {
                    saab95.setTurboOff();
                } else {
                    saab95.setTurboOn();
                }
            }
        }
    }

    public Car getControlledCar() { return controlledCar; }
}