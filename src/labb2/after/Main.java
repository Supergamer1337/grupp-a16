package labb2.after;

import labb2.after.controller.Controller;
import labb2.after.model.Model;
import labb2.after.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("CarSim 1.1", model);
        Controller controller = new Controller(model, view);
        model.run();
    }
}
