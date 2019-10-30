package model.Service;

import model.Entity.Tariff;

/**
 * Description : model.Service is used to process the business, which is isolated with data persistence layer for OCP and DIP.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public interface TariffService {
    /**
     * Obtain all the tariff
     * @return the Tariff entity which contains all the information of current tariff.
     */
    Tariff list();

    /**
     * Modify certain information of current tariff and put the result.
     * @param tariff the entity with new electric and gas information.
     */
    void modify(Tariff tariff);
}
