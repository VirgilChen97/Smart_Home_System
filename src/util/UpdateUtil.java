package util;
import model.Service.Implimentation.MeterServiceImpl;

import java.util.Timer;
import java.util.TimerTask;

/**
 * useful functions for tasks need to be executed periodically
 * @author Yifeng Chen
 * @version 1.0
 */
public class UpdateUtil {

    private MeterServiceImpl server;
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    private static final long PERIOD_MONTH = PERIOD_DAY * 30;

    public UpdateUtil(String id){
        server = new MeterServiceImpl();
        RepaintTimer();
        ElecTimer(id);
        GasTimer(id);
        DayTimer(id);
        MonthTimer(id);
    }


    /**
     * Update the data to display.
     */
    public void RepaintTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                FlyweightUtil.userUI.update();
                System.out.println("GUI Updated!");
            }
        }, 0, 3000);
    }


    /**
     * Update electricity readings per 30s
     * @param id ID you want to update meter reading
     */

    public void ElecTimer(String id) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                server.updateElecReading(id);
                System.out.println("Elec Updated!");
            }
        }, 0, 3000);
    }

    /**
     * Update gas readings per 30min
     * @param id ID you want to update meter reading
     */
    public void GasTimer(String id) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                server.updateGasReading(id);
                System.out.println("Gas Updated!");
            }
        }, 0, 6000);
    }

    /**
     * Update daily cost
     * @param id    ID you want to update
     */
    public void DayTimer(String id) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                server.generate_history(id);
                System.out.println("History generated!");
            }
        }, 0, 30000);//8640000
    }

    public void MonthTimer(String id){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                server.update_month(id);
                System.out.println("month updated!");
            }
        }, 0, 6000);
    }
}
