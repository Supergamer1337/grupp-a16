package labb1.del2.vehicleparts;

public interface IFlatbed {
    /**
     * Raises the flatbed.
     */
    void raiseFlatbed();

    /**
     * Lowers the flatbed.
     */
    void lowerFlatbed();

    /**
     * Returns the current state of the flatbed.
     * @return True if the flatbed is lowered, false if it is raised.
     */
    boolean isLowered();
}
