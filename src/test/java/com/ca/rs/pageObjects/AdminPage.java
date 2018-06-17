package com.ca.rs.pageObjects;

import com.ca.rs.models.SeleniumDriverContainer;
import com.ca.rs.steps.AbstractSeleniumSteps;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static com.ca.rs.SeleniumUtils.*;
import static com.codeborne.selenide.Configuration.browser;

@Component
@Lazy
public class AdminPage extends AbstractSeleniumSteps {

    public AdminPage(SeleniumDriverContainer seleniumDriverContainer) {
        super(seleniumDriverContainer);
    }

    @FindBy(css = ".dialog-button.dialog-button-update")
    private WebElement CloneButton;

    @FindBy(css = "input")
    private WebElement ExaminerNo;

    @FindBy(css = "button[type='button'] > span:nth-child(2)")
    private WebElement ProceedButton;

    @FindBy(css = "label[data-option='Interlocutor']")
    private WebElement Interlocutor;

    @FindBy(css = "label[data-option='Remote Assessor']")
    private WebElement RemoteAssessor;

    @FindBy(css = "label[data-option='In-Room Assessor']")
    private WebElement InRoomAssessor;

    @FindBy(css = "label[data-option='A2 Key']")
    private WebElement ExamTypeA2Key;

    @FindBy(css = "#videorecorder-ribbon >div >div >button")
    private WebElement RecordButton;

    @FindBy(name = "email")
    private WebElement UserName;

    @FindBy(name = "password")
    private WebElement Password;

    @FindBy(css = ".auth0-lock-submit")
    private WebElement LoginButton;

    @FindBy(css = "button[data-candidate='1']")
    private WebElement selectCandidate1;

    @FindBy(css = "button[data-candidate='2']")
    private WebElement selectCandidate2;

    @FindBy(css = "#confirm-yes")
    private WebElement ClickOk;

    @FindBy(css = "#button-submit")
    private WebElement SubmitButton;

    @FindBy(css = "#confirm-yes")
    private WebElement ConfirmYesSubmitButton;

    @FindBy(linkText = "PLAY")
    private WebElement PlayButton;

    @FindBy(css = "#player-play-toggle")
    private WebElement PlayButtonRecording;

    @FindBy(css = "button[data-candidate='1'][data-category='Grammar & Vocabulary']")
    private WebElement selectCandidateOneGrammerAndVocabulary;

    @FindBy(css = "button[data-candidate='1'][data-category='Pronunciation']")
    private WebElement selectCandidateOnePronunciation;

    @FindBy(css = "button[data-candidate='1'][data-category='Interactive Communication']")
    private WebElement selectCandidateOneInteractiveCommunication;

    @FindBy(css = "button[data-candidate='2'][data-category='Grammar & Vocabulary']")
    private WebElement selectCandidateTwoGrammerAndVocabulary;

    @FindBy(css = "button[data-candidate='2'][data-category='Pronunciation']")
    private WebElement selectCandidateTwoPronunciation;

    @FindBy(css = "button[data-candidate='2'][data-category='Interactive Communication']")
    private WebElement selectCandidateTwoInteractiveCommunication;


    public void enterExaminerNo(int ExaminerNumber) throws Exception {
        waitUntilVisible(webDriver(), ExaminerNo);
        ExaminerNo.sendKeys(Integer.toString(ExaminerNumber));
    }

