package labb2.movables;

import javafx.scene.image.Image;
import labb2.helpers.Vector2D;

public abstract class GameObject extends MovableObject {
    private Image img;

    public GameObject(Vector2D pos, Vector2D direction, double speed, Image img) {
        super(pos, direction, speed);
        this.img = img;
    }

    public GameObject(Vector2D pos, Vector2D direction, double speed, String imgPath) {
        this(pos, direction, speed, new Image(imgPath));
    }

    public void loadImage(String path) {
        img = new Image(path);
    }
}
