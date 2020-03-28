package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import services.UserDetailsServiceImpl;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserDetailsServiceImpl userService;
}