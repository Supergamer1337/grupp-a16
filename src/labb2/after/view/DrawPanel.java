package labb2.after.view;

import labb1.del2.vehicles.Vehicle;
import labb2.after.model.vehicles.Car;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DrawPanel extends JPanel implements Observer{

    private List<DrawComponent> components;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.components = new ArrayList<>();
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
        // g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }

    @Override
    public void render(Graphics g) {
        for (DrawComponent component : components) {
            component.render(g);
        }
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
        DrawComponent c = new DrawComponent(car.getX(), car.getY(), img);
    }
}
