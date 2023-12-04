package corso.Sessioni.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import corso.Sessioni.users.Users;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private Users user;

	@GetMapping("/")
	public String index(HttpSession session) {
		return "index.html";
	}

	@PostMapping("/login")
	public String login(@RequestParam HashMap<String, String> params, HttpSession session) {
		boolean esito = user.checkUser(params.get("username"), params.get("password"));

		if (esito) {
			session.setAttribute("username", params.get("username"));
			session.setAttribute("loggato", "OK");
			session.setAttribute("errore", "");
			return "redirect:/home";
		} else {
			session.setAttribute("loggato", "KO");
			session.setAttribute("errore", "invalid Username/Password");
			return "redirect:/";
		}

	}

	@GetMapping("/home")
	public String home(HttpSession session) {
		if ("OK".equals(session.getAttribute("loggato"))) {
			return "home.html";
		} else {
			session.setAttribute("errore", "Non hai effettuato il login");
			return "redirect:/";
		}
	}

	@GetMapping("/games")
	public String games(HttpSession session) {

		if ("OK".equals(session.getAttribute("loggato"))) {
			return "games.html";
		} else {
			session.setAttribute("errore", "Non hai effettuato il login");
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
