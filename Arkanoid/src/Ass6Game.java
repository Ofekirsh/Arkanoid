import biuoop.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ass6Game.
 */
public class Ass6Game {
    /**
     * run the game.
     *
     * @param args as a command line
     */
    public static void main(String[] args) {
        int[] num = stringToInt(args);
        List<LevelInformation> listGameLevel = new ArrayList<>();
        for (int n : num) {
            if (n == 1) {
                listGameLevel.add(new DirectHit());
            }
            if (n == 2) {
                listGameLevel.add(new WideEasy());
            }
            if (n == 3) {
                listGameLevel.add(new Green3());
            }
            if (n == 4) {
                listGameLevel.add(new FinalFour());
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