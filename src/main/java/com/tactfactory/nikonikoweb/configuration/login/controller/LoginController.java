package com.tactfactory.nikonikoweb.configuration.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private static final String LOGIN = "login/login";

	@RequestMapping("/login")
	public String login(HttpSession session) {
		return LOGIN;
	}
}
