package model.Service.Implimentation;

import model.Entity.Tariff;
import model.Service.TariffService;
import model.dao.TariffDAO;
import model.dao.implimentation.TariffDAOImpl;

/**
 * Description : Use xml dom parsing to implement the service.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class TariffServiceImpl implements TariffService {

    TariffDAO tariffDAO = new TariffDAOImpl();

    /**
     * Obtain all the tariff
     * @return the Tariff entity which contains all the information of current tariff.
     */
    public Tariff list(){
        return (Tariff) tariffDAO.getFamilyMemebers();
    }

    /**
     * Modify certain information of current tariff and put the result.
     * @param tariff the entity with new electric and gas information.
     */
    public void modify(Tariff tariff){
        tariffDAO.modify(tariff);
    }
}