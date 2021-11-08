package exempel.lv1.inheritance;

import exempel.lv1.inheritance.Person;

public class Car extends Vehicle {

    private double topSpeed;

    public Car(Person owner, String id, double topSpeed) {
        super(owner, id);
        this.topSpeed = topSpeed;
    }

    @Override
    public String toString() {
        return "Car{topSpeed=" + topSpeed +", {owner=" + owner + "}, id='" + id + "'}";
    }
}
