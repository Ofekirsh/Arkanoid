package geometry;

/**
 * Class check if smaller epsilon.
 */
public class ArithmeticOnDouble {
    /**
     * Check if smaller epsilon.
     *
     * @param x (double)
     * @param y (double)
     * @return true or false (Boolean)
     */
    public static Boolean equal(double x, double y) {
        return Math.abs(x) - Math.abs(y) < 0.000001;
    }
}


