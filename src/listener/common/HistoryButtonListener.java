package listener.common;

import model.Entity.AccountHistory;
import model.Service.AccountHistoryService;
import model.Service.AccountService;
import model.Service.Implimentation.AccountHistoryServiceImpl;
import model.Service.Implimentation.AccountServiceImpl;
import util.ErrorUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a listener for day/week/month button
 * in userhistoryUI
 * @author Yifeng Chen
 * @version 1.2
 */
public class HistoryButtonListener implements ActionListener {

    private JTextField id;
    private JScrollPane dataPane;
    String mode;

    AccountService as = new AccountServiceImpl();
    AccountHistoryService ahs = new AccountHistoryServiceImpl();

    public HistoryButtonListener(JScrollPane dataPane, JTextField id, String mode){
        this.dataPane = dataPane;
        this.mode = mode;
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!ErrorUtil.checkEmpty(id,"UserID"));
        else if(as.retrieve(id.getText())==null){
            JOptionPane.showMessageDialog(null,"No such user","Error",JOptionPane.ERROR_MESSAGE);
            id.setText("");
        }
        else{
            AccountHistory data;
            data = ahs.histories(id.getText());
            String[] name = {"userID", "Date","ElecUsage","GasUsage","ElecCost", "GasCost"};
            JTable table;
            switch (mode) {
                case "Month": {
                    table = new JTable(data.getAccountsM(), name);
                } break;
                case "Week":{
                    table = new JTable(data.getAccountsW(), name);
                } break;
                case "Day":{
                    table = new JTable(data.getAccounts(),name);
                } break;
                default: table= new JTable();
            }
            dataPane.setViewportView(table);
        }
    }

}
