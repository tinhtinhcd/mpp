package com.housing.app.ultilities;

public class Constant {

	private Constant() {
		throw new IllegalStateException("Utility class");
	}

	static PathConfig pathConfig = new PathConfig();
	
	public static String getUserPath() {
		return pathConfig.getUser();
	}
}
