package labb2.after.view;

import labb2.after.model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {

    private static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 800;

    List<JPanel> panels;
    Model model;

    public View(String title, Model model) {
        this.model = model;
        panels = new ArrayList<>();
        initView(title);
        createDrawPanel();
    }

    private void initView(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createDrawPanel() {
        DrawPanel panel = new DrawPanel();
        add();
    }

}
