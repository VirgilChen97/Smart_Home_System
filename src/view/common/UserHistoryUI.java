package view.common;

import model.Entity.AccountHistory;
import listener.User.controlListener;
import listener.common.HistoryButtonListener;
import listener.common.HistoryEnterListener;
import listener.common.SearchDayListener;
import model.Service.AccountHistoryService;
import model.Service.Implimentation.AccountHistoryServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class creates the user history interface, it allows both customer and manager to look at their history.
 * @author  Yuhang Fan
 * @version 2.3
 */
public class UserHistoryUI extends UpdateFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JTable data;
    private JScrollPane dataPane;
    private JButton btnDaily;
    private JButton btnWeekly;
    private JButton btnMonthly;
    private JPanel panel_3;
    private JButton btnGoBack;
    private AccountHistory accountHistory;
    private JTextField id;
    private JLabel year;
    private JLabel month;
    private JLabel day;
    private JTextField TextYear;
    private JTextField TextMonth;
    private JTextField TextDay;
    private JButton btnGo;

    /**
     * This is the constructor. Other user interfaces can directly create this class's obejct to initiate.
     * @param mode String. The interface provide both manager and user to operate, so this can choose the mode.
     */

    public UserHistoryUI(String mode) {
        dataPane = new JScrollPane();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        panel.add(new JLabel("ID:"));
        id = new JTextField();
        id.addKeyListener(new controlListener());
        id.setPreferredSize(new Dimension(60,25));
        if (mode.equals("manager")) {
            id.addKeyListener(new HistoryEnterListener(id, dataPane));
            panel.add(id);
        }
        else{
            id.setText(mode);
            id.setEnabled(false);
        }

        btnDaily = new JButton("Daily");
        btnDaily.addActionListener(new HistoryButtonListener(dataPane,id,"Day"));
        panel.add(btnDaily);

        btnWeekly = new JButton("Weekly");
        btnWeekly.addActionListener(new HistoryButtonListener(dataPane,id,"Week"));
        panel.add(btnWeekly);

        btnMonthly = new JButton("Monthly");
        btnMonthly.addActionListener(new HistoryButtonListener(dataPane,id,"Month"));
        panel.add(btnMonthly);

        year =new JLabel("Search specific day: ");
        month = new JLabel("--");
        day = new JLabel("--");

        TextDay =new JTextField();
        TextDay.setColumns(5);
        TextDay.addKeyListener(new controlListener());
        TextMonth =new JTextField();
        TextMonth.setColumns(5);
        TextMonth.addKeyListener(new controlListener());
        TextYear = new JTextField();
        TextYear.setColumns(5);
        TextYear.addKeyListener(new controlListener());

        panel.add(year);
        panel.add(TextYear);
        panel.add(month);
        panel.add(TextMonth);
        panel.add(day);
        panel.add(TextDay);
        btnGo = new JButton("Go");
        panel.add(btnGo);
        btnGo.addActionListener(new SearchDayListener(dataPane,id,TextYear,TextMonth,TextDay,data));

        panel_3 = new JPanel();
        contentPane.add(panel_3, BorderLayout.SOUTH);

        btnGoBack = new JButton("Go back");
        panel_3.add(btnGoBack);

        contentPane.add(dataPane,BorderLayout.CENTER);

        update();

        setLocationRelativeTo(getOwner());
        this.setVisible(true);
    }

    public void update() {
        AccountHistoryService ahs = new AccountHistoryServiceImpl();

        accountHistory = ahs.histories("1");
    }
}
