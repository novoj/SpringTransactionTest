package cz.novoj.dao.mysql;

import cz.novoj.model.DatabaseUser;
import cz.novoj.model.User;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Simple MySql user dao implementation.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class UserDaoImpl extends SimpleJdbcDaoSupport implements cz.novoj.dao.UserDao {
	private static ParameterizedRowMapper<DatabaseUser> userMapper;

	static {
		ParameterizedBeanPropertyRowMapper<DatabaseUser> mapper =
				new ParameterizedBeanPropertyRowMapper<DatabaseUser>();
		mapper.setMappedClass(DatabaseUser.class);
		userMapper = mapper;

	}

	public User addUser(final String login, final String password) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(
			new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps =
						connection.prepareStatement(
								"insert into T_USER (login, password) values (?, ?)", 
								new String[] {"id"}
						);
					ps.setString(1, login);
					ps.setString(2, password);
					return ps;
				}
			},
			keyHolder);

		return new DatabaseUser(login, password, keyHolder.getKey().intValue());
	}

	public void deleteUser(String login) {
		getSimpleJdbcTemplate().update(
				"delete from T_USER where login = ?",
				login
		);
	}

	public void deleteAllUsers() {
		getSimpleJdbcTemplate().update(
				"delete from T_USER"
		);
	}

	public User getUser(String login) {
		List<DatabaseUser> userList = getSimpleJdbcTemplate().query(
				"select * from T_USER where login = ?",
				userMapper,
				login
		);
		return userList.isEmpty() ? null : userList.get(0);
	}

	@SuppressWarnings({"unchecked"})
	public List<User> getUsers() {
		return getJdbcTemplate().query(
				"select * from T_USER",
				userMapper
		);
	}

}
