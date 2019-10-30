package util;

import java.awt.*;

/**
 * Description : The class contain all the color operation.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class ColorUtil {
    public static Color blueColor = Color.decode("#3399FF");

    /**
     * Set the foreground color the progressBar shows according to the rule: percentage 0 -> 100 leads to color green -> red.
     * @param per the percentage (cost/budgets)
     * @return the color according to the rule
     */
    public static Color getByPercentage(int per) {
        if(per > 100)
            per = 100;
        int r = 51;
        int g = 255;
        int b = 0;
        float rate = per / 100f;
        r = (int) ((255 - 51) * rate + 51);
        g = 255 - r + 51;
        Color color = new Color(r, g, b);
        return color;
    }
}

