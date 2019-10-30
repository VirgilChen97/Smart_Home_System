package listener.manager;

import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is a listener which listens JTable in Add user UI.
 * When user press enter, JTable will add anothr roll
 * @author Pingzhou Li
 * @version 1.0
 */
public class AddEnterListener implements KeyListener {
    private DefaultTableModel model;
    String[][] datas;

    /**
     * This method  get the data transport to the listener
     * @param model
     * @param datas
     */
    public AddEnterListener(DefaultTableModel model,String[][] datas){
        this.model = model;
        this.datas = datas;
    }
    /**
     * This method implement when user press enter, JTable will add a new row
     */
    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        if(arg0.getKeyChar()=='\n') {
            model.addRow(datas);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }
}
