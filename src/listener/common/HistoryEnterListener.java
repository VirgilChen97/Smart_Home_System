package listener.common;

import model.Entity.AccountHistory;
import model.Service.AccountHistoryService;
import model.Service.Implimentation.AccountHistoryServiceImpl;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * controller.Listener for ManagementUI, when press the enter
 * this listener will add a new line when add user
 * @author Pinzhou Li
 * @version 1.0
 */
public class HistoryEnterListener implements KeyListener {
    private JTextField id;
    private JScrollPane dataPane;

    AccountHistoryService ahs = new AccountHistoryServiceImpl();

    public HistoryEnterListener(JTextField id, JScrollPane dataPane){
        this.id = id;
        this. dataPane = dataPane;
    }
    @Override
    public void keyPressed(KeyEvent arg0) {
        if(arg0.getKeyChar()=='\n') {
            AccountHistory data;
            data = ahs.histories(id.getText());
            String[] name = {"userID", "Date","ElecID","GasID","ElecCost", "GasCost"};
            JTable table = new JTable(data.getAccounts(),name);
            dataPane.setViewportView(table);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
