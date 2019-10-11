package application.business;

import application.models.Item;
import application.models.User;
import application.persistence.Persistence;

public class Logic {
	private Persistence persistence;

	public Logic(String environment) {
		this.persistence = new Persistence(environment);
	}

	public String createNewItem(String itemName, int itemValue) {
		int result = persistence.createNewItem(new Item(itemName, itemValue));
		if (result == 0) {
			return "No Item created";
		} else if (result == -1) {
			return "An error has occured - check the logs";
		} else {
			return "Item successfully created";
		}
	}

	public String createNewUser(String userName) {
		int result = persistence.createNewUser(new User(userName));
		if (result == 0) {
			return "No User created";
		} else if (result == -1) {
			return "An error has occured - check the logs";
		} else {
			return "User successfully created";
		}

	}

	public void displayItems() {
		persistence.displayItems();
	}

	public void displayUsers() {
		persistence.displayUsers();
	}

	public int calculateCost(String itemID) {
		return persistence.calculateCost(itemID);
	}

	public int calculateTotalCost(int costOfItem, int amount) {
		return costOfItem * amount;
	}

	public void applyBulkDiscount(double cost) {
		if (cost > 10000) {
			cost = cost * 0.9;
			System.out.println("A discount of 10% has been applied");
		} else if (cost > 1000) {
			cost = cost * 0.95;
			System.out.println("A discount of 5% has been applied");
		}
	}

	public double applyDeliveryCharge(String deliveryChoice, double cost) {
		if (deliveryChoice.equals("Y") && cost > 2000) {
			cost = cost * 1.1;
		} else if (deliveryChoice.equals("Y")) {
			cost = cost * 1.05;
		}
		return cost;
	}
	
	public void saveOrder(int userID, double finalCost) {
		
		persistence.saveOrder(userID, finalCost);
		
	}

	public void clearDatabase() {

		persistence.clearDatabase();

	}

	public void displayOrders() {
		persistence.displayOrders();
		
	}

	
}
