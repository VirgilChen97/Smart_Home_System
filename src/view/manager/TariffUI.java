package view.manager;

import listener.common.InputControlListener;
import listener.manager.TariffSubmitListener;
import model.Entity.Tariff;
import model.Service.Implimentation.TariffServiceImpl;
import model.Service.TariffService;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the user tariff interface, it allows user to look at their tariff and reset it.
 * @author  Yuhang Fan
 * @version 2.3
 */
public class TariffUI extends JFrame{

    private JPanel jp1,jp2,jp3;
    private JButton Btnsub;
    private JLabel text1,text2;
    private JTextField jtf1,jtf2;
    /**
     * This is the constructor. Other user interfaces can directly create this class's obejct to initiate.
     *
     */
    public TariffUI(){

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();

        Btnsub=new JButton("Submit");

        text1=new JLabel("Gas:         ");
        text2=new JLabel("Electricity: ");

        Tariff tariff =new TariffServiceImpl().list();

        jtf1=new JTextField(10);
        jtf1.setText(tariff.getGas());
        jtf1.addKeyListener(new InputControlListener());
        jtf2=new JTextField(10);
        jtf2.setText(tariff.getElectricity());
        jtf2.addKeyListener(new InputControlListener());

        jp1.add(text1);
        jp1.add(jtf1);

        jp2.add(text2);
        jp2.add(jtf2);

        jp3.add(Btnsub);
        Btnsub.addActionListener(new TariffSubmitListener(jtf1,jtf2));

        this.setLayout(new GridLayout(3,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setSize(400,200);
        this.setTitle("Tariff");
        this.setVisible(true);

    }
}
