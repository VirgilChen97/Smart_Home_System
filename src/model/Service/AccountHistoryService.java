package model.Service;

import model.Entity.AccountHistory;

/**
 * Description : model.Service is used to process the business, which is isolated with data persistence layer for OCP and DIP.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public interface AccountHistoryService {

    /**
     * @param accountID the accountID you want to get its reading history by day, week, month.
     * @return the entity AccountHistory
     */
    AccountHistory histories(String accountID);

    /**
     * Add a new item of userHistory.
     * @param accountID
     * @param date
     * @param electricUsage
     * @param electricCost
     * @param gasUsage
     * @param gasCost
     */
    void add(String accountID, String date, String electricUsage, String electricCost, String gasUsage, String gasCost);
}