    public void clickOnProceedButton() throws Exception {
        waitUntilVisible(webDriver(), ProceedButton);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ProceedButton);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ProceedButton).perform();
            ProceedButton.click();
        }
    }

    public void clickOnInterlocutor() throws Exception {
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", Interlocutor);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(Interlocutor).perform();
            Interlocutor.click();
        }
    }

    public void clickOnRemoteAssessor() throws Exception {
        waitUntilVisible(webDriver(), RemoteAssessor);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", RemoteAssessor);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(RemoteAssessor).perform();
            RemoteAssessor.click();
        }
    }

    public void clickOnInRoomAssessor() throws Exception {
        waitUntilVisible(webDriver(), InRoomAssessor);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", InRoomAssessor);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(InRoomAssessor).perform();
            InRoomAssessor.click();
        }
    }

    public void clickOnExamTypeA2Key() throws Exception {
        customWait(webDriver(), ExamTypeA2Key);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ExamTypeA2Key);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ExamTypeA2Key).perform();
            ExamTypeA2Key.click();
        }
    }

    public void clickOnRecordButton() throws Exception {
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", RecordButton);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(RecordButton).perform();
            RecordButton.click();
        }
    }

    public void enterUserName(String txtUserName) throws Exception {
        waitUntilVisible(webDriver(), UserName);
        UserName.sendKeys(txtUserName);
    }

    public void enterPassword(String txtPassword) throws Exception {
        waitUntilVisible(webDriver(), Password);
        Password.sendKeys(txtPassword);
    }

    public void clickOnLoginButton() throws Exception {
        waitUntilVisible(webDriver(), LoginButton);
        LoginButton.click();
    }

    public void selectCandidateOneMarks() throws Exception {

        customWait(webDriver(), selectCandidate1);
        try {
            scrollIntoView(webDriver(), selectCandidate1);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidate1);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidate1).perform();
            selectCandidate1.click();
        }
    }

    public void selectValueFromDropdown(String marks) throws Exception {
        By locator = By.cssSelector("ul>li:nth-child(4)");
        WebElement element = webDriver().findElement(locator);
        try {
            scrollIntoView(webDriver(), element);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(element).perform();
            element.click();
        }
    }

    public void selectCandidateTwoMarks() throws Exception {

        customWait(webDriver(), selectCandidate2);
        try {
            scrollIntoView(webDriver(), selectCandidate2);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidate2);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidate2).perform();
            selectCandidate2.click();
        }
    }

    public void clickOk() throws Exception {
        try {
            //webDriver().switchTo().alert().accept();
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ClickOk);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ClickOk).perform();
            ClickOk.click();
        }
    }

    public void clickSubmitButton() {
        waitUntilVisible(webDriver(), SubmitButton);
        SubmitButton.click();
    }

    public void clickConfirmYesSubmitButton() {
        waitUntilVisible(webDriver(), ConfirmYesSubmitButton);
        ConfirmYesSubmitButton.click();
    }

    public void clickOnPlayButton() {
        waitUntilVisible(webDriver(), PlayButton);
        PlayButton.click();
    }

    public void clickOnPlayButtonForRecording() {
        waitUntilVisible(webDriver(), PlayButtonRecording);
        PlayButtonRecording.click();
        webDriver().manage().window().maximize();
    }

    public void selectCandidateOneGrammerAndVocabulary() throws Exception {

        customWait(webDriver(), selectCandidateOneGrammerAndVocabulary);
        try {
            scrollIntoView(webDriver(), selectCandidateOneGrammerAndVocabulary);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidateOneGrammerAndVocabulary);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidateOneGrammerAndVocabulary).perform();
            selectCandidateOneGrammerAndVocabulary.click();
        }
    }

    public void selectCandidateOnePronunciation() throws Exception {

        customWait(webDriver(), selectCandidateOnePronunciation);
        try {
            scrollIntoView(webDriver(), selectCandidateOnePronunciation);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidateOnePronunciation);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidateOnePronunciation).perform();
            selectCandidateOnePronunciation.click();
        }
    }

    public void selectCandidateOneInteractiveCommunication() throws Exception {

        customWait(webDriver(), selectCandidateOneInteractiveCommunication);
        try {
            scrollIntoView(webDriver(), selectCandidateOneInteractiveCommunication);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidateOneInteractiveCommunication);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidateOneInteractiveCommunication).perform();
            selectCandidateOneInteractiveCommunication.click();
        }
    }

    public void selectCandidateTwoGrammerAndVocabulary() throws Exception {

        customWait(webDriver(), selectCandidateTwoGrammerAndVocabulary);
        try {
            scrollIntoView(webDriver(), selectCandidateTwoGrammerAndVocabulary);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidateTwoGrammerAndVocabulary);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidateTwoGrammerAndVocabulary).perform();
            selectCandidateTwoGrammerAndVocabulary.click();
        }
    }

    public void selectCandidateTwoPronunciation() throws Exception {

        customWait(webDriver(), selectCandidateTwoPronunciation);
        try {
            scrollIntoView(webDriver(), selectCandidateTwoPronunciation);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidateTwoPronunciation);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidateTwoPronunciation).perform();
            selectCandidateTwoPronunciation.click();
        }
    }

    public void selectCandidateTwoInteractiveCommunication() throws Exception {

        customWait(webDriver(), selectCandidateTwoInteractiveCommunication);
        try {
            scrollIntoView(webDriver(), selectCandidateTwoInteractiveCommunication);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCandidateTwoInteractiveCommunication);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCandidateTwoInteractiveCommunication).perform();
            selectCandidateTwoInteractiveCommunication.click();
        }
    }

    public void selectDataValueFromMarkDropdown(String marks) throws Exception {
        Thread.sleep(400);
        By locator = By.cssSelector("li[role='menuitem'][data-mark-value='" + marks + "']");
        WebElement element = webDriver().findElement(locator);
        try {
            scrollIntoView(webDriver(), element);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(element).perform();
            element.click();
        }
        Thread.sleep(400);
    }
}