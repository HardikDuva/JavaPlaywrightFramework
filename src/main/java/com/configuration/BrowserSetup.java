package com.configuration;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.utilities.TestDataConstants;

public class BrowserSetup {

	public BrowserSetup() {

	}

	public Browser setBrowser(String browserStr, Playwright playwright) {
		Browser browser;
		if (browserStr.toLowerCase().contains("chrome")) {
			browser = playwright.chromium().launch(
					new BrowserType.LaunchOptions().setHeadless
							(TestDataConstants.RUN_HEADLESS));
		} else if (browserStr.toLowerCase().contains("firefox")) {
			browser = playwright.firefox().launch(
					new BrowserType.LaunchOptions().setHeadless
							(TestDataConstants.RUN_HEADLESS));
		} else if (browserStr.toLowerCase().contains("safari")) {
			browser = playwright.webkit().launch(
					new BrowserType.LaunchOptions().setHeadless
							(TestDataConstants.RUN_HEADLESS));
		}
		else {
			browser = playwright.chromium().launch(
					new BrowserType.LaunchOptions().setHeadless
							(TestDataConstants.RUN_HEADLESS));
		}

		return browser;
	}

}