package labb1.del2.buildings;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import labb1.del2.vehicles.*;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleWorkshopTest {

    @Test
    void load() {
        VehicleWorkshop<Car> vw = new VehicleWorkshop<>(5);
        vw.load(new Saab95());

    }

    @Test
    void unload() {
        VehicleWorkshop<Car> vw = new VehicleWorkshop<>(5);
        Saab95 c = new Saab95();
        vw.load(c);
        assertEquals(c, vw.unload(), "The vehicle should be Saab95");
    }

    @Test
    void getAtIndex() {
        VehicleWorkshop<Car> vehicleWorkshop = new VehicleWorkshop<>(5);
        vehicleWorkshop.load(new Scania());
        vehicleWorkshop.load(new Saab95());
        Volvo240 c = new Volvo240();
        vehicleWorkshop.load(c);
        assertEquals(c, vehicleWorkshop.unload(), "The vehicle at index 2 should be Volvo240");
    }

    @Test
    void paintJob() {
        VehicleWorkshop<Car> vehicleWorkshop = new VehicleWorkshop<>(5);
        vehicleWorkshop.load(new Saab95());
        vehicleWorkshop.paintJob(Color.TURQUOISE, 0);
        assertEquals(Color.TURQUOISE, vehicleWorkshop.unload().getColor(), "The color of the vehicle should be turquoise");
    }
}
