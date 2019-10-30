package view.manager;
import model.Entity.Account;
import listener.User.controlListener;
import listener.common.InputControlListener;
import listener.manager.AddActionListner;
import listener.manager.AddEnterListener;
import listener.manager.DeleteListener;
import model.Service.AccountService;
import model.Service.Implimentation.AccountServiceImpl;
import util.FlyweightUtil;
import view.common.UpdateFrame;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * User management UI for manager to manage users
 * @author Yifeng Chen
 * @version 1.3
 */
public class ManagementUI extends UpdateFrame {
    
    public ManagementUI() {
        initComponents();

        //Add
        String[][] datas = {};
        String[] titles = { "electricMeterID", "gasMeterID", "userName", "password", "balance" };
        Addmodel = new DefaultTableModel(datas, titles);
        Addtable = new JTable(Addmodel);
        Addmodel.addRow(new String[] {null,null,null});
        btnSubmit.addActionListener(new AddActionListner(Addtable,this));
        Addtable.addKeyListener(new AddEnterListener(Addmodel,datas));

        JTextField tf = new JTextField();
        tf.addKeyListener(new InputControlListener());
        tf.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tf.setSelectionStart(0);
        tf.setSelectionEnd(tf.getText().length());
        Addtable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(tf));

        AddDataPane.setViewportView(Addtable);

        //delete
        btnDelete.addActionListener(new DeleteListener(this,textField1));

        update();

        this.setVisible(true);
        FlyweightUtil.managementUI = this;
    }

    public void update() {
        AccountService as = new AccountServiceImpl();

        //check
        String[] name = {
                "ID",
                "electricMeterID",
                "gasMeterID",
                "userName",
                "electricMeterReadingHis",
                "gasMeterReadingHis",
                "electricMeterReading",
                "gasMeterReading",
                "electricBudgets",
                "gasBudgets",
                "password",
                "state",
                "balance"
        };
        DefaultTableModel tableModel = new DefaultTableModel(name, 0);
        List<Account> accountList = as.list();
        JTable jt = new JTable(tableModel);
        for (Account a : accountList){
            tableModel.addRow(a.strings());
        }

        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        CheckPane.setViewportView(jt);
    }


        private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        Add = new JPanel();
        AddDataPane = new JScrollPane();
        btnSubmit = new JButton();
        Delete = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        btnDelete = new JButton();
        label3 = new JLabel();
        Check = new JPanel();
        CheckPane = new JScrollPane();

        //======== this ========
        setTitle("Smart Home Management System");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(new Font("Arial", Font.PLAIN, 12));

            //======== Add ========
            {
                Add.setLayout(null);
                Add.add(AddDataPane);
                AddDataPane.setBounds(15, 15, 530, 310);

                //---- btnSubmit ----
                btnSubmit.setText("Submit");
                btnSubmit.setFont(new Font("Arial", Font.PLAIN, 12));
                Add.add(btnSubmit);
                btnSubmit.setBounds(new Rectangle(new Point(470, 335), btnSubmit.getPreferredSize()));
            }
            tabbedPane1.addTab("Add User", Add);

            //======== Delete ========
            {
                Delete.setLayout(null);

                //---- label2 ----
                label2.setText("Please input the ID of the user");
                label2.setFont(new Font("Arial", Font.PLAIN, 12));
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                Delete.add(label2);
                label2.setBounds(160, 125, 240, 30);

                //---- textField1 ----
                textField1.setFont(new Font("Arial", Font.PLAIN, 16));
                textField1.setHorizontalAlignment(SwingConstants.CENTER);
                Delete.add(textField1);
                textField1.addKeyListener(new controlListener());
                textField1.setBounds(215, 160, 130, 45);

                //---- btnDelete ----
                btnDelete.setText("Delete");
                btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
                btnDelete.setForeground(new Color(204, 0, 0));
                Delete.add(btnDelete);
                btnDelete.setBounds(240, 215, 80, 25);

                //---- label3 ----
                label3.setText("Warining: All user data will loss after delete");
                label3.setFont(new Font("Arial", Font.PLAIN, 12));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setBackground(Color.white);
                label3.setForeground(new Color(204, 0, 0));
                Delete.add(label3);
                label3.setBounds(125, 100, 315, 30);
            }
            tabbedPane1.addTab("Delete User", Delete);

            //======== Check ========
            {
                Check.setLayout(null);
                Check.add(CheckPane);
                CheckPane.setBounds(5, 5, 550, 355);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < Check.getComponentCount(); i++) {
                        Rectangle bounds = Check.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Check.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Check.setMinimumSize(preferredSize);
                    Check.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("Check", Check);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(0, 0, 563, 398);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private DefaultTableModel Addmodel = null;
    private JTable Addtable = null;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel Add;
    private JScrollPane AddDataPane;
    private JButton btnSubmit;
    private JPanel Delete;
    private JLabel label2;
    private JTextField textField1;
    private JButton btnDelete;
    private JLabel label3;
    private JPanel Check;
    private JScrollPane CheckPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
