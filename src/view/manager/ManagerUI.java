/*
 * Created by JFormDesigner on Mon May 21 11:45:47 CST 2018
 */

package view.manager;

import listener.common.JumpListener;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the manager main interface
 * It allows managers to choose which function they want to use
 * @author Yifeng Chen, Lejin Bai
 * @version 2.3
 */
public class ManagerUI extends JFrame {

    public ManagerUI() {
        initComponents();
        UserManagement.addActionListener(new JumpListener("ManagementUI",this,null));
        UserHistory.addActionListener(new JumpListener("UserHistoryUI",this,"manager"));
        settariff.addActionListener(new JumpListener("TariffUI",this,null));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * This method define the components and initialize them.
     */
    private void initComponents() {
        UserManagement = new JButton();
        UserHistory = new JButton();
        settariff = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setTitle("Smart Home Management System");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- UserManagement ----
        UserManagement.setIcon(new ImageIcon("resources/icons/UserManagement.png"));
        UserManagement.setForeground(new Color(46, 117, 182));
        UserManagement.setBackground(new Color(46, 117, 182));
        contentPane.add(UserManagement);
        UserManagement.setBounds(220, 120, 175, 260);

        //---- UserHistory ----
        UserHistory.setIcon(new ImageIcon("resources/icons/UserHistory.png"));
        UserHistory.setForeground(new Color(46, 117, 182));
        UserHistory.setBackground(new Color(46, 117, 182));
        contentPane.add(UserHistory);
        UserHistory.setBounds(40, 120, 175, 260);

        //---- settariff ----
        settariff.setIcon(new ImageIcon("resources/icons/SetTarrif.png"));
        settariff.setForeground(new Color(46, 117, 182));
        settariff.setBackground(new Color(46, 117, 182));
        contentPane.add(settariff);
        settariff.setBounds(400, 120, 175, 260);

        //---- label1 ----
        label1.setText("Welcome! Manager");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        contentPane.add(label1);
        label1.setBounds(40, 30, 215, 30);

        //---- label2 ----
        label2.setText("What would you like to do?");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        contentPane.add(label2);
        label2.setBounds(40, 65, 215, 30);

        contentPane.setPreferredSize(new Dimension(620, 450));
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JButton UserManagement;
    private JButton UserHistory;
    private JButton settariff;
    private JLabel label1;
    private JLabel label2;
    public static void main(String[] args) {
        new ManagerUI().setVisible(true);

    }
}
