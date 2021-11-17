package labb1del2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import labb1del2.movables.cars.Car;

import static labb1del2.CarGame.*;

public class CarGUI extends Application {
    private static double dTime;
    private static long lastTime;

    private CarGame game;
    private Group map;
    private GraphicsContext gc;

    @Override
    public void init() {
        game = new CarGame();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Cars");
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        map = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(map);
        gc = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                updateDeltaTime(now);
                game.update(dTime);
                render();
            }
        };

        scene.setOnKeyPressed(game::keyPressed);
        scene.setOnKeyReleased(game::keyReleased);

        stage.setScene(scene);
        stage.show();
        timer.start();
    }

    private void render() {
        Car car = game.getControlledCar();
        // Uses gc
        renderCarHUD(car);
        // uses map
        map.getChildren().clear();
        renderCar(car);
    }

    private void renderCarHUD(Car car) {
        gc.clearRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        // Write text
        gc.setFont(new Font(20));
        gc.setFill(Color.BLACK);
        gc.fillText("Current Speed: " + car.getSpeed() + "km/h", 10, 20);
    }

    private void renderCar(Car car) {
        // Draw car
        Rectangle rect = new Rectangle(car.getPos().getX(), car.getPos().getY(), car.getDimensions().getX(), car.getDimensions().getY());
        rect.setFill(car.getColor());
        Rotate rotate = new Rotate();
        rotate.setPivotX(car.getDimensions().getX() / 2 + car.getPos().getX());
        rotate.setPivotY(car.getDimensions().getY() / 2 + car.getPos().getY());
        rotate.setAngle(car.getRotation() * 180 / Math.PI);
        rect.getTransforms().addAll(rotate);
        map.getChildren().add(rect);
    }

    public static void updateDeltaTime(long now) {
        dTime = (now - lastTime) / 1000000000.0;
        lastTime = now;
    }

    public static double getDeltaTime() {
        return dTime;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
