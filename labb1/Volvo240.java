import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, Color.BLACK, 100.0, "Volvo240");
    }

    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}
