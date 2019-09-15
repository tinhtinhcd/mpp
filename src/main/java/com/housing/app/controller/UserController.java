package com.housing.app.controller;

import com.housing.app.dto.LoginRequest;
import com.housing.app.dto.UserDto;
import com.housing.app.dto.UserRegisterRequest;
import com.housing.app.dto.UserUpdateRequest;
import com.housing.app.exception.LoginFailedException;
import com.housing.app.interceptor.JwtTokenProvider;
import com.housing.app.mapper.UserMapper;
import com.housing.app.model.User;
import com.housing.app.service.UserService;
import com.housing.app.util.LocalDateUtil;
import com.housing.app.util.PasswordUtil;
import com.housing.app.util.RequestUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "/user", produces = { "application/json"})
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	/**
	 * Login handler
	 *
	 * @param request
	 * @param result
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@Valid @RequestBody LoginRequest request, BindingResult result) {

		RequestUtil.validateRequest(result);
		try {
			authenticateUser(request.getUsername(), request.getPassword());
			String accessToken = tokenProvider.generateToken(request.getUsername());
			User currentUser = getCurrentUser(request.getUsername());
			UserDto dto = userMapper.toUserDto(currentUser);
			dto.setAccessToken(accessToken);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (BadCredentialsException bce) {
			throw new LoginFailedException();
		}
	}

	/**
	 * User registration
	 *
	 * @param request
	 * @param result
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterRequest request,
			BindingResult result) {

		// validate request
		RequestUtil.validateRequest(result);
		// check user with email existed
		userService.checkUserExisted(request.getEmail());

		// save to database user
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(PasswordUtil.encrypt(request.getPassword()));
		user.setActive(true);
		userService.saveUser(user);
		// set response data
		authenticateUser(request.getEmail(), request.getPassword());
		String accessToken = tokenProvider.generateToken(request.getEmail());
		UserDto userDto = userMapper.toUserDto(user);
		userDto.setAccessToken(accessToken);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserUpdateRequest request, Principal principal,
											  BindingResult result){
		RequestUtil.validateRequest(result);
		User currentUser = getCurrentUser(principal.getName());
		currentUser.setFirstName(request.getFirstName());
		currentUser.setLastName(request.getLastName());
		currentUser.setPhone(request.getPhone());
		currentUser.setDob(LocalDateUtil.convertStringToDate(request.getDob()));
		// change password if user send password in request
		if(!StringUtils.isEmpty(request.getPassword())) {
			currentUser.setPassword(PasswordUtil.encrypt(request.getPassword()));
		}
		userService.saveUser(currentUser);

		return new ResponseEntity<UserDto>(userMapper.toUserDto(currentUser), HttpStatus.OK);
	}



	// Non API function
	private void authenticateUser(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authenticatedUser = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

	private User getCurrentUser(String userName) {
		return userService.findUserByEmail(userName);
	}
}
