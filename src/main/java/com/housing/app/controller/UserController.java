package com.housing.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housing.app.iservice.IUserService;
import com.housing.app.models.UserModel;

@RestController
@PropertySource("classpath:/appConfig.properties")
@RequestMapping(value = { "${uri.user}" })
@Controller
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping(value = "/find")
	public ResponseEntity<UserModel> user(@RequestParam(value = "id") int id) {
		Assert.notNull(userService.get(id), "Body must not be null");
		return userService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
}
