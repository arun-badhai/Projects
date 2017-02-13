package eStore;

public class Customer {
	String custID;
	String custFName;
	String custLName;
	String custPswd;
	String custAddress;
	String custState;
	int custZip;
	String nameonCard;
	long cardNumber;
	int cvv;
	String exp;
	
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getCustFName() {
		return custFName;
	}
	public void setCustFName(String custFName) {
		this.custFName = custFName;
	}
	public String getCustLName() {
		return custLName;
	}
	public void setCustLName(String custLName) {
		this.custLName = custLName;
	}
	public String getCustPswd() {
		return custPswd;
	}
	public void setCustPswd(String custPswd) {
		this.custPswd = custPswd;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public int getCustZip() {
		return custZip;
	}
	public void setCustZip(int custZip) {
		this.custZip = custZip;
	}
	public String getNameonCard() {
		return nameonCard;
	}
	public void setNameonCard(String nameonCard) {
		this.nameonCard = nameonCard;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	} 
}