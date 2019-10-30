package model.Service.Implimentation;

import model.Entity.AccountHistory;
import model.Service.AccountHistoryService;
import model.dao.AccountHistoryDAO;
import model.dao.implimentation.AccountHistoryDAOImpl;

/**
 * Description : Use xml dom parsing to implement the service.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class AccountHistoryServiceImpl implements AccountHistoryService {

    AccountHistoryDAO accountHistoryDAO = new AccountHistoryDAOImpl();

    /**
     * @param accountID the accountID you want to get its reading history by day, week, month.
     * @return the entity AccountHistory
     */
    public AccountHistory histories(String accountID){
        return accountHistoryDAO.getSpecialFamilyMemebers(accountID);
    }


    /**
     * Add a new item of userHistory.
     * @param accountID
     * @param date
     * @param electricUsage
     * @param electricCost
     * @param gasUsage
     * @param gasCost
     */
    public void add(String accountID, String date, String electricUsage, String electricCost, String gasUsage, String gasCost) {
        accountHistoryDAO.add(accountID, date, electricUsage, electricCost, gasUsage, gasCost);
    }
}
