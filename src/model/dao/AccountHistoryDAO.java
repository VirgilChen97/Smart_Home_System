package model.dao;

import model.Entity.AccountHistory;

/**
 * Description : The DAO interface to make the business implementation do not depend on the details of the persistence layer.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public interface AccountHistoryDAO extends XMLDAO  {

    /**
     * @param accountID the accountID you want to get its reading history by day, week, month.
     * @return the entity AccountHistory
     */
    AccountHistory getSpecialFamilyMemebers(String accountID);

    /**
     * Add a new item of userHistory to XML
     * @param accountID
     * @param date
     * @param electricUsage
     * @param electricCost
     * @param gasUsage
     * @param gasCost
     */
    void add(String accountID, String date, String electricUsage, String electricCost, String gasUsage, String gasCost);
}
