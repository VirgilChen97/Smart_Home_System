package model.Entity;
/**
 * Description : This is a normal entity to store the account.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class Account {
		private String accountID;
		private Meter electricMeter = new Meter();
		private Meter gasMeter = new Meter();
		private String userName;
		private String electricBudgets;
		private String gasBudgets;
		private String password;
		private String state = "1";// 1 is normal, and -1 is deleted
		private String balance;
		
		public String getAccountID() {
			return accountID;
		}
		public void setAccountID(String accountID) {
			this.accountID = accountID;
		}
		public String getElectricMeterID() {
			return electricMeter.getID();
		}
		public void setElectricMeterID(String electricMeterID) {
			this.electricMeter.setID(electricMeterID);
		}
		public String getGasMeterID() {
			return gasMeter.getID();
		}
		public void setGasMeterID(String gasMeterID) {
			this.gasMeter.setID(gasMeterID);
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getElectricMeterReading() {
			return electricMeter.getReading();
		}
		public void setElectricMeterReading(String electricMeterReading) {
			this.electricMeter.setReading(electricMeterReading);
		}
		public String getGasMeterReading() {
			return gasMeter.getReading();
		}
		public void setGasMeterReading(String gasMeterReading) {
			this.gasMeter.setReading(gasMeterReading);
		}
		public String getElectricBudgets() {
			return electricBudgets;
		}
		public void setElectricBudgets(String electricBudgets) {
			this.electricBudgets = electricBudgets;
		}
		public String getGasBudgets() {
			return gasBudgets;
		}
		public void setGasBudgets(String gasBudgets) {
			this.gasBudgets = gasBudgets;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getBalance() {
		return balance;
	}
		public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getGasMeterReadingHis() {
		return gasMeter.getReadingHis();
	}

	public void setGasMeterReadingHis(String gasMeterReadingHis) {
		this.gasMeter.setReadingHis(gasMeterReadingHis);
	}

	public String getElectricMeterReadingHis() {
		return electricMeter.getReadingHis();
	}

	public void setElectricMeterReadingHis(String electricMeterReadingHis) {
		this.electricMeter.setReadingHis(electricMeterReadingHis);
	}

	/**
	 * @return the string format of this account object.
	 */
	public String[] strings() {
		String account[] = {accountID, electricMeter.getID(), gasMeter.getID(), userName, electricMeter.getReadingHis(), gasMeter.getReadingHis(),
                electricMeter.getReading(), gasMeter.getReading(), electricBudgets, gasBudgets, password, state, balance};
		return account;
	}
}
