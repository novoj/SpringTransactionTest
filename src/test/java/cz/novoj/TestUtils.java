package cz.novoj;

import cz.novoj.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class TestUtils {
	public static User getUser() {
		return new User("pepin", "sHeslem");
	}

	public static Map<String, String> getUserProperties() {
		Map<String, String> props = new HashMap<String, String>();

		props.put("firstName", "Pepin");
		props.put("lastName", "Grill Master");
		return props;
	}
}
