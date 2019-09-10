/**
 * 
 */
package com.housing.app.controller;

import java.util.ArrayList;

import com.housing.app.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author lyvantinh
 *
 */
public class UserControllerTest {

	@Test
	public void get() {
		UserService service = Mockito.mock(UserService.class);
		//Mockito.when(service.getAll()).thenReturn(new ArrayList<UserModel>());
	}

}
