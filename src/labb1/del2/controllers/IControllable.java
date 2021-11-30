package labb1.del2.controllers;

import javafx.scene.input.KeyCode;

public interface IControllable {
    /**
     * Handles the <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html">Keycode</a> as long as it is pressed.
     * @param key The key that is pressed.
     */
    void handleKeyPressed(KeyCode key);

    /**
     * Handles the <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html">Keycode</a> when it is released.
     * @param key The key that is released.
     */
    void handleKeyReleased(KeyCode key);
}
