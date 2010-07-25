package cz.novoj.business;
/**
 * Description
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */

import cz.novoj.business.userManagerVariations.ClassWideAnnotationUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static cz.novoj.TestUtils.getUser;
import static cz.novoj.TestUtils.getUserProperties;

public class ProgramaticFactoryBeanClassWideAnnotationUserManagerIntegrationTest extends AbstractUserManagerTransactionalTest {

	@Autowired(required = true)
	@Qualifier("factoryBeanClassWideAnnotationUserManager")
	ClassWideAnnotationUserManager factoryBeanClassWideAnnotationUserManager;

	@Override
	protected Class getManagerClass() {
		return ClassWideAnnotationUserManager.class;
	}

	@Override
	protected void callLogic() {
		factoryBeanClassWideAnnotationUserManager.createUserWithProperties(getUser(), getUserProperties());
	}
	
}