package webfood.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webfood.service.UserService;

@Controller
public class UserController {

	private final static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

}
