package labb1.del2.vehicleparts.flatbeds;

public class SimpleFlatbed implements IFlatbed {
    private boolean isLowered;

    public SimpleFlatbed() {
        isLowered = false;
    }

    @Override
    public void raiseFlatbed() {
        isLowered = false;
    }

    @Override
    public void lowerFlatbed() {
        isLowered = true;
    }

    @Override
    public boolean isLowered() {
        return this.isLowered;
    }
}
