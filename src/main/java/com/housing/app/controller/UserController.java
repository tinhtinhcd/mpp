package com.housing.app.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:/appConfig.properties")
@RequestMapping(value ={ "${uri.user}"})
public class UserController{
	
	@GetMapping(value = "/test")
	public ResponseEntity<String> user(@RequestParam(value = "id") int id) {
		return new ResponseEntity<>("welcome to housing", HttpStatus.OK);

	}
}
