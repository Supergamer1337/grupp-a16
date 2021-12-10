package labb2.after.model;

import labb2.after.view.Observer;

public interface Observable {
    void update();
    void subscribe(Observer observer);
}
