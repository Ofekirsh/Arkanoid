package game;
import java.awt.Color;
import java.util.Random;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.SpriteCollection;
import utils.Counter;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    /**
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;


    private final Counter blockCounter;
    private final BlockRemover blockRemover;

    private final Counter ballCounter;
    private final BallRemover ballRemover;

    private final AnimationRunner runner;
    private boolean running;
    private final ScoreTrackingListener scoreTrackingListener;
    private final Counter score;
    private final Counter lives;

    private final LevelInformation levelInformation;
    private final KeyboardSensor keyboardSensor;

    /**
     * Initializes a new Game.
     *
     * @param levelInformation the level information
     * @param keyboardSensor   the keyboard sensor
     * @param runner           the runner
     * @param score            the score
     * @param lives            the lives
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner runner,
                     Counter score, Counter lives) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();

        blockCounter = new Counter();
        blockRemover = new BlockRemover(this, blockCounter);

        ballCounter = new Counter();
        ballRemover = new BallRemover(this, ballCounter);

        this.running = false;
        this.runner = runner;

        this.score = score;
        scoreTrackingListener = new ScoreTrackingListener(score);

        this.lives = lives;

        this.keyboardSensor = keyboardSensor;
        this.levelInformation = levelInformation;

    }

    /**
     * Add colidable.
     *
     * @param c (Collidable)
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s (Sprite)
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize limit, balls, rectangle and paddle.
     */
    public void initialize() {
        //levelInformation.getBackground().addToGame(this);
        addSprite(levelInformation.getBackground());
        addBalls();
        createRectangle();
        frameBlocks();
        new ScoreIndicator(score).addToGame(this);
        new Paddle(runner.getGui().getKeyboardSensor(), levelInformation.paddleSpeed(),
                levelInformation.paddleWidth()).addToGame(this);
    }

    /**
     * Add rectangle to the game.
     */
    private void createRectangle() {
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            blockCounter.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Add balls to the game.
     */
    private void addBalls() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 550), 5, Color.black);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.setStartCoordinate(0);
            ball.setEndCoordinate(WIDTH);
            ball.addToGame(this);
            ballCounter.increase(1);
            ball.setGameEnvironment(environment);
        }
    }

    /**
     * Random color.
     *
     * @return newColor
     */
    @SuppressWarnings("unused")
    private Color randColor() {
        Random rand = new Random();
        int a = rand.nextInt(255);
        int b = rand.nextInt(255);
        int c = rand.nextInt(255);
        return new Color(a, b, c);
    }

    /**
     * Frame blocks.
     */
    private void frameBlocks() {
        final double border = 25;
        new Block(new Rectangle(new Point(0, 30), border, HEIGHT), Color.GRAY).addToGame(this);
        //rightSide
        new Block(new Rectangle(new Point(WIDTH - border, 30), border, HEIGHT), Color.GRAY).addToGame(this);
        //upperSide
        new Block(new Rectangle(new Point(0, 30), WIDTH, border), Color.GRAY).addToGame(this);
        //underSide
        Block blockUnder = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, border), Color.WHITE);
        blockUnder.addToGame(this);
        blockUnder.addHitListener(ballRemover);

    }

    /**
     * Remove collidable.
     *
     * @param c (Collidable)
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            environment.removeCollidable(c);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s (Sprite)
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            sprites.removeSprite(s);
        }
    }

    /**
     * Should stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one frame.
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(600, 20, "Level Name: " + levelInformation.levelName(), 15);
        d.drawText(50, 20, "Lives: " + lives.getValue(), 15);
        if (ballCounter.getValue() == 0) {
            lives.decrease(1);
            addBalls();
            if (lives.getValue() != -1) {
                runner.run(new CountdownAnimation(sprites, 2, 3));
            }
        }
        if (lives.getValue() < 0) {
            running = false;
        }
        // check if all the blocks are hit.
        if (blockCounter.getValue() == 0) {
            score.increase(100);
            running = false;
        }
        if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                    new PauseScreen()));
            runner.run(new CountdownAnimation(sprites, 2, 3));

        }

        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
    }

    /**
     * Run.
     */
    public void run() {
        this.running = true;
        runner.run(new CountdownAnimation(sprites, 2, 3));
        this.runner.run(this);
    }
}

