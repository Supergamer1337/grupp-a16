package labb1.del2.vehicles;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;

public class PlayerVehicle {

    private Vehicle vehicle;
    private IControllable controller;

    public PlayerVehicle(Vehicle vehicle) {
        switchVehicle(vehicle);
    }

    /**
     * Handles the <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html">Keycode</a> as released.
     * @param key The key that was released.
     */
    public void handleKeyReleased(KeyCode key) {
        switch(key) {
            case DIGIT1 -> switchVehicle(new Saab95(vehicle.getRect()));
            case DIGIT2 -> switchVehicle(new Volvo240(vehicle.getRect()));
            case DIGIT3 -> switchVehicle(new Scania(vehicle.getRect()));
            case DIGIT4 -> switchVehicle(new TowingTruck(vehicle.getRect()));
            default -> controller.handleKeyReleased(key);
        }
    }

    /**
     * Updates the vehicle.
     * @param dTime The time since the last update.
     */
    public void update(double dTime) {
        vehicle.move(dTime);
    }

    /**
     * Handles the <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html">Keycode</a> as pressed.
     * @param key The key that was pressed.
     */
    public void handleKeyPressed(KeyCode key) {
        controller.handleKeyPressed(key);
    }

    /**
     * Switches the vehicle to the given vehicle.
     * @param vehicle The vehicle to switch to.
     */
    private void switchVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        controller = vehicle.getController();
    }

    /**
     * Gets the current vehicle.
     * @return The current vehicle.
     */
    public Vehicle getVehicle() { return vehicle; }

    /**
     * Gets the current vehicle's <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/shape/Rectangle.html">Rectangle</a>.
     * @return The rectangle of the current vehicle.
     */
    public Rectangle getRect() {
        return vehicle.getRect();
    }

    /**
     * Gets the HUD of the current vehicle.
     * @return The HUD of the current vehicle.
     */
    public String[] getHudInfo() {
        return vehicle.getHudInfo();
    }
}
