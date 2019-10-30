package listener.manager;

import model.Service.AccountService;
import model.Service.Implimentation.AccountServiceImpl;
import util.ErrorUtil;
import util.FlyweightUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controller.Listener handle delete operation
 * @author Yifeng Chen
 * @version 1.0
 */
public class DeleteListener implements ActionListener {

    JTextField id;
    JFrame owner;

    AccountService as = new AccountServiceImpl();

    public DeleteListener(JFrame owner,JTextField id){
        this.id = id;
        this.owner = owner;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!ErrorUtil.checkEmpty(id,"UserID"));
        else{
            if(as.delete(id.getText())) {
                JOptionPane.showMessageDialog(owner, "Delete successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                FlyweightUtil.managementUI.update();
            }
            else {
                JOptionPane.showMessageDialog(owner, "Invalid ID", "Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
