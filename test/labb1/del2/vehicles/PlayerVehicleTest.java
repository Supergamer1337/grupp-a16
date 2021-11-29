package labb1.del2.vehicles;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerVehicleTest {

    @Test
    void testHandleKeyReleased() {
        Car car = new Saab95();
        PlayerVehicle playerVehicle = new PlayerVehicle(car);

        PlayerVehicle temp = new PlayerVehicle(car);
        playerVehicle.handleKeyReleased(KeyCode.DIGIT1);
        assertTrue(playerVehicle.getVehicle() instanceof Saab95);

        playerVehicle.handleKeyReleased(KeyCode.DIGIT2);
        assertTrue(playerVehicle.getVehicle() instanceof Volvo240);

        playerVehicle.handleKeyReleased(KeyCode.DIGIT3);
        assertTrue(playerVehicle.getVehicle() instanceof Scania);

        playerVehicle.handleKeyReleased(KeyCode.DIGIT4);
        assertTrue(playerVehicle.getVehicle() instanceof TowingTruck);
    }

    @Test
    void testUpdate() {
        Car car = new Saab95();
        PlayerVehicle playerVehicle = new PlayerVehicle(car);
        double[] prevPos = { car.getPosX(), car.getPosY() };
        car.turnOn();
        car.accelerate(1);
        playerVehicle.update(1);
        assertFalse(prevPos[0] == car.getPosX() && prevPos[1] == car.getPosY());
    }

    @Test
    void testHandleKeyPressed() {
        Car car = new Saab95();
        PlayerVehicle playerVehicle = new PlayerVehicle(car);
        playerVehicle.handleKeyReleased(KeyCode.Q); // Turn on engine
        playerVehicle.handleKeyPressed(KeyCode.W); // Accelerate
        playerVehicle.handleKeyPressed(KeyCode.D); // Turn right
        assertEquals(car.getTurnSpeed(), car.getRotation());
    }

    @Test
    void testGetRect() {

    }

    @Test
    void testGetHudInfo() {

    }
}
