package com.utilities;

public final class TestDataConstants {

	/**
	 * There should be no instance of this class.
	 */
	private TestDataConstants() { }

	/**
	 * The Report Name
	 */
	public static final boolean RUN_HEADLESS
			= Boolean.parseBoolean(FrameworkConfig.get("RUN_HEADLESS"));

	/**
	 * The Report Name
	 */
	public static final String REPORT_NAME
			= FrameworkConfig.get("REPORT_NAME");

	/**
	 * The Send Email
	 */
	public static final Boolean SEND_EMAIL
			= Boolean.valueOf(FrameworkConfig.get("SEND_EMAIL"));

	/**
	 * The Email Username
	 */
	public static final String EMAIL_USERNAME
			= FrameworkConfig.get("EMAIL_USERNAME");

	/**
	 * The Email Password
	 */
	public static final String EMAIL_PASSWORD
			= FrameworkConfig.get("EMAIL_PASSWORD");

	/**
	 * The Email-To
	 */
	public static final String EMAIL_TO
			= FrameworkConfig.get("EMAIL_TO");

	/**
	 * The Product URL
	 */
	public static final String PRODUCT_URL
			= UserDetailsConfig.get("PRODUCT_URL");

	/**
	 * The Product URL
	 */
	public static final String USERNAME
			= UserDetailsConfig.get("UserName");

	/**
	 * The Password
	 */
	public static final String PASSWORD
			= UserDetailsConfig.get("Password");

	/**
	 * The HOME_PAGE_URL
	 */
	public static final String HOME_PAGE_URL
			= UserDetailsConfig.get("productHomePageURL");







}