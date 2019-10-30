package model.Service.Implimentation;

import model.Entity.Account;
import model.Entity.Tariff;
import model.Service.AccountHistoryService;
import model.Service.AccountService;
import model.Service.MeterService;
import model.Service.TariffService;
import model.dao.TariffDAO;
import model.dao.implimentation.TariffDAOImpl;
import view.manager.ManagerUI;

import javax.swing.*;
import java.util.Calendar;

/**
 * Class that runs basic meter service, update meter readings, update user history
 * @author Yifeng Chen
 * @version 1.0
 */
public class MeterServiceImpl implements MeterService {

    AccountService as = new AccountServiceImpl();
    TariffService ts = new TariffServiceImpl();
    AccountHistoryService ahs = new AccountHistoryServiceImpl();

    private Float gasdailycost = 0f;
    private Float elecdailycost = 0f;

    /**
     * this method randomly update the gas meter readings
     * @param id the account id of the account that you want to update
     * @return isSuccess
     */
    @Override
    public int updateGasReading(String id) {

        int add = (int) (Math.random() * 4 + 1);
        Account account = as.retrieve(id);
        int Reading = Integer.parseInt(account.getGasMeterReading());
        Reading = Reading + add;
        gasdailycost = gasdailycost + add;
        account.setGasMeterReading(String.valueOf(Reading));
        Tariff tariff = ts.list();
        Float cost = add * Float.parseFloat(tariff.getGas());
        Float sub = Float.parseFloat(account.getBalance())-cost;
        if(sub<0) return 1;
        else {
            account.setBalance(String.valueOf(sub));
            as.modify(account);
            return 0;
        }
    }

    /**
     * this method randomly update the electricity meter readings
     * @param id the account id of the account that you want to update
     * @return isSuccess
     */
    @Override
    public int updateElecReading(String id){

        int add = (int) (Math.random() * 5);
        Account account = as.retrieve(id);
        int Reading = Integer.parseInt(account.getElectricMeterReading());
        Reading = Reading + add;
        elecdailycost = elecdailycost + add;
        account.setElectricMeterReading(String.valueOf(Reading));
        Tariff tariff = ts.list();
        Float cost = add * Float.parseFloat(tariff.getElectricity());
        Float sub = Float.parseFloat(account.getBalance())-cost;

        if(sub<0) return 1;
        else {
            account.setBalance(String.valueOf(sub));
            as.modify(account);
            return 0;
        }
    }

    /**
     * This method generate history data and write into xml files
     * @param id the account that you want to update history
     * @return  isSuccess
     */
    @Override
    public int generate_history(String id) {
        Calendar now = Calendar.getInstance();
        String Date = (now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DAY_OF_MONTH));
        Account account = as.retrieve(id);
        Tariff tariff = ts.list();
        float gasTariff = Float.parseFloat(tariff.getGas());
        float elecTariff = Float.parseFloat(tariff.getElectricity());
        float Gascost = gasdailycost * gasTariff;
        float Eleccost = elecdailycost * elecTariff;
        ahs.add(id,Date,String.valueOf(elecdailycost),String.valueOf(Eleccost),String.valueOf(gasdailycost),String.valueOf(Gascost));
        gasdailycost = 0f;
        elecdailycost = 0f;
        return 0;
    }

    /**
     * Copy the value of Reading to ReadingHistory
     * @param id
     * @return isSuccess
     */
    @Override
    public int update_month(String id) {
        Account account = as.retrieve(id);
        account.setGasMeterReadingHis(account.getGasMeterReading());
        account.setElectricMeterReadingHis(account.getElectricMeterReading());
        Tariff tariff = ts.list();
        tariff.setGas(tariff.getGasFuture());
        tariff.setElectricity(tariff.getElectricityFuture());
        ts.modify(tariff);
        return 0;
    }
}
