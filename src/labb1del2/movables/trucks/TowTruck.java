package labb1del2.movables.trucks;

import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;

import java.awt.*;
import java.util.Vector;

public class TowTruck extends Truck {

    private int maxcarload, maxcarryweight;
    double carPickupRange;

    public TowTruck(
            Vector2D pos,
            Point dimensions,
            double enginePower,
            Color color,
            String modelName,
            int weight,
            int nrOfDoors,
            int maxCarLoad,
            int maxCarryWeight,
            double carPickupRange
    ) {
        super(pos, dimensions, enginePower, color, modelName, weight, nrOfDoors);
        this.maxcarryweight = maxCarryWeight;
        this.maxcarload = maxCarLoad;
        this.carPickupRange = carPickupRange;
    }

    public TowTruck(Point dimensions, double enginePower, Color color, String modelName, int weight, int nrOfDoors, int maxCarLoad, int maxCarryWeight, double carPickupRange) {
        this(Vector2D.zero(), dimensions, enginePower, color, modelName, weight, nrOfDoors, maxCarLoad, maxCarryWeight, carPickupRange);
    }

    public TowTruck(Vector2D pos) {
        this(pos, new Point(150, 75), 70, Color.GRAY, "Towing Truck", 5000, 2, 1, 3500, 10);
    }

    @Override
    public double speedFactor() {
        if (getFlatbed().getCurrentFlatbedAngle() == 0)
            return getEnginePower() * 0.01;
        return 0;
    }
}