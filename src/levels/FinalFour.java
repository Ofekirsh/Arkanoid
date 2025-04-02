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
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    /**
     * Number of balls.
     * @return 3 (int)
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * Initial ball velocities.
     * @return listVelocity (the list of velocity)
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>(numberOfBalls());
        int angle = 330;
        for (int i = 0; i < numberOfBalls(); i++) {
            listVelocity.add(Velocity.fromAngleAndSpeed(angle, 5));
            angle += 30;
        }
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
        return "Final Four";
    }
    /**
     * Get background.
     * Use in this method anonymous class.
     * @return Sprite
     */
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#1788d0"));
                d.fillRectangle(0, 50, d.getWidth(), d.getHeight());

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(200 + i * 10, 400, 230 + i * 10, 600);
                }

                d.setColor(new Color(100, 100, 100));
                d.fillCircle(200, 400, 25);
                d.fillCircle(220, 420, 27);
                d.setColor(new Color(150, 150, 150));
                d.fillCircle(240, 390, 29);
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(260, 420, 23);
                d.fillCircle(280, 400, 35);

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(500 + i * 10, 350, 530 + i * 10, 600);
                }

                d.setColor(new Color(100, 100, 100));
                d.fillCircle(500, 350, 25);
                d.fillCircle(520, 370, 27);
                d.setColor(new Color(150, 150, 150));
                d.fillCircle(540, 340, 29);
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(560, 370, 23);
                d.fillCircle(580, 350, 35);
            }

            public void timePassed() {
            }

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
        int x = 25, y = 120, width = 50, height = 30;
        List<Color> listColor = Arrays.asList(Color.red, Color.orange, Color.yellow, Color.green,
                Color.blue, Color.pink, Color.cyan);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Rectangle(new Point(x, y), width, height),
                        listColor.get(i));
                listBlock.add(block);
                x += width;
            }
            y += height;
            x = 25;
        }
        return listBlock;
    }
    /**
     * Number of blocks to remove.
     * @return 1 (int)
     */
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
