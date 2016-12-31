/*
 *			http://hanoo.org/index.php?article=how-to-generate-logs-in-java
 *			https://examples.javacodegeeks.com/core-java/util/logging/java-util-logging-example/
 * 			http://stackoverflow.com/questions/5950557/good-examples-using-java-util-logging
 */

package pkgStock;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utility {
	private final static Logger LOGGER = Logger.getLogger(Utility.class.getName());
	private static FileHandler logFileHandler = null;
	
//	Constructor
	Utility() {
		initializeLogger();
	}
	
	public void initializeLogger() {
		try {
			logFileHandler = new FileHandler("stocks.log", false);
		} catch (SecurityException | IOException exception) {
			exception.printStackTrace();
		}
		
		logFileHandler.setFormatter(new SimpleFormatter());
		Logger l = LOGGER.getLogger("");
		l.addHandler(logFileHandler);
		l.setLevel(Level.CONFIG);
	}
	
	public void log(Level level, String message) {
		LOGGER.log(level, message);
	}
	
}
