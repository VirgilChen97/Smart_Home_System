package listener.User;

import model.Entity.Account;
import model.Service.AccountService;
import model.Service.Implimentation.AccountServiceImpl;
import util.ErrorUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * controller.Listener handle budget submit button, submit the budget
 * @author YIfeng Chen
 * @version 1.0
 */
public class BudgetListener implements ActionListener {
    JTextField gas,elec;
    Account account;

    AccountService as = new AccountServiceImpl();

    public BudgetListener(JTextField gasBudget, JTextField elecBudget, Account account){
        this.gas = gasBudget;
        this.elec = elecBudget;
        this.account = account;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!ErrorUtil.checkEmpty(gas,"Gas budget"));

        else if(!ErrorUtil.checkEmpty(elec,"Electric budget"));

        else {
            account.setGasBudgets(gas.getText());
            account.setElectricBudgets(elec.getText());
            as.modify(account);
            JOptionPane.showMessageDialog(null, "Budget set successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
