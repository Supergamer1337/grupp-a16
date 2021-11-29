package labb1.del2;

public interface IMovable {
    /**
     * Moves the object in the direction it is facing.
     * @param dTime The time since the last update.
     */
    void move(double dTime);

    /**
     * Rotates the object to the right.
     * @param dTime The time since the last update.
     */
    void turnRight(double dTime);

    /**
     * Rotates the object to the left.
     * @param dTime The time since the last update.
     */
    void turnLeft(double dTime);
}
