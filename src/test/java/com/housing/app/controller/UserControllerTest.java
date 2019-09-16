package com.housing.app.controller;

import com.housing.app.dto.LoginRequest;
import com.housing.app.dto.UserDto;
import com.housing.app.dto.UserRegisterRequest;
import com.housing.app.dto.UserUpdateRequest;
import com.housing.app.interceptor.JwtTokenProvider;
import com.housing.app.mapper.UserMapper;
import com.housing.app.model.User;
import com.housing.app.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import org.mockito.Mockito;

public class UserControllerTest {

	@Mock
	private AuthenticationManager mockAuthenticationManager;
	@Mock
	private JwtTokenProvider mockTokenProvider;

	@Mock
	private UserService userService;

	@Mock
	UserMapper userMapper;

	@InjectMocks
	private UserController userControllerUnder;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void testLogin() {
		final LoginRequest request = mock(LoginRequest.class);
		final Principal principal = mockCurrentUser();
		final BindingResult result = mock(BindingResult.class);
		when(userService.findUserByEmail(principal.getName())).thenReturn(mock(User.class));

		assertNotNull(userControllerUnder.login(request, result));
	}

	@Test
	public void testRegisterUser() {
		// Setup
		final UserRegisterRequest request = mock(UserRegisterRequest.class);
		final BindingResult result = mock(BindingResult.class);
		User user = mock(User.class);
		user.setEmail("email");

		when(userService.saveUser(user)).thenReturn(user);
		when(mockAuthenticationManager.authenticate(null)).thenReturn(null);
		when(mockTokenProvider.generateToken(user.getEmail())).thenReturn("result");

		final ResponseEntity<UserDto> actual = userControllerUnder.registerUser(request, result);

		assertNotNull(actual);
	}

	@Test
	public void testUpdateUser() {
		final UserUpdateRequest request = mock(UserUpdateRequest.class);
		final Principal principal = mockCurrentUser();
		final BindingResult result = mock(BindingResult.class);
		when(userService.findUserByEmail(principal.getName())).thenReturn(mock(User.class));

		final ResponseEntity<UserDto> actual = userControllerUnder.updateUser(request, principal, result);
		assertNotNull(actual);
	}

	private Authentication mockCurrentUser() {
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
		when(userService.findUserByEmail(authentication.getName())).thenReturn(mock(User.class));
		return authentication;
	}
}
