package view.common;

import listener.User.controlListener;
import listener.common.LoginButtonLisener;

import javax.swing.*;

/**
 * Login UI, user can login here
 * @author Yifeng Chen
 * @version 1.0
 */
public class LoginUI {
    private JLabel userLabel;
    JTextField userText;
    JLabel passwordLabel;
    JPasswordField passwordText;
    JButton loginButton;
    JFrame frame;

    public LoginUI() {
        frame = new JFrame("Management System");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
        userText.addKeyListener(new controlListener());

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new LoginButtonLisener(userText,passwordText,frame));
        panel.add(loginButton);

    }

    public static void main(String[] args) {
        new LoginUI();
    }

}