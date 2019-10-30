package listener.manager;

import model.Entity.Tariff;
//import jdk.nashorn.internal.scripts.JO;
import model.Service.Implimentation.TariffServiceImpl;
import model.Service.TariffService;
import util.ErrorUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controller.Listener handle tariff submit button, submit the tariff
 * @author YIfeng Chen
 * @version 1.0
 */
public class TariffSubmitListener implements ActionListener {
    private JTextField gas;
    private JTextField elec;

    TariffService ts = new TariffServiceImpl();

    public TariffSubmitListener(JTextField gas, JTextField elec){
        this.gas = gas;
        this.elec = elec;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!ErrorUtil.checkEmpty(gas,"Gas tariff"));
        else if(!ErrorUtil.checkEmpty(elec,"Electric tariff"));
        else{
            Tariff t = ts.list();
            t.setGasFuture(gas.getText());
            t.setElectricityFuture(elec.getText());
            ts.modify(t);
            JOptionPane.showMessageDialog(null,"Success\nTariff will change in the next month","success",JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
