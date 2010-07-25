package cz.novoj.business;

import cz.novoj.dao.UserDao;
import cz.novoj.dao.UserPropertiesDao;
import cz.novoj.model.User;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Constructor;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class CustomFactoryBean implements FactoryBean {
	@Autowired(required = true)
	private UserDao daoUser;
	@Autowired(required = true)
	private UserPropertiesDao<User> daoUserProperties;

	private Class userManagerClass;
	private Constructor userManagerClassConstructor;

	public Object getObject() throws Exception {
		Object userManagerInstance = userManagerClassConstructor.newInstance(daoUser, daoUserProperties);
		ProxyFactory factory = new ProxyFactory(userManagerInstance);
		factory.setProxyTargetClass(true);
		return factory.getProxy();
	}

	public void setUserManagerClass(Class userManagerClass) {
		try {
			this.userManagerClass = userManagerClass;
			this.userManagerClassConstructor =
					userManagerClass.getDeclaredConstructor(UserDao.class, UserPropertiesDao.class);
		}
		catch(NoSuchMethodException e) {
			throw new RuntimeException("Cannot find appropriate constructor on user manager class!");
		}
	}

	public Class getObjectType() {
		return userManagerClass;
	}

	public boolean isSingleton() {
		return true;
	}
}
