package listener.common;

import model.Entity.AccountHistory;
import model.Service.AccountHistoryService;
import model.Service.AccountService;
import model.Service.Implimentation.AccountHistoryServiceImpl;
import model.Service.Implimentation.AccountServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchDayListener implements ActionListener {
    private JScrollPane dataPane;
    private JTextField Year;
    private JTextField Month;
    private JTextField Day;
    private JTextField id;

    private JTable table;

    AccountService as = new AccountServiceImpl();
    AccountHistoryService ahs = new AccountHistoryServiceImpl();

    public SearchDayListener(JScrollPane dataPane, JTextField id,JTextField Year,JTextField Month,JTextField Day,JTable table){
        this.dataPane=dataPane;
        this.id =id;
        this.Year=Year;
        this.Day =Day;
        this.Month= Month;
        this.table = table;

    }
    public void actionPerformed(ActionEvent e) {
        String [] data1 = new String[5];
        int flag=0;
        if(!util.ErrorUtil.checkEmpty(id,"UserID"));
        else if(as.retrieve(id.getText())==null);
        else if((!util.ErrorUtil.checkEmpty(Year,"Date"))||
                (!util.ErrorUtil.checkEmpty(Month,"Date"))||
                (!util.ErrorUtil.checkEmpty(Day,"Date")));
        else{
            AccountHistory data;
            data = ahs.histories(id.getText());
            for(int i=0;i<data.getAccounts().length;i++){
                if(data.getAccounts()[i][1].substring(0,4).equals(Year.getText())&&data.getAccounts()[i][1].substring(5,6).equals(Month.getText())&&data.getAccounts()[i][1].substring(7).equals(Day.getText()))
                {
                    String[] name = {"userID", "Date","ElecUsage","GasUsage","ElecCost", "GasCost"};
                    JTable table;

                    String[][] SearchString =new String[1][];
                    SearchString[0]=data.getAccounts()[i];

                    table = new JTable(SearchString, name);
                    dataPane.setViewportView(table);
                    flag =1;
                }
            }
            if(flag==0){
                JOptionPane.showMessageDialog(null,"No such date!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
