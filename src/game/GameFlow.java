package game;
import java.util.List;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import levels.EndScreen;
import utils.Counter;


/**
 * The type Game flow.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;


    private final Counter score = new Counter();
    private final Counter lives = new Counter();

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        lives.increase(3);

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives);
            level.initialize();
            level.run();
            if (lives.getValue() < 0) {
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new EndScreen(score, lives)));
    }
}