package labb2.after.model.vehicles;

import labb2.after.model.IMovable;

public interface IVehicle extends IMovable {
    void accelerate();
    void decelerate();
    String getModelName();
}
