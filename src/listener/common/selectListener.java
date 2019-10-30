package listener.common;

import model.Entity.AccountHistory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * lister handle the choose in mailbox
 * @author Pinzhou Li
 * @version 1.2
 */
public class selectListener implements ActionListener {
    String[][] input;
    AccountHistory data;
    JScrollPane jp2;
    JTable myTable;
    JTextArea jta1;
    JComboBox jcb1;
    public selectListener(JComboBox jcb1, JScrollPane jp2, String[][] input, AccountHistory data) {
        this.input=input;
        this.data=data;
        this.jp2=jp2;
        this.jcb1=jcb1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s=(String)jcb1.getSelectedItem();
        //String[] title={"userID", "Date","ElecUsage","GasUsage","ElecCost", "GasCost"};

        for(int j =0; j<input.length; j++){
            if(s.equals(input[j][1])){
                //myTable=new JTable(data.getAccountsM(),title);
                jta1=new JTextArea(10,15);
                jta1.setTabSize(4);
                jta1.setLineWrap(true);
                jta1.setWrapStyleWord(true);
                jta1.setEditable(false);
                jta1.setText("Date: "+s+'\n'+"\n  Hello, this is your bill of last month, please have a check.\n"+"\n  Gas usage: "+input[j][2]+"\n  Electricity Usage: "+input[j][3]+"\n  Gas cost: "+input[j][4]+"\n  Electricity cost: "+input[j][5]);
                jp2.setViewportView(jta1);
            }
        }
    }
}
