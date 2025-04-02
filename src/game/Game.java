package game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import animation.AnimationRunner;
import biuoop.GUI;
import interfaces.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;

/**
 * Ass6Game.
 */
public class Game {
    /**
     * run the game.
     *
     * @param args as a command line
     */
    public static void main(String[] args) {
        int[] num = stringToInt(args);
        List<LevelInformation> listGameLevel = new ArrayList<>();
        for (int n : num) {
            switch (n) {
                case 1:
                    listGameLevel.add(new DirectHit());
                    break;
                case 2:
                    listGameLevel.add(new WideEasy());
                    break;
                case 3:
                    listGameLevel.add(new Green3());
                    break;
                case 4:
                    listGameLevel.add(new FinalFour());
                    break;
                default: break;
            }
        }
        if (listGameLevel.isEmpty()) {
            listGameLevel = Arrays.asList(new DirectHit(), new WideEasy(), new Green3(), new FinalFour());
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor());
        gameFlow.runLevels(listGameLevel);
        gui.close();
    }

    /**
     * String to int.
     * @param numbers (the numbers)
     * @return arrOfInt
     */
    private static int[] stringToInt(String[] numbers) {
        int len = numbers.length;
        int[] arrOfInt = new int[len];
        try {
            for (int i = 0; i < len; i++) {
                arrOfInt[i] = Integer.parseInt(numbers[i]);
            }
        } catch (Exception ignored) {
        }
        return arrOfInt;
    }
}