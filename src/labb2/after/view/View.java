package labb2.after.view;

import labb2.after.model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {

    List<JPanel> panels;
    Model model;

    public View(String title, Model model, int width, int height) {
        this.model = model;
        panels = new ArrayList<>();
        this.setTitle(title);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createDrawPanel(width, height - 240);
    }

    private void createDrawPanel(int width, int height) {
        DrawPanel dPanel = new DrawPanel(width, height);
        dPanel.createComponents(model.getVehicles());
        model.subscribe(dPanel);
        add(dPanel);
    }

    public void finished() {
        // Finishes the view layout
        this.pack();
        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
    }
}
