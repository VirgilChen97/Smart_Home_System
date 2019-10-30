package view.user;

import javax.swing.*;
import java.awt.*;

import model.Entity.Account;
import listener.User.EnsureListener;
import listener.common.InputControlListener;
import model.Service.Implimentation.AccountServiceImpl;

/**
 * This class create a recharge UI for user,
 * User can type in the TextField to recharge his account
 * @author Pingzhou Li
 * @version 1.0
 */
public class RechargeUI extends JFrame {
    private JPanel jp1, jp2, jp3;
    private JLabel jlb1, jlb2, Message1, Message2,Message3;
    private JButton jb1;
    private JTextField jtf1, jtf2;
    private String pounds;

    /**
     * This method create a recharge UI for user,
     * User can type in the TextField to recharge his account.
     * @param account
     */
    public RechargeUI(Account account) {


        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        Message1 = new JLabel("Smart Home Monitoring System", JLabel.CENTER);
        Message2 = new JLabel("Pounds", JLabel.CENTER);
        Message3 = new JLabel("Pounds",JLabel.CENTER);
        jlb1 = new JLabel("Balance:");
        jlb2 = new JLabel("Amount:");

        pounds = new AccountServiceImpl().retrieve(account.getAccountID()).getBalance();
        jtf1 = new JTextField(10);
        jtf1.setText(pounds);
        jtf1.setEditable(false);
        jtf2 = new JTextField(10);
        jtf2.addKeyListener(new InputControlListener());

        jb1 = new JButton("Confirm");
        jb1.addMouseListener(new EnsureListener(jtf1, jtf2, Message2, account));
        this.setLayout(new GridLayout(5, 1));

        jp1.add(jlb1);
        jp1.add(jtf1);
        jp1.add(Message2);

        jp2.add(jlb2);
        jp2.add(jtf2);
        jp2.add(Message3);

        jp3.add(jb1);

        this.add(Message1);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setSize(500, 300);
        this.setTitle("Recharge");
        this.setVisible(true);

        setLocationRelativeTo(getOwner());
    }
}
