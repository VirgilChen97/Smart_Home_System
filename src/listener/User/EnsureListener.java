package listener.User;

import model.Entity.Account;
import model.Service.AccountService;
import model.Service.Implimentation.AccountServiceImpl;
import util.ErrorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class is a listener which listens button in user UI
 * @author Pingzhou Li
 * @version 2.0
 */
public class EnsureListener  implements MouseListener{

    private JTextField jtf1,jtf2;
    private JLabel Message2;
    Account account;

    AccountService as = new AccountServiceImpl();

    /**
     * This method  get the data transport to the listener
     * @param jtf1
     * @param jtf2
     * @param Message2
     * @param account
     */
    public EnsureListener(JTextField jtf1, JTextField jtf2, JLabel Message2, Account account) {
        this.jtf1=jtf1;
        this.jtf2=jtf2;
        this.Message2=Message2;
        this.account = account;
    }

    /**
     * This method implement two function
     * When user press recharge button it will be a 'Success' hint
     * When user press recharge button, the balance will change.
     * @param arg0
     */
    @Override
    public void mouseClicked(MouseEvent arg0)  {
        if(!ErrorUtil.checkEmpty(jtf2,"The amount of money you want to recharge"));
        // TODO Auto-generated method stub
        else{
            System.out.println(jtf2.getText());
            Float ad = Float.parseFloat(jtf2.getText());
            Float before = Float.parseFloat(account.getBalance());
            Float after = before + ad;
            jtf1.setText(String.valueOf(after));
            account.setBalance(String.valueOf(after));
            as.modify(account);
            jtf1.setForeground(Color.RED);
            //Message2.setVisible(true);
            JOptionPane.showMessageDialog(null,"Recharge Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
