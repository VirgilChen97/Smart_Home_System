package listener.common;

import model.Entity.Account;
import view.common.UserHistoryUI;
import view.manager.*;
import view.user.Mailbox;
import view.user.RechargeUI;
import view.user.UserBudgetUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This listener manages jumps betweens different UI
 * @author Yifeng Chen
 * @version 1.3
 */
public class JumpListener implements ActionListener {
    JFrame from;
    String to;
    Object args;

    /**
     * constructor
     * @param to where this button want to jump to
     * @param from the source windwos
     * @param args args will pass to new window
     */
    public JumpListener(String to, JFrame from,Object args){
        this.from = from;
        this.to = to;
        this.args = args;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (to){
            case "ManagementUI":{
                new ManagementUI().setAlwaysOnTop(true);
            } break;
            case "UserHistoryUI":{
                new UserHistoryUI(args.toString());
            } break;
            case "TariffUI":{
                new TariffUI();
            }break;
            case "RechargeUI":{
                new RechargeUI((Account)args);
            }break;
            case "UserBudgetUI":{
                new UserBudgetUI((Account)args);
            }break;
            case "Mailbox":{
                new Mailbox((Account)args);
            }
        }
    }
}
