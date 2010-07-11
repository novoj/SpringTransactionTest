package cz.novoj.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Base test appender used to capture logging events for test purposes.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class TestAppender extends AppenderSkeleton {
	private static Log log = LogFactory.getLog(TestAppender.class);
	private final Class[] monitoredClasses;
	private final LoggerInfo[] backedUpLoggers;
	private final List<LoggingEvent> events = new ArrayList<LoggingEvent>();
	
	public TestAppender(Class monitoredClass) {
		this(new Class[] {monitoredClass});
	}

	public TestAppender(Class[] monitoredClasses) {
		super();
		this.monitoredClasses = monitoredClasses;
		if(log.isInfoEnabled()) {
			log.info("***************************************************************");
			log.info("        APPENDING TEST CONTROLLED LOGGING ENVIRONMENT          ");
			log.info("***************************************************************");
		}
		backedUpLoggers = new LoggerInfo[monitoredClasses.length];
		for(int i = 0; i < monitoredClasses.length; i++) {
			Class monitoredClass = monitoredClasses[i];
			Logger logger = LogManager.getLogger(monitoredClass);
			backedUpLoggers[i] = new LoggerInfo(logger.getLevel(), logger.getAdditivity());
			logger.setLevel(Level.TRACE);
			logger.addAppender(this);
			logger.setAdditivity(true);
		}
	}

	public void clearLogChanges() {
		for(int i = 0; i < monitoredClasses.length; i++) {
			Class monitoredClass = monitoredClasses[i];
			Logger logger = LogManager.getLogger(monitoredClass);
			logger.setLevel(backedUpLoggers[i].getOriginalLevel());
			logger.setAdditivity(backedUpLoggers[i].isOriginalAdditivity());
			logger.removeAppender(this);
		}
		if(log.isInfoEnabled()) {
			log.info("***************************************************************");
			log.info("         REMOVED TEST CONTROLLED LOGGING ENVIRONMENT           ");
			log.info("***************************************************************");
		}
	}

	protected void append(LoggingEvent event) {
		synchronized(events) {
			events.add(event);
			System.out.println(">>> Capturing : " + event.getMessage());
		}
	}

	public int countLogRecord(String messagePart) {
		int counter = 0;
		for(LoggingEvent event : events) {
			String message = (String)event.getMessage();
			if(message != null && message.indexOf(messagePart) > -1) {
				counter ++;
			}
		}
		return counter;
	}

	public int countExactLogRecord(String comparedMessage) {
		int counter = 0;
		for(LoggingEvent event : events) {
			String message = (String)event.getMessage();
			if(message != null && message.equals(comparedMessage)) {
				counter ++;
			}
		}
		return counter;
	}

	public boolean containsLogRecord(String messagePart) {
		return countLogRecord(messagePart) > 0;
	}

	public boolean containsSingleLogRecord(String messagePart) {
		return countLogRecord(messagePart) == 1;
	}

	public boolean containsExactLogRecord(String completeMessage) {
		return countExactLogRecord(completeMessage) > 0;
	}

	public boolean containsExactSingleLogRecord(String completeMessage) {
		return countExactLogRecord(completeMessage) == 1;
	}

	public void close() {
		clearLogChanges();
		events.clear();
	}

	public boolean requiresLayout() {
		return false;
	}

	private class LoggerInfo {
		Level originalLevel;
		boolean originalAdditivity;

		public LoggerInfo(Level originalLevel, boolean originalAdditivity) {
			this.originalLevel = originalLevel;
			this.originalAdditivity = originalAdditivity;
		}

		public Level getOriginalLevel() {
			return originalLevel;
		}

		public boolean isOriginalAdditivity() {
			return originalAdditivity;
		}
	}

}
