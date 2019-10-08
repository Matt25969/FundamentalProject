package application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ application.business.LogicTest.class, application.persistence.PersistenceTest.class })

public class TestRunner {

}
