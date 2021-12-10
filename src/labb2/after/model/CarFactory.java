package labb2.after.model;

import labb2.after.utils.Vector2D;
import labb2.after.model.vehicles.*;

public class CarFactory {
    public static Car[] createDefaultCarSetup() {
        return new Car[]{
                new Volvo240(),
                new Saab95(new Vector2D(0, 100)),
                new Scania(new Vector2D(0, 200))
        };
    }
}
