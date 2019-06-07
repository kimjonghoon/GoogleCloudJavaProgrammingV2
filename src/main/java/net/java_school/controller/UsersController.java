package net.java_school.controller;

import net.java_school.spring.security.GaeUserAuthentication;
import net.java_school.user.GaeUser;
import net.java_school.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/changeNickname")
	public String changeNicknameForm() {
		return "users/changeNickname";
	}
	
	@PostMapping("/changeNickname")
	public String changeNickname(String nickname, Model model, GaeUserAuthentication gaeUserAuthentication) {
		String msg = "Success";
		GaeUser gaeUser = (GaeUser) gaeUserAuthentication.getPrincipal();
		String current = gaeUser.getNickname();
		if (!current.equals(nickname)) {
			gaeUser.setNickname(nickname);
			userService.addUser(gaeUser);
			//when not enabled user try change nickname, nickname not changed. 
			GaeUser savedUser = userService.findUser(gaeUser.getEmail());
			if (current.equals(savedUser.getNickname())) {
				gaeUser.setNickname(current);
				msg = "NotEnabled";
			}
		} else {
			msg = "SameName";
		}

		model.addAttribute("msg", msg);
		
		return "redirect:/users/welcome";
	}
	
	@GetMapping("/welcome")
	public String changeNicknameConfirm() {
		return "users/welcome";
	}

}