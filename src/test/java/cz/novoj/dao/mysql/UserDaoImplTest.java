package cz.novoj.dao.mysql;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.model.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.util.List;

public class UserDaoImplTest extends AbstractDaoTest {
	@Autowired(required = true)
	@Qualifier(value = "daoUser")
	private UserDaoImpl tested;

	@BeforeTransaction
	public void verifyInitialDatabaseState() {
		//logic to verify the initial state before a transaction is started
		List<User> users = tested.getUsers();
		if (users.size() != 3) {
			tested.deleteAllUsers();
			tested.addUser("veska", "tajneHeslo");
			tested.addUser("franc", "superTajneHeslo");
			tested.addUser("cap", "jsemBuh");
		}
	}

	@Test
	public void testAddUser() throws Exception {
		tested.addUser("novoj", "heslo");
		User user = tested.getUser("novoj");
		assertNotNull(user);
		assertEquals("heslo", user.getPassword());
	}

	@Test
	@NotTransactional
	public void testGetUser() throws Exception {
		assertNotNull(tested.getUser("cap"));
	}

	@Test
	@NotTransactional
	public void testGetUsers() throws Exception {
		assertEquals(3, tested.getUsers().size());
	}

	@Test
	public void testDeleteUser() throws Exception {
		assertNotNull(tested.getUser("veska"));
		tested.deleteUser("veska");
		assertNull(tested.getUser("veska"));
	}

}