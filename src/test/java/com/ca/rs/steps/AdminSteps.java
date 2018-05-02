package com.ca.rs.steps;

import com.ca.rs.AppProperties;
import com.ca.rs.Cleanup;
import com.ca.rs.models.ModelContainer;
import com.ca.rs.models.SeleniumDriverContainer;
import com.ca.rs.pageObjects.Common;
import com.ca.rs.pageObjects.AdminPage;
import com.ca.rs.pageObjects.LoginPage;
import com.google.common.util.concurrent.Uninterruptibles;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ca.rs.models.ModelContainer.UNNAMED_TEST_ID;
import static com.ca.rs.models.ModelContainer.UNNAMED_TEST_NAME;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.util.Preconditions.checkNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminSteps extends AbstractSeleniumSteps {

    private Common common;
    private AdminPage adminPage;
    private LoginPage loginPage;
    private final ModelContainer modelContainer;

    String randomString = RandomStringUtils.randomAlphabetic(5);
    int uniqueId = (int) (System.currentTimeMillis() & 0xfffffff);

    private static final Logger LOG = LoggerFactory.getLogger(Cleanup.class);

    @Autowired
    public AdminSteps(SeleniumDriverContainer seleniumDriverContainer, AdminPage adminPage, LoginPage loginPage, Common common, ModelContainer modelContainer) {
        super(seleniumDriverContainer);
        this.common = checkNotNull(common);
        this.adminPage = checkNotNull(adminPage);
        this.loginPage = checkNotNull(loginPage);
        this.modelContainer = checkNotNull(modelContainer);
        PageFactory.initElements(webDriver(), common);
        PageFactory.initElements(webDriver(), adminPage);
        PageFactory.initElements(webDriver(), loginPage);
    }

    @Given("^User navigates to Remote Speaking Page$")
    public void userNavigatesToRemoteSpeakingPage() throws Throwable {
        common.navigateToURL(AppProperties.baseUrl());
    }

    @And("^I enter Examiner No as " + StepFields.INTEGER + "$")
    public void iEnterExaminerNoAs(int ExaminerNumber) throws Throwable {
        adminPage.enterExaminerNo(ExaminerNumber);
    }

    @And("^I click on Proceed button$")
    public void iClickOnProceedButton() throws Throwable {
        adminPage.clickOnProceedButton();
    }

    @And("^I click on Examiner type Interlocutor$")
    public void iClickOnInterlocutor() throws Throwable {
        adminPage.clickOnInterlocutor();
    }

    @And("^I click on Examiner type RemoteAssessor$")
    public void iClickOnRemoteAssessor() throws Throwable {
        adminPage.clickOnRemoteAssessor();
    }

    @And("^I click on Examiner type InRoomAssessor$")
    public void iClickOnInRoomAssessor() throws Throwable {
        adminPage.clickOnInRoomAssessor();
    }

    @And("^I click on exam type A2 Key$")
    public void iClickOnExamTypeA2Key() throws Throwable {
        adminPage.clickOnExamTypeA2Key();
    }
}