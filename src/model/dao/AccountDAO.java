package model.dao;

import model.Entity.Account;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Description : The DAO interface to make the business implementation do not depend on the details of the persistence layer.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public interface AccountDAO extends XMLDAO {

    /**
     * Obtain all the information in account.xml
     *
     * @return the arrayList of account entities which contain all the information of all accounts.
     */
    Object getFamilyMemebers();

    /**
     * Add a new account in xml
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
     * Modify certain information of an specific account and put the result in xml, for example: update the readings. (the state can't be changed here)
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

    /**
     * Transfer a 2-D array to an arrayList of account
     *
     * @param accounts a 2-D array
     * @return an arrayList of account
     */
    ArrayList<Account> Str2Acc(String accounts[][]);
}
