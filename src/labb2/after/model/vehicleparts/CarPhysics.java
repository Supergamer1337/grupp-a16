package labb2.after.model.vehicleparts;

import labb2.after.model.utils.Vector2D;

public class CarPhysics {
    private Vector2D pos, direction;
    private final double width, height, weight;
    private double speed;
    private Vector2D direction;

    public void move(double dTime) {
        pos = pos.add(direction.getX() * dTime, direction.getY() * dTime);
    
    }

    public void turnRight(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() + turnSpeed * dTime);
        }
    }

    public void turnLeft(double dTime) {
        if(getSpeed() != 0) {
            setRotation(getRotation() - turnSpeed * dTime);
        }
    }

    public Vector2D getDirection() {
        return direction;
    }
}
