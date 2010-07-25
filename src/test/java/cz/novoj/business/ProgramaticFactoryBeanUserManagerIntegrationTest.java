package cz.novoj.business;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.business.userManagerVariations.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static cz.novoj.TestUtils.getUser;
import static cz.novoj.TestUtils.getUserProperties;

public class ProgramaticFactoryBeanUserManagerIntegrationTest extends AbstractUserManagerTransactionalTest {

	@Autowired(required = true)
	@Qualifier("factoryBeanUserManager")
	UserManager factoryBeanUserManager;

	@Override
	protected Class getManagerClass() {
		return UserManager.class;
	}

	@Override
	protected void callLogic() {
		factoryBeanUserManager.createUserWithProperties(getUser(), getUserProperties());
	}
	
}