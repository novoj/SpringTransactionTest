package cz.novoj.business;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.business.userManagerVariations.UserManager;
import cz.novoj.dao.mysql.AbstractDaoTest;
import cz.novoj.spring.utils.HostConfigurableContextLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import static cz.novoj.TestUtils.getUser;
import static cz.novoj.TestUtils.getUserProperties;

@ContextConfiguration(
		locations = {
				"classpath:spring/spring-business.xml",
				"classpath:spring/spring-transaction.xml"
				},
		inheritLocations = true,
		loader = HostConfigurableContextLoader.class		
)
public class UserManagerTest extends AbstractDaoTest {
	private static Log log = LogFactory.getLog(UserManagerTest.class);
	@Autowired(required = true)
	@Qualifier("userManager")
	UserManager userManager;

	@Test
	public void testCreateUserWithProperties() throws Exception {
		userManager.createUserWithProperties(getUser(), getUserProperties());
	}

}