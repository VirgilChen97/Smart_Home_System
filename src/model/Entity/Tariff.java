package model.Entity;

/**
 * Description : This is a normal entity to store the Tariff.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class Tariff {

    String gas;
    String electricity;
    String gasFuture;
    String electricityFuture;


    public String getGasFuture() {
        return gasFuture;
    }

    public void setGasFuture(String gasFuture) {
        this.gasFuture = gasFuture;
    }

    public String getElectricityFuture() {
        return electricityFuture;
    }

    public void setElectricityFuture(String electricityFuture) {
        this.electricityFuture = electricityFuture;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    @Override
    public String toString() {
        return "gas = " + getGas() + " elec = " + getElectricity() + " gF = " + getGasFuture() + " eF = " + getElectricityFuture() + " ";
    }


}
