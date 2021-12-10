package labb2.after.controller;

import labb1.del2.vehicles.Car;
import labb2.after.model.Model;
import labb2.after.view.View;

import javax.swing.*;
import java.awt.*;

public class Controller extends JPanel {

    private final Model model;
    private int gasAmount;
    private JButton gasButton, brakeButton, turboOnButton, turboOffButton, liftBedButton, lowerBedButton,startButton, stopButton;

    private final int width, height;

    public Controller(Model model, View view) {
        view.add(this);
        width = view.getWidth();
        height = view.getHeight() - 240;
        this.model = model;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.green);
        gasAmount = 0;
        createGUI(view);
        addListeners();

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

        JPanel gasPanel = new JPanel();

        gasButton = new JButton("Gas");
        brakeButton = new JButton("Brake");
        turboOnButton = new JButton("Saab Turbo on");
        turboOffButton = new JButton("Saab Turbo off");
        liftBedButton = new JButton("Scania Lift Bed");
        lowerBedButton = new JButton("Lower Lift Bed");

        startButton = new JButton("Start all cars");
        stopButton = new JButton("Stop all cars");

        JLabel gasLabel = new JLabel("Amount of gas");

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        JSpinner gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        view.add(gasPanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((width/2)+4, 200));
        view.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(width/5-15,200));
        view.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(width/5-15,200));
        view.add(stopButton);

        view.pack();
    }

    private void addListeners() {
        gasButton.addActionListener(e -> model.gas(gasAmount));

        brakeButton.addActionListener(e -> model.brake(gasAmount));

        startButton.addActionListener(e -> model.start());

        stopButton.addActionListener(e -> model.stop());

        turboOnButton.addActionListener(e -> model.turboOn());

        turboOffButton.addActionListener(e -> model.turboOff());

        liftBedButton.addActionListener(e -> model.liftBed());

        lowerBedButton.addActionListener(e -> model.lowerBed());
    }
}
