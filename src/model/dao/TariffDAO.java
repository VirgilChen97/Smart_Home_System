package model.dao;

import model.Entity.Tariff;

/**
 * Description : The DAO interface to make the business implementation do not depend on the details of the persistence layer.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public interface TariffDAO extends XMLDAO  {
    /**
     * Obtain all the information in tariff.xml
     * @return the Tariff entity which contains all the information of current tariff.
     */
    Object getFamilyMemebers();


    /**
     * Modify certain information of current tariff and put the result into xml.
     * @param tariff the entity with new electric and gas information.
     */
    void modify(Tariff tariff);

    /**
     * Transfer the 2-D array to an tariff entity.
     * @param tariffs the string array of tariff.
     * @return the tariff entity.
     */
    Tariff Str2Tariff(String tariffs[][]);
}
