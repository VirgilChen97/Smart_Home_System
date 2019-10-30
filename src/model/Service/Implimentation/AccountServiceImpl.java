package model.Service.Implimentation;

import model.Entity.Account;
import model.Service.AccountService;
import model.dao.AccountDAO;
import model.dao.implimentation.AccountDAOImpl;

import java.util.ArrayList;

/**
 * Description : Use xml dom parsing to implement the service.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class AccountServiceImpl implements AccountService {
    AccountDAO accountDAO = new AccountDAOImpl();

    /**
     * List all the accounts information.
     *
     * @return the arrayList of account entities which contain all the information of all accounts.
     */
    public ArrayList<Account> list() {
        ArrayList<Account>  a = (ArrayList<Account>)accountDAO.getFamilyMemebers();
        return a;
    }

    /**
     * Add a new account.
     *
     * @param electricMeterID
     * @param gasMeterID
     * @param userName
     * @param electricMeterReading
     * @param gasMeterReading
     * @param electricBudgets
     * @param gasBudgets
     * @param password
     * @param state                -1 means deleted, 0 means active
     * @param balance
     */
    public void add(String electricMeterID, String gasMeterID, String userName, String electricMeterReading, String gasMeterReading, String electricBudgets, String gasBudgets, String password, String state, double balance) {
        accountDAO.add(electricMeterID, gasMeterID, userName, electricMeterReading, gasMeterReading, electricBudgets, gasBudgets, password, state, balance);
    }

    /**
     * Delete certain account, in fact, I let its state be -1.
     *
     * @param accountID the id you want to delete. (Because energy provider just want to input id to delete someone)
     */
    public Boolean delete(String accountID) {
        return accountDAO.delete(accountID);
    }

    /**
     * Modify certain information of an specific account and put the result, for example: update the readings. (the state can't be changed here)
     *
     * @param acc the account entity you want to modify
     */
    public void modify(Account acc) {
        accountDAO.modify(acc);
    }

    /**
     * Get an account entity according to the id.
     *
     * @param accountID the ID you want to obtain its information.
     * @return the account entity of the id.
     */
    public Account retrieve(String accountID) {
        return accountDAO.retrieve(accountID);
    }
}
