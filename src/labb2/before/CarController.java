package labb2.before;

import labb1.del2.vehicles.Car;
import labb1.del2.vehicles.Saab95;
import labb1.del2.vehicles.Scania;
import labb1.del2.vehicles.Volvo240;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(0, 0));
        cc.cars.add(new Scania(0, 100));
        cc.cars.add(new Saab95(0, 200));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        for (Car car : cc.cars) {
            cc.frame.drawPanel.createDrawComponent(car);
        }

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move(1); // wants a dTime
                int x = (int) Math.round(car.getPosX());
                int y = (int) Math.round(car.getPosY());
                outsideOfWindow(car);
            }
            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    private void outsideOfWindow(Car car) {
        if (outOfBounds(car)) {
            car.setRotation(car.getRotation() + Math.PI);
        }
    }

    private boolean outOfBounds(Car car) {
        return car.getPosX() + car.getWidth() > 800 || car.getPosX() < 0;
    }

    // Calls the accelerate method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.accelerate(gas);
        }
    }

    public void brake(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.decelerate(gas);
        }
    }

    // Calls the turn on method for each car once
    public void start() {
        for (Car car : cars) {
            car.turnOn();
        }
    }

    //
    public void stop() {
        for (Car car : cars) {
            car.turnOff();
        }
    }

    public void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95 saab) {
                saab.turboOn();
            }
        }
    }

    public void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95 saab) {
                saab.turboOff();
            }
        }
    }

    public void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania scania) {
                scania.raiseFlatbed();
            }
        }
    }

    public void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania scania) {
                scania.lowerFlatbed();
            }
        }
    }
}
