package application.interoperability;

import java.util.InputMismatchException;
import java.util.Scanner;

import application.business.Logic;

public class TextInput implements Input {
	
	private Logic logic;

	public TextInput() {
		this.logic = new Logic("dev");
	}

	private String createItem(Scanner sc1) {
		System.out.println("Type the name of the item you which too add");
		String itemName = sc1.nextLine();
		System.out.println("What is the value of the item (Integer)");
		int itemValue = Integer.parseInt(sc1.nextLine());
		System.out.println("Are you sure you wish to save this Item (Y or N)");
		String itemChoice = sc1.nextLine();
		if (itemChoice.equals("Y")) {
			return logic.createNewItem(itemName, itemValue);
		} else {
			return "You have cancelled the execution of this task";
		}
	}

	private void calculateCost(Scanner sc1) {
		boolean choice = true;
		double cost = 0;
		do {
			System.out.println("What item do you want to calculate cost for? (use its id)");
			String itemID = sc1.nextLine();
			int costOfItem = logic.calculateCost(itemID);
			System.out.println("How many items are on the order?");
			int amount = Integer.parseInt(sc1.nextLine());
			int totalCostForItem = logic.calculateTotalCost(costOfItem, amount);
			cost = cost + totalCostForItem;
			System.out.println("Do you need to add any more items to this delivery? (Y or N)");
			String userChoice = sc1.nextLine();
			if (userChoice.equals("N")) {
				choice = false;
			}
		} while (choice);
		logic.applyBulkDiscount(cost);
		System.out.println("Would you like to add speedy delivery? (Y or N)");
		String deliveryChoice = sc1.nextLine();
		double finalCost = logic.applyDeliveryCharge(deliveryChoice, cost);
		System.out.println("The final cost for this order is " + finalCost + ", would you like to save this order?");
		String saveOrderChoice = sc1.nextLine();
		if (saveOrderChoice.equals("Y")) {
			System.out.println("Type the ID of the User you want to save this order too");
			int userID = Integer.parseInt(sc1.nextLine());
			logic.saveOrder(userID,finalCost);
		}
	}

	@Override
	public void start() {
		try (Scanner sc1 = new Scanner(System.in)) {
			boolean cont = true;
			do {
				try {
					System.out.println(
							"pick option 1 (Create New Item) 2 (Create New User) 3 (Display Items) 4 (Display Users) 5 (Calculate Cost) 6 (Display Orders)");
					String selection = sc1.nextLine();
					switch (selection) {
					case "1":
						System.out.println(createItem(sc1));
						break;
					case "2":
						System.out.println("Type the name of the user you wish to add");
						String userName = sc1.nextLine();
						logic.createNewUser(userName);
						break;
					case "3":
						logic.displayItems();
						break;
					case "4":
						logic.displayUsers();
						break;
					case "5":
						calculateCost(sc1);
						break;
					case "6":
						logic.displayOrders();
						break;
					default:
						System.out.println("This is not a valid choice");
						break;
					}
					System.out.println("Do you want to continue working? (Y or N)");
					String contChoice = sc1.nextLine();
					cont = !contChoice.equals("N");
				} catch (InputMismatchException ime) {
					System.out.println("That input was incorrect - please try again.");
				}
			} while (cont);
		} finally {
			System.out.println("Good-Bye!");
		}
	}
}
