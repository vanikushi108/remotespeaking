package com.ca.rs.steps;

import com.ca.rs.AppProperties;
import com.ca.rs.Cleanup;
import com.ca.rs.models.ModelContainer;
import com.ca.rs.models.SeleniumDriverContainer;
import com.ca.rs.pageObjects.Common;
import com.ca.rs.pageObjects.AdminPage;
import com.ca.rs.pageObjects.LoginPage;
import com.google.common.util.concurrent.Uninterruptibles;
import cucumber.api.PendingException;
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

    @And("^I click on Examiner type Remote Assessor$")
    public void iClickOnRemoteAssessor() throws Throwable {
        adminPage.clickOnRemoteAssessor();
    }

    @And("^I click on Examiner type InRoom Assessor$")
    public void iClickOnInRoomAssessor() throws Throwable {
        adminPage.clickOnInRoomAssessor();
    }

    @And("^I click on exam type A2 Key$")
    public void iClickOnExamTypeA2Key() throws Throwable {
        adminPage.clickOnExamTypeA2Key();
    }

    @And("^I allow camera notification$")
    public void iAllowCameraNotifications() throws Throwable {

    }

    @And("^I click on Record button$")
    public void iClickOnRecordButton() throws Throwable {
        adminPage.clickOnRecordButton();
    }

    @And("^I enter user name as \"([^\"]*)\"$")
    public void iEnterUserNameAs(String txtUserName) throws Throwable {
        adminPage.enterUserName(txtUserName);
    }

    @And("^I enter password as \"([^\"]*)\"$")
    public void iEnterPasswordAs(String txtPassword) throws Throwable {
        adminPage.enterPassword(txtPassword);
    }

    @And("^I click on Login button$")
    public void iClickOnLoginButton() throws Throwable {
        adminPage.clickOnLoginButton();
    }

    @And("^I click on ok button$")
    public void iClickOnOkButton() throws Throwable {
        adminPage.clickOk();
    }

    @And("^I Select a video Recording$")
    public void iSelectAVideoRecording() throws Throwable {

    }

    @And("^I click play button$")
    public void iClickPlayButton() throws Throwable {
        adminPage.clickOnPlayButton();
    }

    @And("^I click on Play button for video Recording$")
    public void iClickOnPlayButtonForVideoRecording() throws Throwable {
        adminPage.clickOnPlayButtonForRecording();
    }

    @And("^I click on Play back button for video$")
    public void iClickOnPlayBackButtonForVideo() throws Throwable {
        adminPage.clickOnPlayBackButtonForVedio();
    }

    @And("^I select candidate one marks$")
    public void iSelectCandidateOneMarksAs() throws Throwable {
        adminPage.selectCandidateOneMarks();
    }

    @And("^I select candidate two marks$")
    public void iSelectCandidateTwoMarksAs() throws Throwable {
        adminPage.selectCandidateTwoMarks();
    }

    @And("^I click on Submit Button$")
    public void iClickOnSubmitButton() throws Throwable {
        adminPage.clickSubmitButton();
    }

    @And("^I click on Confirm Yes Submit Button$")
    public void iClickOnConfirmYesSubmitButton() throws Throwable {
        adminPage.clickConfirmYesSubmitButton();
    }

    @And("^I select \"([^\"]*)\" from drop down$")
    public void iSelectFromDropDown(String marks) throws Throwable {
        adminPage.selectDataValueFromMarkDropdown(marks);
    }

    @And("^I select candidate one Grammar and Vocabulary$")
    public void iSelectCandidateOneGrammarAndVocabulary() throws Throwable {
        adminPage.selectCandidateOneGrammerAndVocabulary();
    }

    @And("^I select candidate one Pronunciation$")
    public void iSelectCandidateOnePronunciation() throws Throwable {
        adminPage.selectCandidateOnePronunciation();
    }

    @And("^I select candidate one Interactive Communication$")
    public void iSelectCandidateOneInteractiveCommunication() throws Throwable {
        adminPage.selectCandidateOneInteractiveCommunication();
    }

    @And("^I select candidate two Grammar and Vocabulary$")
    public void iSelectCandidateTwoGrammarAndVocabulary() throws Throwable {
        adminPage.selectCandidateTwoGrammerAndVocabulary();
    }

    @And("^I select candidate two Pronunciation$")
    public void iSelectCandidateTwoPronunciation() throws Throwable {
        adminPage.selectCandidateTwoPronunciation();
    }

    @And("^I select candidate two Interactive Communication$")
    public void iSelectCandidateTwoInteractiveCommunication() throws Throwable {
        adminPage.selectCandidateTwoInteractiveCommunication();
    }

    @And("^I select part(\\d+) test material as \"([^\"]*)\"$")
    public void iSelectPartTestMaterialAs(int materialNum) throws Throwable {
        adminPage.selectPart2TestMaterial(materialNum);
    }

    @And("^I click on test type dropdown$")
    public void iClickOnTestTypeDropdown() throws Throwable {
        adminPage.clickOnExamTypeDropdown();
    }

    @And("^I select test type as \"([^\"]*)\"$")
    public void iSelectTestTypeAs(String examType) throws Throwable {
        adminPage.selectExamType(examType);
    }

    @And("^I enter Candidate one name as \"([^\"]*)\"$")
    public void iEnterCandidateOneNameAs(String name) throws Throwable {
        adminPage.enterCandidate1Name(name);
    }

    @And("^I enter candidate Two name as \"([^\"]*)\"$")
    public void iEnterCandidateTwoNameAs(String name) throws Throwable {
        adminPage.enterCandidate2Name(name);
    }

    @And("^I enter candidate One number as \"([^\"]*)\"$")
    public void iEnterCandidateOneNumberAs(String num) throws Throwable {
        adminPage.enterCandidate1Number(num);
    }

    @And("^I enter candidate Two number as \"([^\"]*)\"$")
    public void iEnterCandidateTwoNumberAs(String num) throws Throwable {
        adminPage.enterCandidate2Number(num);
    }

    @And("^I select partTwo inputBox test material as \"([^\"]*)\"$")
    public void iSelectPartTwoInputBoxTestMaterialAs(String num) throws Throwable {
        adminPage.enterPart2InputBox1Material(num);
    }

    @And("^I select partTwo inputBoxTwo test material as \"([^\"]*)\"$")
    public void iSelectPartTwoInputBoxTwoTestMaterialAs(String num) throws Throwable {
        adminPage.enterPart2InputBox2Material(num);
    }

    @And("^I select partThree inputBoxOne test material as \"([^\"]*)\"$")
    public void iSelectPartthreeInputBoxoneTestMaterialAs(String num) throws Throwable {
        adminPage.enterPart3InputBox1Material(num);
    }

    @And("^I select partThree inputBoxTwo test material as \"([^\"]*)\"$")
    public void iSelectPartThreeInputBoxTwoTestMaterialAs(String num) throws Throwable {
        adminPage.enterPart3InputBox2Material(num);
    }


    @And("^I Enter Center as \"([^\"]*)\"$")
    public void iEnterCenterAsCA(String centerNo) throws Throwable {
        adminPage.enterCenterNumber(centerNo);

    }


}