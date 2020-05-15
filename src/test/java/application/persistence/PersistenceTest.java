package application.persistence;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.models.Item;
import application.models.User;

public class PersistenceTest {

	static Persistence persistence = null;
	static final Item MOCK_ITEM_1 = new Item("Test", -1);
	static final User MOCK_USER_1 = new User("Test");

	@BeforeClass
	public static void setupClass() {
		persistence = new Persistence("test");
		persistence.clearDatabase();
	}

	@Before
	public void setup() {

		persistence = new Persistence("test");

	}

	@Test
	public void createNewItemTest() {

		assertEquals(1, persistence.createNewItem(MOCK_ITEM_1));

	}

	@Test
	public void createNewUserTest() {

		assertEquals(1, persistence.createNewUser(MOCK_USER_1));

	}

	@Test
	public void displayItemsTest() {

		//int previousSize = persistence.displayItems().size();

		//persistence.createNewItem(MOCK_ITEM_1);

		//assertEquals(previousSize + 1, persistence.displayItems().size());

	}

}
