package cz.novoj.model;

/**
 * Description
 *
 * @author Jan Novotný, FG Forrest a.s. (c) 2007
 * @version $Id: $
 */
public class DatabaseUser extends User {
	private int id;

	public DatabaseUser() {
		super();
	}

	public DatabaseUser(String login, String password, int id) {
		super(login, password);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
