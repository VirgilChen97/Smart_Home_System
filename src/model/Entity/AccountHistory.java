package model.Entity;

/**
 * Description : This is not a normal entity, it's used to store all the history by day, week, month.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class AccountHistory {

    String accounts[][];
    String accountsW[][];
    String accountsM[][];
    public String[][] getAccounts() {
        return accounts;
    }

    public void setAccounts(String[][] accounts) {
        this.accounts = accounts;
    }

    public String[][] getAccountsW() {
        return accountsW;
    }

    public void setAccountsW(String[][] accountsW) {
        this.accountsW = accountsW;
    }


    public String[][] getAccountsM() {
        return accountsM;
    }

    public void setAccountsM(String[][] accountsM) {
        this.accountsM = accountsM;
    }



}
