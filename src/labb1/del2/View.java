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
import labb1.del2.vehicles.Car;
import labb1.del2.vehicles.Saab95;
import labb1.del2.vehicles.Vehicle;
import labb1.del2.vehicles.Volvo240;

import java.util.ArrayList;
import java.util.Comparator;

public class View extends Application {

    private final static int GAME_WIDTH = 1600, GAME_HEIGHT = 900;

    private long timeSinceLastTick;
    private static double dTime;
    private GraphicsContext gc;
    private Group map;
    private PlayerVehicle playerVehicle;
    private ArrayList<Vehicle> vehicles;

    @Override
    public void init() throws Exception {
        playerVehicle = new PlayerVehicle(new Saab95(GAME_WIDTH / 2.0, GAME_HEIGHT / 2.0));
        timeSinceLastTick = 0;
        vehicles = new ArrayList<>();
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

        map = new Group();
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
        map.getChildren().add(playerVehicle.getRect());
        vehicles.add(new Volvo240(GAME_WIDTH-200, GAME_HEIGHT - 100));
        map.getChildren().add(vehicles.get(0).getRect());
    }

    private void render() {
        gc.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gc.setFill(Color.BLACK);
        String[] hudText = playerVehicle.getHudInfo();
        for (int i = 0; i < hudText.length; i++) {
            gc.fillText(hudText[i], 10, 20 + 20 * i);
        }
    }

    private void update(long now) {
        updateDeltaTime(now);
        playerVehicle.update(dTime);
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        playerVehicle.handleKeyPressed(key);
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        playerVehicle.handleKeyReleased(key);
    }

    private void updateDeltaTime(long now) {
        dTime = (now - timeSinceLastTick) / 1000000000.0;
        timeSinceLastTick = now;
    }

    public static double getDeltaTime() { return dTime; }

    public static void start(String[] args) {
        launch(args);
    }
}

class VehicleComparator implements Comparator<Vehicle> {
    Vehicle car;
    VehicleComparator(Vehicle baseCar) {
        car = baseCar;
    }

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        double dist1 = calcDist(o1);
        double dist2 = calcDist(o2);
        if (o1 instanceof Car && !(o2 instanceof Car)) {
            return -1;
        }
        if (!(o1 instanceof Car) && o2 instanceof Car) {
            return 1;
        }
        return Double.compare(dist1, dist2);
    }
    private double calcDist(Vehicle v) {
        return Math.sqrt(Math.pow((car.getPosX() - v.getPosX()), 2) + Math.pow((car.getPosY() - v.getPosX()), 2));
    }
}