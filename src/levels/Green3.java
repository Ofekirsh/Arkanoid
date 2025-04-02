package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    private static final int WIDTH = 800;
    /**
     * Number of balls.
     * @return 2 (int)
     */
    public int numberOfBalls() {
        return 2;
    }
    /**
     * Initial ball velocities.
     * @return listVelocity (the list of velocity)
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>(numberOfBalls());
        listVelocity.add(Velocity.fromAngleAndSpeed(330, 9));
        listVelocity.add(Velocity.fromAngleAndSpeed(30, 9));
        return listVelocity;
    }

    /**
     * Paddle speed.
     * @return 12 (int)
     */
    public int paddleSpeed() {
        return 12;
    }
    /**
     * Paddle width.
     * @return 100 (int)
     */
    public int paddleWidth() {
        return 100;
    }
    /**
     * Level name.
     * @return string
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * Get background.
     * Use in this method anonymous class.
     * @return Sprite
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(50, 200, 50));
                d.fillRectangle(0, 50, d.getWidth(), d.getHeight());

                d.setColor(Color.BLACK);
                d.fillRectangle(110, 200, 10, 200);

                d.setColor(Color.RED);
                d.fillCircle(115, 200, 12);

                d.setColor(Color.ORANGE);
                d.fillCircle(115, 200, 8);

                d.setColor(Color.WHITE);
                d.fillCircle(115, 200, 3);

                d.setColor(Color.BLACK);
                d.fillRectangle(100, 400, 30, 200);

                d.setColor(Color.BLACK);
                d.fillRectangle(65, 450, 100, 200);

                d.setColor(Color.WHITE);

                for (int x = 0; x < 5; ++x) {
                    for (int y = 0; y < 5; ++y) {
                        d.fillRectangle(75 + x * 18, 460 + y * 32, 10, 25);
                    }
                }
            }

            @Override
            public void timePassed() {

            }

            @Override
            public void addToGame(GameLevel gameLevel) {

            }
        };
    }
    /**
     * Blocks.
     * @return listBlocks (the list of blocks)
     */
    public List<Block> blocks() {
        List<Block> listBlock = new ArrayList<>(numberOfBlocksToRemove());
        Block block;
        int counter = 10;
        double width = 50, height = 30;
        double x = WIDTH - 25 - width, y = 100;
        List<Color> listColor = Arrays.asList(Color.gray, Color.red, Color.yellow, Color.blue,
                Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < counter; j++) {
                block = new Block(new Rectangle(new Point(x, y), width, height),
                        listColor.get(i));
                listBlock.add(block);
                x = x - width;
            }
            x = WIDTH - 25 - width;
            y = y + height;
            counter--;
        }
        return listBlock;
    }
    /**
     * Number of blocks to remove.
     * @return 1 (int)
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
