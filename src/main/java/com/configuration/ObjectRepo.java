package com.configuration;

import com.pages.LogInPage;

public class ObjectRepo extends BaseTest {

	public LogInPage ObLogIn() {

		return new LogInPage(page,extentTest);
	}

}
	
	