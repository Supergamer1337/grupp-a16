package labb2.after.controller;

import labb1.del2.vehicles.Car;
import labb2.after.model.Model;
import labb2.after.view.View;

import javax.swing.*;
import java.awt.*;

public class Controller {

    private final Model model;
    private int gasAmount;

    private final int width;

    public Controller(Model model, View view, int width) {
        this.width = width;
        this.model = model;
        gasAmount = 0;
        createGUI(view);
    }

    private void outsideOfWindow(Car car) {
        if (outOfBounds(car)) {
            car.setRotation(car.getRotation() + Math.PI);
        }
    }

    private boolean outOfBounds(Car car) {
        return car.getPosX() + car.getWidth() > 800 || car.getPosX() < 0;
    }

    private void createGUI(View view) {
        createGasPanel(view);
        createControlPanel(view);
        createStartButton(view);
        createStopButton(view);
    }

    private void createGasPanel(View view) {
        JPanel gasPanel = new JPanel();

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        JSpinner gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());

        JLabel gasLabel = new JLabel("Amount of gas");

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        view.add(gasPanel);
    }

    private void createControlPanel(View view) {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,4));
        controlPanel.setBackground(Color.CYAN);

        JButton gasButton = new JButton("Gas");
        JButton brakeButton = new JButton("Brake");
        JButton turboOnButton = new JButton("Saab Turbo on");
        JButton turboOffButton = new JButton("Saab Turbo off");
        JButton liftBedButton = new JButton("Scania Lift Bed");
        JButton lowerBedButton = new JButton("Lower Lift Bed");

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((width/2)+4, 200));

        gasButton.addActionListener(e -> model.gas(gasAmount));
        brakeButton.addActionListener(e -> model.brake(gasAmount));
        turboOnButton.addActionListener(e -> model.turboOn());
        turboOffButton.addActionListener(e -> model.turboOff());
        liftBedButton.addActionListener(e -> model.liftBed());
        lowerBedButton.addActionListener(e -> model.lowerBed());

        view.add(controlPanel);
    }

    private void createStartButton(View view) {
        JButton startButton = new JButton("Start all cars");
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(width/5-15,200));
        startButton.addActionListener(e -> model.start());
        view.add(startButton);
    }

    private void createStopButton(View view) {
        JButton stopButton = new JButton("Stop all cars");
        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(width/5-15,200));
        stopButton.addActionListener(e -> model.stop());
        view.add(stopButton);
    }

}
