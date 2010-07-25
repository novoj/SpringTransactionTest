package cz.novoj.business.userManagerVariations;

import cz.novoj.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public interface UserManagerIface {

	@Transactional
	void createUserWithProperties(User user, Map<String, String> properties);
	
}
