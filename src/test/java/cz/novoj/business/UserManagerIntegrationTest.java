package cz.novoj.business;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.log.SpringTransactionTestAppender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserManagerIntegrationTest extends UserManagerTest {
	private static Log log = LogFactory.getLog(UserManagerTest.class);
	@Autowired(required = true)
	@Qualifier("userManager")
	UserManager userManager;
	@Autowired(required = true)
	@Qualifier("userManagerFromCustomFactoryBean")
	UserManager userManagerFromCustomFactoryBean;
	SpringTransactionTestAppender transactionTestAppender;

	@Before
	public void initLogger() {
		transactionTestAppender = new SpringTransactionTestAppender();
	}

	@After
	public void closeLogger() {
		transactionTestAppender.close();
	}

	@Test
	public void testCreateUserWithPropertiesTransaction() throws Exception {
		try {
			createUserByManager(userManager);
		} catch (Exception ex) {
			String msg = "Logging error: " + ex.getMessage();
			log.error(msg, ex);
		} finally {
			String methodName = "createUserWithProperties";
			assertTrue(transactionTestAppender.isTransactionOpened(UserManager.class, methodName));
			assertTrue(transactionTestAppender.isTransactionCommited(UserManager.class, methodName));
			assertFalse(transactionTestAppender.isTransactionRollbacked(UserManager.class, methodName));
		}
	}

	@Test
	public void testCreateUserWithPropertiesTransactionOnProgrammaticProxy() throws Exception {
		try {
			createUserByManager(userManagerFromCustomFactoryBean);
		} catch (Exception ex) {
			String msg = "Logging error: " + ex.getMessage();
			log.error(msg, ex);
		} finally {
			String methodName = "createUserWithProperties";
			assertTrue(transactionTestAppender.isTransactionOpened(UserManager.class, methodName));
			assertTrue(transactionTestAppender.isTransactionCommited(UserManager.class, methodName));
			assertFalse(transactionTestAppender.isTransactionRollbacked(UserManager.class, methodName));
		}
	}

}