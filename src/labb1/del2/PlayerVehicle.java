package labb1.del2;

import javafx.scene.input.KeyCode;
import labb1.del2.gameobjects.vehicles.Vehicle;
import labb1.del2.helpers.IControllable;

public class PlayerVehicle // implements IControllable
{
    private Vehicle vehicle;

    public PlayerVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
/*
    // Maybe?
    @Override
    public void handleReleasedKey(KeyCode key) {
        vehicle.getController().handleReleasedKey(key);
    }

    @Override
    public void handlePressedKey(KeyCode key) {
        vehicle.getController().handlePressedKey(key);
    }
 */
}
