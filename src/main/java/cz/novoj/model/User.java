package cz.novoj.model;

/**
 * Description
*
* @author Jan Novotný, FG Forrest a.s. (c) 2007
* @version $Id: $
*/
public class User {
	private String login;
	private String password;

	public User() {
		super();
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
