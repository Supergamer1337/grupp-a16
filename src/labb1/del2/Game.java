package labb1.del2;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import labb1.del2.vehicles.Saab95;
import labb1.del2.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Vehicle> vehicles;
    private PlayerVehicle playerVehicle;

    public Game(PlayerVehicle startVehicle) {
        vehicles = new ArrayList<>();
        playerVehicle = startVehicle;
    }

    public Game() {
        this(new PlayerVehicle(new Saab95()));
    }

    public void update(double dTime) {
        playerVehicle.update(dTime);
        /*
        for (Vehicle vehicle : vehicles) {
            // TODO: Self driven???
        }
        */
    }

    public PlayerVehicle getPlayer() {
        return playerVehicle;
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        playerVehicle.handleKeyPressed(key);
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        playerVehicle.handleKeyReleased(key);
    }
}
