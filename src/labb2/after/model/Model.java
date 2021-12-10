package labb2.after.model;

import labb2.after.model.vehicles.Car;
import labb2.after.model.vehicles.Saab95;
import labb2.after.model.vehicles.Scania;
import labb2.after.view.Observer;
import labb2.after.model.vehicles.IVehicle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model implements Observable {
    private final static int TIMER_DELAY = 50; // Delay in ms

    private final List<IVehicle> vehicles;
    private final List<Observer> observers;
    private final Timer timer;

    public Model() {
        observers = new ArrayList<>();
        vehicles = Arrays.asList(CarFactory.createDefaultCarSetup());
        timer = new Timer(TIMER_DELAY, e -> tick(TIMER_DELAY / 1000.0));
    }

    private void tick(double dTime) {
        for(IVehicle vehicle : vehicles) {
            vehicle.move(dTime);
        }
        update();
    }

    public void run() {
        timer.start();
    }

    public void pause() {
        timer.stop();
    }

    public List<IVehicle> getVehicles() {
        return vehicles;
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (IVehicle vehicle : vehicles) {
            vehicle.accelerate(gas);
        }
    }

    public void brake(int amount) {
        double gas = ((double) amount) / 100;
        for (IVehicle vehicle : vehicles) {
            vehicle.decelerate(gas);
        }
    }

    // Calls the turn on method for each car once
    public void start() {
        for (IVehicle vehicle : vehicles) {
            if (vehicle instanceof Car car) {
                car.turnOn();
            }
        }
    }

    public void stop() {
        for (IVehicle vehicle : vehicles) {
            if (vehicle instanceof Car car) {
                car.turnOff();
            }
        }
    }

    public void turboOn() {
        for (IVehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95 saab95) {
                saab95.turboOn();
            }
        }
    }

    public void turboOff() {
        for (IVehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95 saab95) {
                saab95.turboOff();
            }
        }
    }

    public void liftBed() {
        for (IVehicle vehicle : vehicles) {
            if (vehicle instanceof Scania scania) {
                scania.raiseFlatbed();
            }
        }
    }

    public void lowerBed() {
        for (IVehicle vehicle : vehicles) {
            if (vehicle instanceof Scania scania) {
                scania.lowerFlatbed();
            }
        }
    }

    @Override
    public void update() {
        for(Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void subscribe(Observer o) {
        observers.add(o);
    }

}


