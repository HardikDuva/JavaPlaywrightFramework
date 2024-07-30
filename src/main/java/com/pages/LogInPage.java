package com.pages;

import com.aventstack.extentreports.ExtentTest;
import com.configuration.AbstractionPOM;
import com.configuration.FindBy;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class LogInPage extends AbstractionPOM {

    public LogInPage(Page pageRef, ExtentTest test) {
        super(pageRef,test);
    }

    @FindBy(selector = "//div[@class=\"login-box\"]//input[@id=\"user-name\"]")
    private ElementHandle usernameInputEle;

    @FindBy(selector = "//div[@class=\"login-box\"]//input[@id=\"password\"]")
    private ElementHandle passwordInputEle;

    @FindBy(selector = "//div[@class=\"login-box\"]//input[@id=\"login-button\"]")
    private ElementHandle loginButtonClickEle;

    /**
     * The user enter username
     */
    public LogInPage enterUsername(String userName) {
        try {
            infoLog("Enter Username :" + userName);
            usernameInputEle.fill(userName);
        } catch (Exception e) {
            errorLog("Username field is not editable");
        }
        return this;
    }

    /**
     * The user enter password
     */
    public LogInPage enterPassword(String password) {
        try {
            infoLog("Enter Password :" + password);
            passwordInputEle.fill(password);
        } catch (Exception e) {
            errorLog("Password field is not editable");
        }
        return this;
    }

    /**
     * The user click on Login button
     */
    public LogInPage clickOnLoginInButton() {
        try {
            infoLog("Click on Login Button");
            loginButtonClickEle.click();
        } catch (Exception e) {
            errorLog("Login Button field is not clickable");
        }
        return this;
    }

    /**
     * Get current page URL
     */
    //will Navigate to Product URL Page
    public String getPageURL() {
        infoLog("Get Current Page URL");
        return this.page.url();
    }

}
