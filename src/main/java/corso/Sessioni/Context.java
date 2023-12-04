package corso.Sessioni;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import corso.Sessioni.database.Database;
import corso.Sessioni.users.Users;

@Configuration
public class Context {

	@Bean
	public Database db(@Value("${db.percorso}") String percorso,
			@Value("${db.user}") String user,
			@Value("${db.pass}") String pass) {
		System.out.println("init database");
		return new Database(percorso, user, pass);
	}
	
	@Bean
	public Users users() {
		System.out.println("init user");
		return new Users();
	}

}
