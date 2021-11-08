package exempel.lv1.inheritance;

import java.util.Random;

public abstract class Vehicle {
    protected String id;
    protected Person owner;

    protected Vehicle(Person owner, String id) {
        this.id = id;
        this.owner = owner;
    }
}
