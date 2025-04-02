package levels;

import java.awt.Color;
import java.util.ArrayList;
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
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {

    /**
     * Number of balls.
     *
     * @return 1 (int)
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Initial ball velocities.
     *
     * @return listVelocity (the list of velocity)
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>(numberOfBalls());
        listVelocity.add(Velocity.fromAngleAndSpeed(0, 5));
        return listVelocity;
    }

    /**
     * Paddle speed.
     *
     * @return 5 (int)
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Paddle width.
     *
     * @return 100 (int)
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * Level name.
     *
     * @return string
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Get background.
     * Use in this method anonymous class.
     *
     * @return Sprite
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 50, d.getWidth(), d.getHeight());

                d.setColor(Color.BLACK);
                d.drawCircle(400, 215, 50);
                d.drawCircle(400, 215, 100);
                d.drawCircle(400, 215, 150);

                d.drawLine(220, 215, 370, 215);
                d.drawLine(430, 215, 580, 215);
                d.drawLine(400, 35, 400, 185);
                d.drawLine(400, 245, 400, 395);
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
     *
     * @return listBlocks (the list of blocks)
     */
    public List<Block> blocks() {
        Block block = new Block(new Rectangle(new Point(375, 190), 50, 50), Color.RED);
        List<Block> listBlocks = new ArrayList<>(numberOfBlocksToRemove());
        listBlocks.add(block);
        return listBlocks;
    }

    /**
     * Number of blocks to remove.
     *
     * @return 1 (int)
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
