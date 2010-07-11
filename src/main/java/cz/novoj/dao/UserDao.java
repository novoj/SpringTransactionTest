package cz.novoj.dao;

import cz.novoj.model.User;

import java.util.List;

/**
 * Simple user DAO.
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public interface UserDao<T extends User> {

	User addUser(String login, String password);

	void deleteUser(String login);

	void deleteAllUsers();

	T getUser(String login);

	List<T> getUsers();

}
