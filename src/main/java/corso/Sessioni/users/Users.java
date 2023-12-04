package corso.Sessioni.users;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import corso.Sessioni.database.Database;

public class Users {

	@Autowired
	private Database db;

	private HashMap<String, String> usersMap;

	public void getUsersFromDB() {
		usersMap = new HashMap<String, String>();

		String query = "select * from USERS";
		for (HashMap<String, Object> record : db.eseguiQuery(query)) {
			usersMap.put((String) record.get("username"), (String) record.get("pass"));
		}
	}

	public boolean checkUser(String username, String password) {
		getUsersFromDB();
		if (usersMap.containsKey(username) && usersMap.get(username).equals(password)) {
			return true;
		}
		return false;

	}

}
