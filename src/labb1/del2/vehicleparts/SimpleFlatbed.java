package labb1.del2.vehicleparts;

public class SimpleFlatbed implements IFlatbed {
    private boolean isLowered;

    public SimpleFlatbed() {
        isLowered = false;
    }

    /**
     * Raises the flatbed.
     */
    @Override
    public void raiseFlatbed() {
        isLowered = false;
    }

    /**
     * Lowers the flatbed.
     */
    @Override
    public void lowerFlatbed() {
        isLowered = true;
    }

    /**
     * Returns true if the flatbed is lowered.
     * @return True if the flatbed is lowered, false otherwise.
     */
    @Override
    public boolean isLowered() {
        return this.isLowered;
    }
}
