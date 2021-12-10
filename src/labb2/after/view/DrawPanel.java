package labb2.after.view;

import labb2.after.model.vehicles.IVehicle;
import labb2.after.observers.RenderedObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DrawPanel extends JPanel implements RenderedObserver {

    private final List<DrawComponent> components;

    // Initializes the panel and reads the images
    DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.components = new ArrayList<>();
    }

    @Override
    public void update() {
        for (DrawComponent component : components) {
            component.update();
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    @Override
    public void render(Graphics g) {
        for (DrawComponent component : components) {
            component.render(g);
        }
    }

    void createDrawComponent(IVehicle vehicle) {
        String folderPath = "pics/";
        String fileType = ".jpg";
        BufferedImage img = null;
        try {
            String path = folderPath + vehicle.getModelName() + fileType;
            img = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        DrawComponent c = new DrawComponent(vehicle, img);
        components.add(c);
    }

    public void createComponents(List<IVehicle> vehicles) {
        for (IVehicle v : vehicles) {
            createDrawComponent(v);
        }
    }
}
