package labb2.after;

import labb2.after.controller.Controller;
import labb2.after.model.Model;
import labb2.after.view.View;

public class Main {

    private static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 800;

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("CarSim 1.1", model, WINDOW_WIDTH, WINDOW_HEIGHT);
        new Controller(model, view, WINDOW_WIDTH);
        view.finished();
        model.run();
    }
}
