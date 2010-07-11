package cz.novoj.business;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.dao.mysql.AbstractDaoTest;
import cz.novoj.model.User;
import cz.novoj.spring.utils.HostConfigurableContextLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

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
		createUserByManager(userManager);
	}

	protected void createUserByManager(UserManager userManager) {
		User myUser = new User("pepin", "sHeslem");
		Map<String, String> props = new HashMap<String, String>();

		props.put("firstName", "Pepin");
		props.put("lastName", "Grill Master");

		userManager.createUserWithProperties(myUser, props);
	}

}