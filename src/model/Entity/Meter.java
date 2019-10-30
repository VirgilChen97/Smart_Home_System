package model.Entity;

/**
 * Description : This is a normal entity to store the meter.
 *
 * @author Yifeng Chen
 * @version 1.2
 *
 */
public class Meter extends Device {

    private String reading;
    private String readingHis;

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getReadingHis() {
        return readingHis;
    }

    public void setReadingHis(String readingHis) {
        this.readingHis = readingHis;
    }
}
