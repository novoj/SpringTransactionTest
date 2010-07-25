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

public class ClassWideAnnotationUserManagerIntegrationTest extends AbstractUserManagerTransactionalTest {

	@Autowired(required = true)
	@Qualifier("classWideAnnotationUserManager")
	ClassWideAnnotationUserManager classWideAnnotationUserManager;

	@Override
	protected Class getManagerClass() {
		return ClassWideAnnotationUserManager.class;
	}

	@Override
	protected void callLogic() {
		classWideAnnotationUserManager.createUserWithProperties(getUser(), getUserProperties());
	}
	
}