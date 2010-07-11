package cz.novoj.dao.mysql;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.dao.UserDao;
import cz.novoj.dao.UserPropertiesDao;
import cz.novoj.model.DatabaseUser;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.util.Map;

public class UserPropertiesDaoImplTest extends AbstractDaoTest {
	@Autowired(required = true)
	@Qualifier(value = "daoUserProperty")
	UserPropertiesDao<DatabaseUser> tested;

	@Autowired(required = true)
	UserDao<DatabaseUser> daoUser;

	DatabaseUser novoj;

	@BeforeTransaction
	public void init() throws Exception {
		DatabaseUser user = daoUser.getUser("novoj");
		if(user == null) {
			daoUser.addUser("novoj", "heslicko");
			user = daoUser.getUser("novoj");
		}
		Map props = tested.getProperties(user);
		if(props.size() != 3) {
			tested.deleteProperties(user);
			tested.addProperty(user, "street", "By the lake 1");
			tested.addProperty(user, "zip", "12345");
			tested.addProperty(user, "city", "Couple of oaks");
		}
		this.novoj = user;
	}

	@Test
	public void testAddProperty() throws Exception {
		tested.addProperty(novoj, "age", "30");
		Map properties = tested.getProperties(novoj);
		assertEquals("30", properties.get("age"));
	}

	@Test
	public void testDeleteProperty() throws Exception {
		tested.deleteProperty(novoj, "zip");
		Map properties = tested.getProperties(novoj);
		assertFalse(properties.containsKey("zip"));
	}

	@Test
	public void testDeleteProperties() throws Exception {
		tested.deleteProperties(novoj);
		Map properties = tested.getProperties(novoj);
		assertTrue(properties.isEmpty());
	}

	@Test
	public void testGetProperties() throws Exception {
		Map properties = tested.getProperties(novoj);
		assertEquals(3, properties.size());
		assertEquals("By the lake 1", properties.get("street"));
		assertEquals("12345", properties.get("zip"));
		assertEquals("Couple of oaks", properties.get("city"));

	}
}