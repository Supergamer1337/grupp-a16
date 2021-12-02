package labb2.delA;

import labb1.del2.vehicles.Car;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    // To keep track of a single cars position
    private final List<DrawComponent> components;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        components = new ArrayList<>();
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DrawComponent c : components) {
            c.draw(g);
        }
        // g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }

    public void createDrawComponent(Car car) {
        String folderPath = "pics/";
        String fileType = ".jpg";
        BufferedImage img = null;
        try {
            String path = folderPath + car.getModelName() + fileType;
            img = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        DrawComponent c = new DrawComponent(car.getPosX(), car.getPosY(), img);
        car.subscribe(c);
        components.add(c);
    }
}
