package cz.novoj.log;

import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * Log4J appender optimalized to check Spring transaction operations being processed.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class SpringTransactionTestAppender extends TestAppender {

	public SpringTransactionTestAppender() {
		super(TransactionInterceptor.class);
	}

	public boolean isTransactionOpened(Class forClass, String forMethod) {
		return containsExactSingleLogRecord("Getting transaction for [" + forClass.getName() + "." + forMethod + "]");
	}

	public boolean isTransactionCommited(Class forClass, String forMethod) {
		return containsExactSingleLogRecord("Completing transaction for [" + forClass.getName() + "." + forMethod + "]");
	}

	public boolean isTransactionRollbacked(Class forClass, String forMethod) {
		return containsSingleLogRecord("Completing transaction for [" + forClass.getName() + "." + forMethod + "] after exception");
	}

}
