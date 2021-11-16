package labb1del2.movables.trucks;

import javafx.scene.paint.Color;
import labb1del2.helpers.Vector2D;

import java.awt.*;

public class Scania extends Truck {

    public Scania(Vector2D pos) {
        super(pos, new Point(150, 75), 80, Color.CYAN, "Scania", 8562,  2);
    }

    public Scania() {
        this(Vector2D.zero());
    }
}
