package com.housing.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SwaggerConfigTest {

	@Mock
	SwaggerConfig swaggerConfig;

	@Test
	public void testDefaultAuth() {
		assertNotNull(swaggerConfig.defaultAuth());
	}
}
