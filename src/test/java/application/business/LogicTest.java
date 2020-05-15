package application.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogicTest {

	static Logic logic = null;

	@BeforeClass
	public static void setupClass() {
		logic = new Logic("test");
		logic.clearDatabase();
	}

	@Before
	public void setup() {

		logic = new Logic("test");

	}

	@Test
	public void createNewItemTest() {

		//assertEquals("Item successfully created", logic.createNewItem("TestItem", 100));

	}

	@Test
	public void createNewUserTest() {

		//assertEquals("User successfully created", logic.createNewUser("TestUser"));

	}

}
