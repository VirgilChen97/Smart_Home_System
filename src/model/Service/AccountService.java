package model.Service;

import model.Entity.Account;

import java.util.ArrayList;

/**
 * Description : model.Service is used to process the business, which is isolated with data persistence layer for OCP and DIP.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public interface AccountService {

    /**
     * List all the accounts information.
     *
     * @return the arrayList of account entities which contain all the information of all accounts.
     */
    ArrayList<Account> list();

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
    void add(String electricMeterID, String gasMeterID, String userName, String electricMeterReading, String gasMeterReading, String electricBudgets, String gasBudgets, String password, String state, double balance);

    /**
     * Delete certain account, in fact, I let its state be -1.
     *
     * @param accountID the id you want to delete. (Because energy provider just want to input id to delete someone)
     */
    Boolean delete(String accountID);

    /**
     * Modify certain information of an specific account and put the result, for example: update the readings. (the state can't be changed here)
     *
     * @param acc the account entity you want to modify
     */
    void modify(Account acc);

    /**
     * Get an account entity according to the id.
     *
     * @param accountID the ID you want to obtain its information.
     * @return the account entity of the id.
     */
    Account retrieve(String accountID);
}
