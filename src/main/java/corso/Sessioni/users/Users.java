package corso.Sessioni.users;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import corso.Sessioni.database.Database;

public class Users {

	@Autowired
	private Database db;

	private HashMap<String, String> users;

	public HashMap<String, String> getUsers() {
		getUsersFromDB();
		return users;
	}

	public void getUsersFromDB() {
		users = new HashMap<String, String>();
	
		String query = "select * from USERS";
		for(HashMap<String, Object> record : db.eseguiQuery(query)) {
			users.put((String)record.get("username"), (String)record.get("pass"));
		}
	}

	public String checkUser(String username, String password) {
		String ris = "KO";
		if(users.containsKey(username) && users.get(username).equals(password)) {
			ris = "OK";
		}
		return ris;
	}



	}
