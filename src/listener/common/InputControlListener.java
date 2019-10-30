package listener.common;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is a listener which listen JTextField in the Recharge UI
 * User only can type in number in JTextField
 * @author Lejin Bai
 * @version 2.0
 */
public class InputControlListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * This method control user that can only type in number
     * @param arg0
     */
    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        int Keychar= arg0.getKeyChar();
        if((Keychar >= KeyEvent.VK_0 && Keychar <= KeyEvent.VK_9)||Keychar =='.'){}
        else{
            arg0.consume();
        }


    }
}
