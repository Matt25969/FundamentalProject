package application.models;

public class Order {
	
	private int userID;
	private double value;
	
	public Order(int userID, int value) {
		super();
		this.userID = userID;
		this.value = value;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	

}
