package com.ca.rs.steps;

import com.ca.rs.AppProperties;
import com.ca.rs.pageObjects.LoginPage;
import com.ca.rs.models.SeleniumDriverContainer;
import com.ca.rs.pageObjects.Common;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Uninterruptibles;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.util.Preconditions.checkNotNull;

public class LoginSteps extends AbstractSeleniumSteps {

    private Common common;
    private LoginPage loginPage;

    @Autowired
    public LoginSteps(SeleniumDriverContainer seleniumDriverContainer, LoginPage loginPage, Common common) {
        super(seleniumDriverContainer);
        this.common = checkNotNull(common);
        this.loginPage = checkNotNull(loginPage);
        PageFactory.initElements(webDriver(), common);
        PageFactory.initElements(webDriver(), loginPage);
    }

    @And("^I enter User Id$")
    public void iEnterUserId() throws Throwable {
        loginPage.enterUserId();
    }
}