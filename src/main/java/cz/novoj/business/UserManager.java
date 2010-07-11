package cz.novoj.business;

import cz.novoj.dao.UserDao;
import cz.novoj.dao.UserPropertiesDao;
import cz.novoj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Some very simple user manager.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class UserManager {
	@Autowired(required = true)
	private UserDao daoUser;
	@Autowired(required = true)
	private UserPropertiesDao<User> daoUserProperties;

	public UserManager() {
	}

	public UserManager(UserDao daoUser, UserPropertiesDao<User> daoUserProperties) {
		this.daoUser = daoUser;
		this.daoUserProperties = daoUserProperties;
	}

	@Transactional
	public void createUserWithProperties(User user, Map<String, String> properties) {
		User createdUser = daoUser.addUser(user.getLogin(), user.getPassword());
		for(String key : properties.keySet()) {
			daoUserProperties.addProperty(createdUser, key, properties.get(key));
			//throw new RuntimeException("My test exception");
		}
	}

}
