package com.rmsMagement.RMSManagement.Controllers;

import java.security.Principal;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
public class loginController {
	@GetMapping("/")
	public String welcomeUser(Principal principal) {
		return "Hi  "+principal.getName()+"  welcome";
	}
}
