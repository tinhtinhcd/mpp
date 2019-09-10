/**
 * 
 */
package com.housing.app.controller;

import java.util.ArrayList;
import org.junit.Test;
import org.mockito.Mockito;

import com.housing.app.models.UserModel;
import com.housing.app.service.UserServive;

/**
 * @author lyvantinh
 *
 */
public class UserControllerTest {

	@Test
	public void get() {
		UserServive service = Mockito.mock(UserServive.class);
		Mockito.when(service.getAll()).thenReturn(new ArrayList<UserModel>());
	}

}
