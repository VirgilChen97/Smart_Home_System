package listener.common;

import view.common.LoginUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controller.Listener handle log out events
 * @author Yifeng Chen
 * @version 1.0
 */
public class LogoutListener implements ActionListener {

    private JFrame mainWindow;
    public LogoutListener(JFrame mainWindow){
        this.mainWindow = mainWindow;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.dispose();
        new LoginUI();
    }
}
