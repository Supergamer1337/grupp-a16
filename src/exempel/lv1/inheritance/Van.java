package exempel.lv1.inheritance;

public class Van extends Vehicle {
    private double maxCargo;

    public Van(Person owner, String id, double maxCargo) {
        super(owner, id);
        this.maxCargo = maxCargo;
    }

    @Override
    public String toString() {
        return "Car{maxCargo=" + maxCargo +", {owner=" + owner + "}, id='" + id + "'}";
    }}
