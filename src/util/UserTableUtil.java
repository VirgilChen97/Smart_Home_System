package util;

import model.Entity.Account;
import model.Entity.Tariff;
import model.Service.Implimentation.TariffServiceImpl;
import model.Service.TariffService;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * Useful functions to generate DefaultTableModel from 2D array
 * @author Lejin Bai
 * @version 1.2
 */

public class UserTableUtil {

    /**
     * generate JTable contains user information
     * @param account entity of user that you want to generate DefaultTableModel
     * @return DefaultTableModel contains user information
     */
    public static DefaultTableModel getTable(Account account) {
        TariffService ts = new TariffServiceImpl();

        DefaultTableModel dtm = new DefaultTableModel();
        Tariff tarrif = ts.list();
        Vector columnName = new Vector();
        columnName.add("ID");
        columnName.add("Type");
        columnName.add("Reading");
        columnName.add("Tariff");
        columnName.add("Cost");
        columnName.add("Status");
        Vector rowData1 = new Vector();
        rowData1.add(account.getGasMeterID());
        rowData1.add("Gas");
        rowData1.add(account.getGasMeterReading());
        rowData1.add(tarrif.getGas());
        Float gascost = (Float.parseFloat(account.getGasMeterReading()) - Float.parseFloat(account.getGasMeterReadingHis()))
                * Float.parseFloat(tarrif.getGas());
        rowData1.add(gascost);
        if(gascost < Float.parseFloat(account.getGasBudgets())*0.8) {
            rowData1.add("OK");
        }else {
            rowData1.add("Insufficient"+ String.valueOf((int)((100 * gascost / Float.parseFloat(account.getGasBudgets())))));
        }
        Vector rowData2 = new Vector();
        rowData2.add(account.getElectricMeterID());
        rowData2.add("Electricity");
        rowData2.add(account.getElectricMeterReading());
        rowData2.add(tarrif.getElectricity());
        Float eleccost = (Float.parseFloat(account.getElectricMeterReading()) - Float.parseFloat(account.getElectricMeterReadingHis()))
                * Float.parseFloat(tarrif.getElectricity());
        rowData2.add(eleccost);
        if(eleccost < Float.parseFloat(account.getElectricBudgets())*0.8) {
            rowData2.add("OK");
        }else {
            rowData2.add("Insufficient"+ String.valueOf((int)((100 * eleccost / Float.parseFloat(account.getElectricBudgets())))));
        }
        Vector rowDatas = new Vector();
        rowDatas.add(rowData1);
        rowDatas.add(rowData2);


        for (int i = 0; i < columnName.size(); i++) {
            dtm.addColumn(columnName.get(i), columnName);
        }


        for (int i = 0; i < rowDatas.size(); i++) {
            for (int j = 0; j < columnName.size(); j++) {
                dtm.setValueAt(((Vector) rowDatas.get(i)).get(j), i, j);
            }
        }

        dtm.setColumnCount(columnName.size());
        dtm.setRowCount(rowDatas.size());
        return dtm;
    }
}
