package view.user;

import listener.User.BudgetListener;
import listener.common.InputControlListener;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import model.Entity.Account;

/**
 * UI for user to change budget
 * @author Yuhang Fan
 * @version 1.0
 */
public class UserBudgetUI extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JPanel panel_3;
    private JPanel panel_4;
    private JButton btnGoBack;
    private JButton btnSave;
    private JTable table;
    private JScrollPane dataPane;
    private JLabel Type;
    private JLabel ID;
    private JLabel ID1;
    private JLabel ID2;
    private JLabel Budget;
    private JTextField text1;
    private JTextField text2;
    private JLabel type1;
    private JLabel type2;

    /**
     * Create the frame.
     */

    public UserBudgetUI (Account account) {
        super("Smart Home Management System");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);

        panel_3 = new JPanel();
        contentPane.add(panel_3, BorderLayout.SOUTH);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnSave = new JButton("Save");
        panel_3.add(btnSave);

        Type = new JLabel("Type");
        ID = new JLabel("ID");
        ID1 = new JLabel(account.getGasMeterID());
        ID2 = new JLabel(account.getElectricMeterID());
        Budget = new JLabel("Budget");
        type1 =new JLabel("Gas");
        type2 =new JLabel("Electricity");
        text1 = new JTextField(account.getGasBudgets());
        text2 = new JTextField(account.getElectricBudgets());
        text1.addKeyListener(new InputControlListener());
        text2.addKeyListener(new InputControlListener());

        panel.setLayout(new GridLayout(3, 3, 0, 0));
        panel.add(ID);
        panel.add(Type);
        panel.add(Budget);
        panel.add(ID1);
        panel.add(type1);
        panel.add(text1);
        panel.add(ID2);
        panel.add(type2);
        panel.add(text2);
        btnSave.addActionListener(new BudgetListener(text1,text2,account));
        setLocationRelativeTo(getOwner());
        this.setVisible(true);
    }
}
