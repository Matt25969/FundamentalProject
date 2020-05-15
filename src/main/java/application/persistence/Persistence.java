package application.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import application.models.Item;
import application.models.Order;
import application.models.User;

public class Persistence {

	private String environment;

	private String jdbcUrl = "jdbc:mysql://mysql:3306/";
	private String username = "root";
	private String password = "root";
	private Connection connection;

	public Persistence(String environment) {
		this.environment = environment;
	}

	private Connection getConnection() {

		try {
			if (this.connection != null && !this.connection.isClosed()) {
				return this.connection;
			}
		} catch (SQLException sqle) {
			MyLogger.getLogger().warning(sqle.getMessage());
		}
		System.out.println("Connecting to database...");
		try {
			this.connection = DriverManager.getConnection(jdbcUrl + environment, username, password);
		} catch (SQLException e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
		return this.connection;
	}

	public int createNewItem(Item item) {
		try (Statement statement = getConnection().createStatement()) {
			return statement.executeUpdate(
					"INSERT INTO Item (name, value)" + "VALUES ( '" + item.getName() + "', " + item.getValue() + ")");
		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
		return -1;
	}

	public int createNewUser(User user) {
		try (Statement statement = getConnection().createStatement()) {
			return statement.executeUpdate("INSERT INTO User (name)" + "VALUES ( '" + user.getName() + "')");
		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
		return -1;
	}

	public Map<Integer, Item> displayItems() {
		Map<Integer, Item> itemMap = new HashMap<>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM Item");
			Item tempItem = new Item();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int value = rs.getInt("value");
				System.out.println("ID: " + id + ", name: " + name + ", value: " + value);
				tempItem.setName(name);
				tempItem.setValue(value);
				itemMap.put(id, tempItem);
			}

			rs.close();

		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
		return itemMap;
	}

	public void displayUsers() {
		try (Statement statement = getConnection().createStatement()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM User");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("ID: " + id + ", name: " + name);
			}
			rs.close();
		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
	}

	public int calculateCost(String itemID) {
		int retVal = -1;
		try (Statement statement = getConnection().createStatement()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM Item WHERE id = " + itemID);
			while (rs.next()) {
				retVal = rs.getInt("value");
			}
			rs.close();
		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
		return retVal;
	}

	public Map<Integer, Order> displayOrders() {

		Map<Integer, Order> orderMap = new HashMap<>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet rs = statement.executeQuery(
					"SELECT o.id, o.userID, o.value, User.name FROM `Order` AS o INNER JOIN User ON User.ID=o.userID");
			Order tempOrder = new Order();

			while (rs.next()) {
				int id = rs.getInt("id");
				int userID = rs.getInt("userID");
				double value = rs.getDouble("value");
				System.out.println("ID: " + id + ", userID: " + userID + ", value: " + value);
				tempOrder.setUserID(userID);
				tempOrder.setValue(value);
				orderMap.put(id, tempOrder);
			}

			rs.close();

		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
		return orderMap;
	}

	public void clearDatabase() {
		try (Statement statement = getConnection().createStatement()) {
			statement.execute("TRUNCATE TABLE user");
		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}
		try (Statement statement = getConnection().createStatement()) {
			statement.execute("TRUNCATE TABLE item");
		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}

	}

	public void saveOrder(int userID, double finalCost) {

		try (Statement statement = getConnection().createStatement()) {
			statement.executeUpdate(
					"INSERT INTO `Order` (userID, value)" + "VALUES ( " + userID + ", " + finalCost + ")");
		} catch (Exception e) {
			MyLogger.getLogger().warning(e.getMessage());
		}

	}
}
