package listener.common;

import model.Entity.Account;
import util.FlyweightUtil;
import util.UpdateUtil;
import view.user.UserUI;
import model.Service.AccountService;
import model.Service.Implimentation.AccountServiceImpl;
import util.ErrorUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controller.Listener handle login button
 * @author Yifeng Chen
 * @version 1.2
 */
public class LoginButtonLisener implements ActionListener {
    private JTextField user;
    private JPasswordField psswd;
    private JFrame from;

    AccountService as = new AccountServiceImpl();

    public LoginButtonLisener(JTextField user, JPasswordField psswd, JFrame from){
        this.user = user;
        this.psswd = psswd;
        this.from = from;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = null;
        String usr = user.getText();
        String password = psswd.getText();

//        //check if you have already logged in
//        if(FlyweightUtil.userUI != null){
//            JOptionPane.showMessageDialog(null,"You have already logged in!","Error",JOptionPane.ERROR_MESSAGE);
//            from.dispose();
//            return;
//        }

        if(!ErrorUtil.checkEmpty(user,"UserID"));
        else if(!ErrorUtil.checkEmpty(psswd,"Password"));
        else{
            account=as.retrieve(usr);
            if(account==null) {
                JOptionPane.showMessageDialog(null,"No such user","Error",JOptionPane.ERROR_MESSAGE);
                user.setText("");
                psswd.setText("");
                return;
            }
            if(!password.equals(account.getPassword())){
                JOptionPane.showMessageDialog(null,"Wrong Password","Error",JOptionPane.ERROR_MESSAGE);
                psswd.setText("");
            }
            else{
                FlyweightUtil.userUI = new UserUI(account);
                new UpdateUtil(account.getAccountID());
                from.dispose();
            }
        }


    }
}
