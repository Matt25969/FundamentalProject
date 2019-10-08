package application.persistence;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
	
	private static Logger logger;

	public static Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger("log");
			try {
				logger.addHandler(new FileHandler("log.log"));
			} catch (SecurityException | IOException e) {
				e.printStackTrace();
			}
		}
		return logger;
	}
}
