package labb1del2.movables.trucks;

import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;

import java.util.Vector;

public class TowTruck extends Truck {

    private int maxcarload, maxcarryweight;
    double carPickupRange;

    public TowTruck(
            Vector2D pos,
            double enginePower,
            Color color,
            String modelName,
            int weight,
            int nrOfDoors,
            int maxCarLoad,
            int maxCarryWeight,
            double carPickupRange
    ) {
        super(pos, enginePower, color, modelName, weight, nrOfDoors);
        this.maxcarryweight = maxCarryWeight;
        this.maxcarload = maxCarLoad;
        this.carPickupRange = carPickupRange;
    }

    public TowTruck(double enginePower, Color color, String modelName, int weight, int nrOfDoors, int maxCarLoad, int maxCarryWeight, double carPickupRange) {
        this(Vector2D.zero(), enginePower, color, modelName, weight, nrOfDoors, maxCarLoad, maxCarryWeight, carPickupRange);
    }

    @Override
    public double speedFactor() {
        if (getFlatbed().getCurrentFlatbedAngle() == 0)
            return getEnginePower() * 0.01;
        return 0;
    }
}