/**
 * The counter type.
 */
public class Counter {
    private int counter;

    /**
     * Initialize the counter to zero.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number (int)
     */
    void increase(int number) {
        counter = getValue() + number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number (int)
     */
    void decrease(int number) {
        counter = getValue() - number;
    }

    /**
     * Get current count.
     *
     * @return counter (int)
     */
    int getValue() {
        return counter;
    }
}