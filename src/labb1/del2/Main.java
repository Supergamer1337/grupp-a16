package labb1.del2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private long timeSinceLastTick;
    private static double dTime = 1;

    /**
     * Initializes delta time variables.
     */
    @Override
    public void init() {
        timeSinceLastTick = 0;
        dTime = 0;
    }

    /**
     * Sets up all required objects, and starts game.
     * @param stage The stage to be used.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Cars");
        Group root = new Group();
        Scene scene = new Scene(root);
        Game game = new Game();
        View v = new View(root, game);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateDeltaTime(now);
                game.update(dTime);
                v.update(dTime);
            }
        };
        scene.setOnKeyPressed(game::handleKeyPressed);
        scene.setOnKeyReleased(game::handleKeyReleased);
        stage.setScene(scene);
        stage.show();
        timer.start();
    }

    /**
     * Updates the delta time.
     * @param now The current time.
     */
    private void updateDeltaTime(long now) {
        dTime = (now - timeSinceLastTick) / 1000000000.0;
        timeSinceLastTick = now;
    }

    /**
     * Gets the current delta time.
     * @return The current delta time.
     */
    public static double getDeltaTime() { return dTime; }

    /**
     * The main entrypoint of the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
