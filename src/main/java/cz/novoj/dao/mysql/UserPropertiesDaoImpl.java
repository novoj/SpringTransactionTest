package cz.novoj.dao.mysql;

import cz.novoj.dao.UserPropertiesDao;
import cz.novoj.model.DatabaseUser;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple MySql user properties dao implementation.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class UserPropertiesDaoImpl extends SimpleJdbcDaoSupport implements UserPropertiesDao<DatabaseUser> {

	public void addProperty(DatabaseUser user, String propertyName, String propertyValue) {
		getSimpleJdbcTemplate().update(
				"insert into T_USER_PROPERTY (idUser, propertyName, propertyValue) values (?, ?, ?)",
				user.getId(), propertyName,propertyValue
		);
	}

	public void deleteProperty(DatabaseUser user, String propertyName) {
		getSimpleJdbcTemplate().update(
				"delete from T_USER_PROPERTY where idUser = ? and propertyName = ?",
				user.getId(), propertyName
		);
	}

	public void deleteProperties(DatabaseUser user) {
		getSimpleJdbcTemplate().update(
				"delete from T_USER_PROPERTY where idUser = ?",
				user.getId()
		);
	}

	public Map getProperties(DatabaseUser user) {
		List<Map<String,Object>> properties = getSimpleJdbcTemplate().queryForList(
				"select * from T_USER_PROPERTY where idUser = ?",
				user.getId()
		);
		Map<String, String> simplifiedProperties = new HashMap<String, String>();
		for(Map<String, Object> property : properties) {
			String propertyName = (String)property.get("propertyName");
			String propertyValue = (String)property.get("propertyValue");
			simplifiedProperties.put(propertyName, propertyValue);
		}
		return simplifiedProperties;
	}

}