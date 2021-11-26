package labb1.del2;

import javafx.scene.Group;
import labb1.del2.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Vehicle> vehicles;
    private long timeSinceLastTick;
    private static double dTime;
    private Group root, map;

    private Main() {
        vehicles = new ArrayList<>();
        timeSinceLastTick = 0;
        dTime = 0;
        root = new Group();
        map = createMap();
    }

    private Group createMap(){
        Group baseMap = new Group();

        return baseMap;
    }


}
