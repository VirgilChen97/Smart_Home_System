package util;

import javax.swing.*;

/**
 * Description : The class is used to achieve all the error check function.
 *
 * @author YinChen, Lejin Bai
 * @version 1.7
 *
 */
public class ErrorUtil {

    /**
     * This method check if the JTextField is empty
     * @param tf the JTextField need check
     * @param input
     * @return safe return true, unsafe return false
     */
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if(0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " can't be empty!");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    /**
     * This method check if the JTextField is empty and if it's a number.
     * @param tf the JTextField need check
     * @param input
     * @return safe return true, unsafe return false
     */
    public static boolean checkNumber(JTextField tf, String input) {
        if(!checkEmpty(tf, input))
            return false;
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " must be integer");
            tf.grabFocus();
            return false;
        }
    }

    /**
     * This method check if the JTextField is empty and if the number inputted is 0
     * @param tf the JTextField need check
     * @param input
     * @return safe return true, unsafe return false
     */
    public static boolean checkZero(JTextField tf, String input) {
        if(!checkNumber(tf, input))
            return false;
        String text = tf.getText().trim();

        if(0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " can't equal to 0");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    public static boolean checkTable(JTable table) {
        for(int i=0;i<table.getRowCount();i++) {
            for(int j=0;j<table.getColumnCount();j++) {
                System.out.println(table.getValueAt(i, j));
                if(table.getValueAt(i, j)==null){
                    JOptionPane.showMessageDialog(null, "Table can't have space.", "alert", JOptionPane.ERROR_MESSAGE);
                    table.grabFocus();
                    return false;
                }
            }
        }
        return true;
    }
}
