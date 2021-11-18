package labb1.del2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import labb1.del2.gameobjects.vehicles.Vehicle;
import labb1.del2.gameobjects.vehicles.cars.Saab95;
import labb1.del2.helpers.Vector2D;

public class View extends Application {

    private static int GAME_WIDTH = 1600, GAME_HEIGHT = 900;

    private long timeSinceLastTick;
    private static double dTime;
    private GraphicsContext gc;
    private Vehicle vehicle;

    @Override
    public void init() throws Exception {
        vehicle = new Saab95(new Vector2D(GAME_WIDTH / 2, GAME_HEIGHT / 2));
        timeSinceLastTick = 0;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Cars");

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
                render();
            }
        };

        Group root = new Group();

        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);

        Group map = new Group();
        initRender(map);
        root.getChildren().add(map);

        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    private void initRender(Group map) {
        // add cars, building etc, to root
        // TODO: Add more to environment
        // TODO: Learn how to add a background color
        map.getChildren().add(vehicle.getRect());
    }

    private void render() {
        gc.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gc.setFill(Color.BLACK);
        String[] hudText = vehicle.getHudInfo();
        for (int i = 0; i < hudText.length; i++) {
            gc.fillText(hudText[i], 10, 20 + 20 * i);
        }
    }

    private void update(long now) {
        updateDeltaTime(now);
        vehicle.move(dTime);
    }

    private void updateDeltaTime(long now) {
        dTime = (now - timeSinceLastTick) / 1000000000.0;
        timeSinceLastTick = now;
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        switch(key) {
            case W -> vehicle.accelerate(1);
            case S -> vehicle.decelerate(1);
            case A -> vehicle.turnLeft(dTime);
            case D -> vehicle.turnRight(dTime);
        }
    }

    public void handleKeyReleased(KeyEvent keyEvent) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
