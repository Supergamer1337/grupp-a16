package labb1.del2;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class View {
    private final static int GAME_WIDTH = 1600, GAME_HEIGHT = 900;

    Canvas canvas;
    Group view;
    Game game;
    GraphicsContext gc;

    public View(Group root, Game game) {
        this.game = game;
        canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        view = new Group();
        root.getChildren().add(view);
        gc = canvas.getGraphicsContext2D();
        initWorld();
    }

    private void initWorld() {
        view.getChildren().add(game.getPlayer().getRect());
    }

    private void render() {
        gc.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gc.setFill(Color.BLACK);
        String[] hudText = game.getPlayer().getHudInfo();
        for (int i = 0; i < hudText.length; i++) {
            gc.fillText(hudText[i], 10, 20 + 20 * i);
        }
    }

    private void displayHUD() {
        String str = game.getPlayer().toString();

    }

    void update(double dTime) {
        render();
    }


}