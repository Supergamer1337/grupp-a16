package labb1.del2.controllers;

import javafx.scene.input.KeyCode;

public interface IControllable {
    void handleKeyPressed(KeyCode key);
    void handleKeyReleased(KeyCode key);
}
