package net.java_school.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class HomeController {

	@GetMapping
	public String index(Model model) {
		model.addAttribute("titleKeywordsDescription", "java.JDK-Install");
		return "index";
	}

	@RequestMapping(value="error", method = {RequestMethod.GET, RequestMethod.POST})
	public String error(Model model) {
		model.addAttribute("titleKeywordsDescription", "error");
		return "403";
	}

	@RequestMapping(value = "403", method = {RequestMethod.GET, RequestMethod.POST})
	public String error403(Model model) {
		model.addAttribute("titleKeywordsDescription", "403");
		return "403";
	}

	@GetMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();

		String url = UserServiceFactory.getUserService().createLogoutURL("/");

		response.sendRedirect(url);
	}

}
