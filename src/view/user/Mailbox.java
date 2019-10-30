package view.user;

import model.Entity.Account;
import model.Entity.AccountHistory;
import model.Service.AccountHistoryService;
import model.Service.Implimentation.AccountHistoryServiceImpl;
import listener.common.selectListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Mail box to check mail
 * @author Pingzhou Li
 * @version 1.0
 */
public class Mailbox extends JFrame{
    private JPanel jp1;
    private JScrollPane jp2;
    private JLabel jlb1,jlb2;
    private JComboBox jcb1;
    private JTable jtb1;
    private DefaultTableModel model=null;

    AccountHistoryService ahs = new AccountHistoryServiceImpl();

    /**
     * Construct the component of mailbox
     * @param account The account that want to check the main
     */
    public Mailbox(Account account){
        String[][] init={};
        String[][] input;
        String[] zero={};
        String[] title={"userID", "Date","ElecUsage","GasUsage","ElecCost", "GasCost"};
        AccountHistory data;
        data = ahs.histories(account.getAccountID());
        input=data.getAccountsM();

        jp1=new JPanel();
        jp2=new JScrollPane();

        jlb1=new JLabel("Select a date to check:");

        jcb1=new JComboBox();
        for(int i=0;i<input.length;i++){
            jcb1.addItem(input[i][1]);
        }
        jcb1.addActionListener(new selectListener(jcb1,jp2,input,data));

        model=new DefaultTableModel(init,title);
        model.addRow(zero);
        jtb1=new JTable(model);
        this.setLayout(new BorderLayout());

        jp1.add(jlb1);
        jp1.add(jcb1);

        this.add(jp1,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.CENTER);
        this.setSize(500, 300);
        this.setTitle("Mailbox");
        setLocationRelativeTo(getOwner());
        this.setVisible(true);
    }
}
