package cz.novoj.dao;

import cz.novoj.model.User;

import java.util.Map;

/**
 * Simple user properties DAO.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public interface UserPropertiesDao<T extends User> {

	void addProperty(T user, String propertyName, String propertyValue);

	void deleteProperty(T user, String propertyName);

	void deleteProperties(T user);

	Map getProperties(T user);

}