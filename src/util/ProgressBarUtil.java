package util;

import javax.swing.*;
import java.awt.*;

/**
 * Description : To paint and show a circle progressBar with the percentage calculation.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class ProgressBarUtil extends JPanel{

    private int minimumProgress;
    private int maximumProgress;
    private int progress;
    private Float cost;
    private Float budget;
    private String progressText;
    private Color backgroundColor;
    private Color foregroundColor;
    private String type;

    /**
     * Constructor to build a circle progress bar.
     * @param type The meter type.
     * @param cost The cost from the beginning of this month.
     * @param budget The budget of this account for this month.
     */
    public ProgressBarUtil(String type, Float cost, Float budget) {
        this.type = type;
        this.cost = cost;
        this.budget = budget;
        minimumProgress = 0;
        maximumProgress = 100;
        progressText = "0%";
        compareBudget();
    }

    public void updateData(Float cost, Float budget){
        this.cost = cost;
        this.budget = budget;
        compareBudget();
    }

    /**
     * A painting method to make graphs
     * @param g an object to paint something
     */
    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D graphics2d = (Graphics2D) g;
        //anti-aliasing
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        int fontSize = 0;
        if(getWidth() >= getHeight()) {
            x = (getWidth() - getHeight()) / 2 + 25;
            y = 25;
            width = getHeight() - 50;
            height = getHeight() - 50;
            fontSize = getWidth() / 20;
            fontSize = getWidth() / 10;
        } else {
            x = 25;
            y = (getHeight() - getWidth()) / 2 + 25;
            width = getWidth() - 50;
            height = getWidth() - 50;
            fontSize = getHeight() / 20;
            fontSize = getHeight() / 10;
        }
        graphics2d.setStroke(new BasicStroke(10.0f));
        graphics2d.setColor(backgroundColor);
        graphics2d.drawArc(x, y, width, height, 0, 360);
        graphics2d.setColor(foregroundColor);
        graphics2d.drawArc(x, y, width, height, 90, -(int) (360 * ((progress * 1.0) / (maximumProgress - minimumProgress))));
        graphics2d.setFont(new Font("Arial", Font.BOLD, fontSize));
        FontMetrics  fontMetrics = graphics2d.getFontMetrics();
        int digitalWidth = fontMetrics.stringWidth(progressText);
        int digitalAscent = fontMetrics.getAscent();
        graphics2d.setColor(foregroundColor);
        graphics2d.drawString(progressText,  getWidth() / 2 - digitalWidth / 2, getHeight() / 2 + digitalAscent / 2);
    }


    /**
     * compute percentage (cost/budgets) of both gas and electric.
     */
    public void compareBudget() {
        progress = (int)((100 * cost / budget));
        System.out.println("the Percent of " + this.type + " is: " + progress);
        setProgress(progress);
        setBackgroundColor(ColorUtil.blueColor);
        setForegroundColor(ColorUtil.getByPercentage(progress));
    }

    public int getProgress() {
        return progress;
    }

    /**
     * Set the progress hint in the progressBAr
     * @param progress the percentage (cost/budgets)
     */
    public void setProgress(int progress) {

        if(progress >= minimumProgress && progress <= maximumProgress)
            this.progress = progress;
        if(progress > maximumProgress)
            this.progress = maximumProgress;

        this.progressText = type + " \n" + String.valueOf(progress + "%");

        this.repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.repaint();
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        this.repaint();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}