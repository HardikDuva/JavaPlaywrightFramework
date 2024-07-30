package com.configuration;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.ElementHandle;
import java.lang.reflect.Field;
import com.microsoft.playwright.Page;

public class AbstractionPOM extends BaseTest {

	// Default constructor
	public AbstractionPOM(Page pageRef, ExtentTest test) {
		this.page = pageRef;
		this.extentTest = test;
		initElements();
	}

	private void initElements() {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(FindBy.class)) {
				FindBy findBy = field.getAnnotation(FindBy.class);
				String selector = findBy.selector();
				ElementHandle element = page.querySelector(selector);
				field.setAccessible(true);
				try {
					field.set(this, element);
				} catch (IllegalAccessException e) {
					errorLog(e.getMessage());
				}
			}
		}
	}

}
