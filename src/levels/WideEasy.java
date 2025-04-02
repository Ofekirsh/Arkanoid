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
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    /**
     * Number of balls.
     * @return 10 (int)
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Initial ball velocities.
     * @return listVelocity (the list of velocity)
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>(numberOfBalls());
        int angle = 300;
        for (int i = 0; i < numberOfBalls(); i++) {
            listVelocity.add(Velocity.fromAngleAndSpeed(angle, 5));
            angle += 12;
            if (i == 4) {
                angle += 12;
            }
        }
        return listVelocity;
    }

    /**
     * Paddle speed.
     * @return 2 (int)
     */
    public int paddleSpeed() {
        return 2;
    }

    /**
     * Paddle width.
     * @return 600 (int)
     */
    public int paddleWidth() {
        return 600;
    }
    /**
     * Level name.
     * @return string
     */
    public String levelName() {
        return "Wide Easy";
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
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 50, d.getWidth(), d.getHeight());
                d.setColor(new Color(239, 231, 176));
                d.fillCircle(150, 150, 60);
                for (int x = 10; x <= 780; x += 10) {
                    d.drawLine(150, 150, x, 250);
                }
                //d.setColor(Color.RED);
                d.setColor(new Color(236, 215, 73));
                d.fillCircle(150, 150, 50);
                //d.setColor(new Color(255, 255, 24));
                d.setColor(Color.YELLOW);
                d.fillCircle(150, 150, 40);

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
        int x = 25;
        List<Color> listColor = Arrays.asList(Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
                Color.PINK, Color.cyan);
        List<Block> listBlocks = new ArrayList<>(numberOfBlocksToRemove());
        for (int i = 0, k = 0; i < numberOfBlocksToRemove(); i++) {
            listBlocks.add(new Block(new Rectangle(new Point(x, 220), 50, 30), listColor.get(k)));
            if (i % 2 == 1 && i < 7) {
                k++;
            }
            if (i >= 8 && i % 2 == 0) {
                k++;
            }
            x += 50;
        }
        return listBlocks;
    }
    /**
     * Number of blocks to remove.
     * @return 1 (int)
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
