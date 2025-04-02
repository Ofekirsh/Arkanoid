package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * Do one frame.
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        //d.drawText(10, d.getHeight() / 2, key, 32);
        if (this.sensor.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    /**
     * Should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}