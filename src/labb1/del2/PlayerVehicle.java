package labb1.del2;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import labb1.del2.controllers.IControllable;
import labb1.del2.gameobjects.vehicles.Vehicle;
import labb1.del2.gameobjects.vehicles.cars.Saab95;
import labb1.del2.gameobjects.vehicles.cars.Scania;
import labb1.del2.gameobjects.vehicles.cars.TowingTruck;
import labb1.del2.gameobjects.vehicles.cars.Volvo240;

public class PlayerVehicle {

    private Vehicle vehicle;
    private IControllable controller;

    public PlayerVehicle(Vehicle vehicle) {
        switchVehicle(vehicle);
    }

    public void handleKeyReleased(KeyCode key) {
        switch(key) {
            case DIGIT1 -> switchVehicle(new Volvo240(vehicle.getRect()));
            case DIGIT2 -> switchVehicle(new Saab95(vehicle.getRect()));
            case DIGIT3 -> switchVehicle(new Scania(vehicle.getRect()));
            case DIGIT4 -> switchVehicle(new TowingTruck(vehicle.getRect()));
        }
        controller.handleKeyReleased(key);
    }

    public void update(double dTime) {
        vehicle.move(dTime);
    }

    public void handleKeyPressed(KeyCode key) {
        controller.handleKeyPressed(key);
    }

    private void switchVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        controller = vehicle.getController();
    }

    public Rectangle getRect() {
        return vehicle.getRect();
    }

    public String[] getHudInfo() {
        return vehicle.getHudInfo();
    }
}
