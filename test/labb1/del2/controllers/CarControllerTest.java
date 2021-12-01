package labb1.del2.controllers;

import javafx.scene.input.KeyCode;
import labb1.del2.vehicles.*;
import org.junit.jupiter.api.Test;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

class CarControllerTest {

    @Test
    void handleKeyPressed() {
        Car car = new Scania();
        PlayerVehicle playerVehicle = new PlayerVehicle(car);
        car.turnOn();

        playerVehicle.handleKeyPressed(KeyCode.W);
        assertEquals(0.9, car.getSpeed());

        playerVehicle.handleKeyPressed(KeyCode.D);
        assertTrue(car.getRotation() > 0);

        playerVehicle.handleKeyPressed(KeyCode.A);
        assertEquals(0, car.getRotation());

        playerVehicle.handleKeyPressed(KeyCode.S);
        assertEquals(0, car.getSpeed());

    }

    @Test
    void handleKeyReleased() {
        Car car = new Scania();
        PlayerVehicle playerVehicle = new PlayerVehicle(car);
        playerVehicle.handleKeyReleased(KeyCode.Q);
        assertTrue(car.isTurnedOn());
    }
}