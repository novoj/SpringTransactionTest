package cz.novoj.business;

import cz.novoj.dao.UserDao;
import cz.novoj.dao.UserPropertiesDao;
import cz.novoj.model.User;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

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

	public Object getObject() throws Exception {
		ProxyFactory factory = new ProxyFactory(new UserManager(daoUser, daoUserProperties));
		factory.setProxyTargetClass(true);
		return factory.getProxy();
	}

	public Class getObjectType() {
		return UserManager.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
