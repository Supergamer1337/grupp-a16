package labb1.del2.helpers;

import javafx.scene.input.KeyCode;

public interface IControllable {
    void handleReleasedKey(KeyCode key);
    void handlePressedKey(KeyCode Key);
}
