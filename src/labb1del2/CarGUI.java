package labb1del2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import labb1del2.movables.cars.Car;

import static labb1del2.CarGame.*;

public class CarGUI extends Application {

    private CarGame game;
    private GraphicsContext gc;
    private AnimationTimer timer;

    @Override
    public void init() {
        // Init game variables
        game = new CarGame();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Canvas canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        timer = new AnimationTimer() {
            public void handle(long now) {
                game.update(now);
                render();
            }
        };
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(game::keyPressed);
        scene.setOnKeyReleased(game::keyReleased);
        stage.setScene(scene);
        stage.setTitle("Cars");
        stage.show();
        timer.start();
    }

    private void render() {
        gc.clearRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        gc.setFill(Color.WHITE);
        Car car = game.getCar();
        gc.fillText("Current Speed: " + car.getSpeed() + "km/h", 10, 20);
        gc.fillRect(car.getPos().getX(), car.getPos().getY(), 50, 25);
    }


    private Node nodeCar;
    private void renderCar() {

    }
}
