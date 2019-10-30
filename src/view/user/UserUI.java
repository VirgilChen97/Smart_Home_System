/*
 * Created by JFormDesigner on Wed May 23 20:32:16 CST 2018
 */

package view.user;

import listener.common.JumpListener;
import listener.common.LogoutListener;
import model.Entity.Account;
import model.Entity.Tariff;
import model.Service.AccountHistoryService;
import model.Service.AccountService;
import model.Service.Implimentation.AccountHistoryServiceImpl;
import model.Service.Implimentation.AccountServiceImpl;
import model.Service.Implimentation.TariffServiceImpl;
import model.Service.TariffService;
import util.ProgressBarUtil;
import util.UserTableUtil;
import view.common.UpdateFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the user main interface
 * It allows users to see how much they use the budget
 * @author  Lejin Bai, Yin Chen
 * @version 2.3
 */
public class UserUI extends UpdateFrame {

    AccountService as = new AccountServiceImpl();
    AccountHistoryService ahs = new AccountHistoryServiceImpl();
    TariffService ts = new TariffServiceImpl();

    /**
     * This constructor create the GUI and define the event handler.
     * @param account
     */
    public UserUI(Account account) {
        this.account = account;
        initComponents();
        updateCost();
        addbar();
        datapane.setViewportView(new JTable(UserTableUtil.getTable(as.retrieve(account.getAccountID()))));
        recharge.addActionListener(new JumpListener("RechargeUI",this,account));
        History.addActionListener(new JumpListener("UserHistoryUI",this, account.getAccountID()));
        budget.addActionListener(new JumpListener("UserBudgetUI",this,account));
        logout.addActionListener(new LogoutListener(this));
        mailbox.addActionListener(new JumpListener("Mailbox",this,account));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This method update the cost.
     */
    private void updateCost(){
        Tariff tarrif = ts.list();
        gascost = (Float.parseFloat(account.getGasMeterReading()) - Float.parseFloat(account.getGasMeterReadingHis()))
                * Float.parseFloat(tarrif.getGas());
        eleccost = (Float.parseFloat(account.getElectricMeterReading()) - Float.parseFloat(account.getElectricMeterReadingHis()))
                * Float.parseFloat(tarrif.getElectricity());
    }

    /**
     * This method add components to GUI
     */
    private void addbar(){
        gasbar = new ProgressBarUtil("Gas",gascost,Float.parseFloat(account.getGasBudgets()));
        elecbar = new ProgressBarUtil("Elec",eleccost,Float.parseFloat(account.getElectricBudgets()));
        panel1.add(gasbar);
        panel2.add(elecbar);
    }

    /**
     * This method define the components and initialize them.
     */
    private void initComponents() {
        label1 = new JLabel();
        label2 = new JLabel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        datapane = new JScrollPane();
        panel3 = new JPanel();
        recharge = new JButton();
        History = new JButton();
        budget = new JButton();
        mailbox = new JButton();
        logout = new JButton();

        //======== this ========
        setTitle("Smart home monitoring system");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Welcome to monitoring system");
        label1.setFont(new Font("Arial", Font.PLAIN, 24));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 20), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Here is your status");
        label2.setFont(new Font("Arial", Font.PLAIN, 14));
        label2.setForeground(Color.darkGray);
        contentPane.add(label2);
        label2.setBounds(20, 45, 330, 28);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(1, 1));
        }
        contentPane.add(panel1);
        panel1.setBounds(25, 90, 190, 190);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(1, 1));
        }
        contentPane.add(panel2);
        panel2.setBounds(215, 90, 190, 190);
        contentPane.add(datapane);
        datapane.setBounds(35, 285, 360, 60);

        //======== panel3 ========
        {
            panel3.setLayout(new GridLayout(1, 4, 10, 10));

            //---- recharge ----
            recharge.setText("Recharge");
            recharge.setFont(new Font("Arial", Font.PLAIN, 12));
            panel3.add(recharge);

            //---- History ----
            History.setText("History");
            History.setFont(new Font("Arial", Font.PLAIN, 12));
            panel3.add(History);

            //---- budget ----
            budget.setText("Budget");
            budget.setFont(new Font("Arial", Font.PLAIN, 12));
            panel3.add(budget);

            //---- mailbox ----
            mailbox.setText("Mail");
            mailbox.setFont(new Font("Arial", Font.PLAIN, 12));
            panel3.add(mailbox);
        }
        contentPane.add(panel3);
        panel3.setBounds(35, 350, 360, 30);

        //---- logout ----
        logout.setText("Log out");
        logout.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(logout);
        logout.setBounds(355, 400, 75, logout.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(435, 435));
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void update() {
        datapane.setViewportView(new JTable(UserTableUtil.getTable(as.retrieve(account.getAccountID()))));
        updateCost();
        gasbar.updateData(gascost, Float.parseFloat(account.getGasBudgets()));
        elecbar.updateData(eleccost, Float.parseFloat(account.getElectricBudgets()));
    }

    private JLabel label1;
    private JLabel label2;
    private JPanel panel1;
    private JPanel panel2;
    private JScrollPane datapane;
    private JPanel panel3;
    private JButton recharge;
    private JButton History;
    private JButton budget;
    private JButton mailbox;
    private JButton logout;
    Account account;
    ProgressBarUtil gasbar;
    ProgressBarUtil elecbar;
    Float gascost,eleccost;

}
