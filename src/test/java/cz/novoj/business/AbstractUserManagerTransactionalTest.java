package cz.novoj.business;

import cz.novoj.dao.mysql.AbstractDaoTest;
import cz.novoj.log.SpringTransactionTestAppender;
import cz.novoj.spring.utils.HostConfigurableContextLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
@ContextConfiguration(
		locations = {
				"classpath:spring/spring-business.xml",
				"classpath:spring/spring-transaction.xml"
				},
		inheritLocations = true,
		loader = HostConfigurableContextLoader.class
)
public abstract class AbstractUserManagerTransactionalTest extends AbstractDaoTest {
	private static Log log = LogFactory.getLog(UserManagerTest.class);
	private SpringTransactionTestAppender transactionTestAppender;

	@Before
	public void initLogger() {
		transactionTestAppender = new SpringTransactionTestAppender();
	}

	@After
	public void closeLogger() {
		transactionTestAppender.close();
	}

	@Test
	public final void testCreateUserWithPropertiesTransaction() throws Exception {
		try {
			callLogic();
		} catch (Exception ex) {
			String msg = "Logging error: " + ex.getMessage();
			log.error(msg, ex);
		} finally {
			String methodName = "createUserWithProperties";
			Class clazz = getManagerClass();
			assertTrue(transactionTestAppender.isTransactionOpened(clazz, methodName));
			assertTrue(transactionTestAppender.isTransactionCommited(clazz, methodName));
			assertFalse(transactionTestAppender.isTransactionRollbacked(clazz, methodName));
		}
	}

	protected abstract Class getManagerClass();

	protected abstract void callLogic();
}
