package corso.Sessioni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import corso.Sessioni.users.Users;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private Users user;
	
	@GetMapping("/")
	@ResponseBody
	public String index(HttpSession session) {
		return user.getUsers().toString();
	}
	
	@PostMapping("/login")
	public String login() {
		return "";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "";
	}
	
	
	@GetMapping("/home")
	public String home() {
		return "index.html";
	}
	
	
	

}
