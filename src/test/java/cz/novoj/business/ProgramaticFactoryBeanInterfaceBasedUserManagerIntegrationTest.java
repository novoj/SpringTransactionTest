package cz.novoj.business;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.business.userManagerVariations.InterfaceBasedUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static cz.novoj.TestUtils.getUser;
import static cz.novoj.TestUtils.getUserProperties;

public class ProgramaticFactoryBeanInterfaceBasedUserManagerIntegrationTest extends AbstractUserManagerTransactionalTest {

	@Autowired(required = true)
	@Qualifier("factoryBeanInterfaceBasedUserManager")
	InterfaceBasedUserManager factoryBeanInterfaceBasedUserManager;

	@Override
	protected Class getManagerClass() {
		return InterfaceBasedUserManager.class;
	}

	@Override
	protected void callLogic() {
		factoryBeanInterfaceBasedUserManager.createUserWithProperties(getUser(), getUserProperties());
	}
	
}