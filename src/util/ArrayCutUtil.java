package util;

import java.util.Arrays;

/**
 * Some useful functions about arrays
 * @author Yifeng Chen
 * @version 1.0
 */
public class ArrayCutUtil {
    /**
     * This method cut null items at the end of an array
     *
     * @param data 2D array
     * @return 2D array without null
     */
    public static String[][] cutarray(String[][] data) {
        int number = 0;
        while (data[number] != null)
            number++;
        String retArray[][] = new String[number][];
        int index = 0;
        for (int i = 0; i < number; i++) {
            if (!data[i][0].equals("\0!")) {
                retArray[index++] = data[i];
            }
        }

        retArray = Arrays.copyOf(retArray, index);
        return retArray;
    }
}
