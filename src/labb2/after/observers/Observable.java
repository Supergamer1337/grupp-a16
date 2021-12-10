package labb2.after.observers;

public interface Observable {
    void notifyObservers();
    void subscribe(Observer observer);
}
