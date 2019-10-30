package listener.manager;

import model.Service.AccountService;
import model.Service.Implimentation.AccountServiceImpl;
import util.ErrorUtil;
import util.FlyweightUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a listener which listens JTable actions in Add user UI
 * When manager press add,there will be a 'Success' hint
 * THe input of manage will be print in the result
 * @author Pingzhou Li
 * @version 2.0
 */
public class AddActionListner implements ActionListener {
    JTable table;
    JFrame parent;

    AccountService as = new AccountServiceImpl();

    public AddActionListner(JTable table, JFrame parent){
        this.table = table;
        this.parent = parent;
    }

    /**
     * This method is an action that implement two function
     * When manager press add, there will be a 'Success' hint
     * The input of manager will be print in the result
     * @param e
     */
    public void actionPerformed(ActionEvent e)
    {
        //JOptionPane.showMessageDialog(null, " can't be empty!");
        //System.out.println(ErrorUtil.checkTable(table));
        if(!ErrorUtil.checkTable(table));
        else{
            for(int i=0;i<table.getRowCount();i++) {
                String [] data = new String[5];
                for(int j=0;j<table.getColumnCount();j++) {
                    data[j]=table.getValueAt(i, j).toString();
                    System.out.println(table.getValueAt(i, j).toString());
                }
                as.add(data[0],data[1],data[2],"0","0","0","0",data[3],"0" ,Double.parseDouble(data[4]));
                JOptionPane.showMessageDialog(parent,"Add successful","Success",JOptionPane.INFORMATION_MESSAGE);
                FlyweightUtil.managementUI.update();
                System.out.println("\n");
            }

    }
    }
}
